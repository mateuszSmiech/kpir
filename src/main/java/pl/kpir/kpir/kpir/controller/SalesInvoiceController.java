package pl.kpir.kpir.kpir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.kpir.kpir.kpir.forms.CreateSalesInvoiceForm;
import pl.kpir.kpir.kpir.forms.EditSaleInvoice;
import pl.kpir.kpir.kpir.model.ContractorDTO;
import pl.kpir.kpir.kpir.model.SalesInvoiceDTO;
import pl.kpir.kpir.kpir.services.ContractorEntityService;
import pl.kpir.kpir.kpir.services.SalesInvoiceEntityService;
import pl.kpir.kpir.kpir.services.UserUtils;

import javax.validation.Valid;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(path = "/salesInvoice")
public class SalesInvoiceController {

    private final SalesInvoiceEntityService salesInvoiceEntityService;
    private final ContractorEntityService contractorEntityService;
    private final UserUtils userUtils;

    public SalesInvoiceController(SalesInvoiceEntityService salesInvoiceEntityService, ContractorEntityService contractorEntityService, UserUtils userUtils) {
        this.salesInvoiceEntityService = salesInvoiceEntityService;
        this.contractorEntityService = contractorEntityService;
        this.userUtils = userUtils;
    }

    @GetMapping(path = "/addSalesInvoice")
    public String loadInvoice(Model model, @RequestParam(name = "returnTo", required = false) String returnTo) {
        CreateSalesInvoiceForm createSalesInvoiceForm = new CreateSalesInvoiceForm();
        model.addAttribute("addSalesInvoice", createSalesInvoiceForm);
        model.addAttribute("contractorList", contractorEntityService.findByCompanyId(userUtils.getLoggedInCompany()));
        if (returnTo != null) {
            model.addAttribute("returnTo", returnTo);
        }
        return "addSalesInvoice";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String addInvoice(@ModelAttribute("addSalesInvoice") @Valid CreateSalesInvoiceForm createSalesInvoiceForm, BindingResult bindingResult,
                             @RequestParam(name="returnTo", required = false) String returnTo) {
        if(bindingResult.hasErrors()){
            bindingResult.toString();
            return "addSalesInvoice";
        }
        LocalDate date = LocalDate.now();
        DecimalFormat numberPattern = new DecimalFormat("00");
        String month = numberPattern.format(date.getMonthValue());
        int year = date.getYear();

        salesInvoiceEntityService.saveInvoice(createSalesInvoiceForm);
        if(returnTo !=null) {
            if (returnTo.equals("routing")) {
                return "redirect:" + createSalesInvoiceForm.getRouting();
            }
        }
        return "redirect:salesList?month=" + month + "&year=" + year;
    }

    @GetMapping(path = "/salesList")
    public String salesList(Model model,
                            @RequestParam(name = "month", required = false) String month,
                            @RequestParam(name = "year", required = false) String year) {
        model.addAttribute("salesList", salesInvoiceEntityService.findByCompanyId(userUtils.getLoggedInCompany(), month, year));

        return "salesList";
    }

    @GetMapping(path ="/{id}/delete")
    public String deleteSalesInvoice(@PathVariable Long id) {
        if(salesInvoiceEntityService.validateEntry(id)) {
            salesInvoiceEntityService.deleteById(id);
            return "redirect:/salesInvoice/salesList";
        }
        return "redirect:/error/403";
    }

    @GetMapping(path="/{id}/editSaleInvoice")
    public String editCostInvoiceForm(@PathVariable Long id, Model model) {
        if(salesInvoiceEntityService.validateEntry(id)){
            EditSaleInvoice editSaleInvoice = new EditSaleInvoice();
            model.addAttribute("currentSaleInvoice", salesInvoiceEntityService.findById(id));
            model.addAttribute("editSaleInvoice", editSaleInvoice);
            model.addAttribute("contractorList", contractorEntityService.findByCompanyId(userUtils.getLoggedInCompany()));
            return "editSaleInvoice";
        }
        return "redirect:/error/403";
    }

    @PostMapping(path = "/edit")
    public String editSaleInvoice(@ModelAttribute EditSaleInvoice editSaleInvoice) {
        salesInvoiceEntityService.editSaleInvoice(editSaleInvoice);
        return "redirect:salesList";
    }



}
