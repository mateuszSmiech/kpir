package pl.kpir.kpir.kpir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.kpir.kpir.kpir.forms.CreateCostInvoiceForm;
import pl.kpir.kpir.kpir.model.ContractorDTO;
import pl.kpir.kpir.kpir.model.CostInvoiceDTO;
import pl.kpir.kpir.kpir.services.ContractorEntityService;
import pl.kpir.kpir.kpir.services.CostInvoiceEntityService;
import pl.kpir.kpir.kpir.services.UserUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(path = "/costInvoice")
public class CostInvoiceController {

    private final CostInvoiceEntityService costInvoiceEntityService;
    private final ContractorEntityService contractorEntityService;
    private final UserUtils userUtils;
    private HttpServletRequest request;


    public CostInvoiceController(CostInvoiceEntityService costInvoiceEntityService, ContractorEntityService contractorEntityService, UserUtils userUtils) {
        this.costInvoiceEntityService = costInvoiceEntityService;
        this.contractorEntityService = contractorEntityService;
        this.userUtils = userUtils;
    }

    @GetMapping(path = "/addCostInvoice")
    public String loadInvoice(Model model, @RequestParam(name = "returnTo", required = false) String returnTo) {
        CreateCostInvoiceForm createCostInvoiceForm = new CreateCostInvoiceForm();
        model.addAttribute("addCostInvoice", createCostInvoiceForm);
        Long loggedInUserId = userUtils.getLoggedInUserId();
        List<ContractorDTO> contractorList = contractorEntityService.findByCompanyId(loggedInUserId);
        model.addAttribute("contractorList", contractorList);
        if (returnTo != null) {
            model.addAttribute("returnTo", returnTo);
        }
        return "addCostInvoice";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String addInvoice(@ModelAttribute CreateCostInvoiceForm createCostInvoiceForm,
                             @RequestParam(name="returnTo", required = false) String returnTo) {
        costInvoiceEntityService.saveInvoice(createCostInvoiceForm);
        if (returnTo != null) {
            if (returnTo.equals("routing")) {
                return "redirect:" + createCostInvoiceForm.getRouting();
            }
        }
        return "redirect:costList";
    }


    @GetMapping(path = "/costList")
    public String salesList(Model model) {
        Long loggedInUserId = userUtils.getLoggedInUserId();

        List<CostInvoiceDTO> costInvoiceList = costInvoiceEntityService.findByCompanyId(loggedInUserId);
        model.addAttribute("costList", costInvoiceList);
        return "costList";
    }



}
