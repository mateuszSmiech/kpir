package pl.kpir.kpir.kpir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.kpir.kpir.kpir.forms.CreateCostInvoiceForm;
import pl.kpir.kpir.kpir.forms.CreateSalesInvoiceForm;
import pl.kpir.kpir.kpir.services.SalesInvoiceEntityService;

@Controller
@RequestMapping(path = "/salesInvoice")
public class SalesInvoiceController {

    private final SalesInvoiceEntityService salesInvoiceEntityService;

    public SalesInvoiceController(SalesInvoiceEntityService salesInvoiceEntityService) {
        this.salesInvoiceEntityService = salesInvoiceEntityService;
    }

    @GetMapping(path = "/addSalesInvoice")
    public String loadInvoice(Model model) {
        CreateSalesInvoiceForm createSalesInvoiceForm = new CreateSalesInvoiceForm();
        model.addAttribute("addSalesInvoice", createSalesInvoiceForm);
        return "addSalesInvoice";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String addInvoice(@ModelAttribute CreateSalesInvoiceForm createSalesInvoiceForm) {
        salesInvoiceEntityService.saveInvoice(createSalesInvoiceForm);
        return "redirect:/";
    }

}
