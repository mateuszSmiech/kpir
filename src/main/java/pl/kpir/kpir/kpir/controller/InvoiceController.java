package pl.kpir.kpir.kpir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.kpir.kpir.kpir.forms.CreateInvoiceForm;
import pl.kpir.kpir.kpir.services.InvoiceEntityService;

@Controller
@RequestMapping(path = "/invoice")
public class InvoiceController {


    private final InvoiceEntityService invoiceEntityService;

    public InvoiceController(InvoiceEntityService invoiceEntityService) {
        this.invoiceEntityService = invoiceEntityService;
    }


    @GetMapping(path = "/addInvoice")
    public String loadInvoice(Model model) {
        CreateInvoiceForm createInvoiceForm = new CreateInvoiceForm();
        model.addAttribute("addInvoice", createInvoiceForm);
        return "addInvoice";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String addInvoice(@ModelAttribute CreateInvoiceForm createInvoiceForm) {
        invoiceEntityService.saveInvoice(createInvoiceForm);
        return "redirect:/";
    }



}
