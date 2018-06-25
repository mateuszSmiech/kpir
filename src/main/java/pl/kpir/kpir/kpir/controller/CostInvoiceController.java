package pl.kpir.kpir.kpir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.kpir.kpir.kpir.forms.CreateCostInvoiceForm;
import pl.kpir.kpir.kpir.services.CostInvoiceEntityService;

@Controller
@RequestMapping(path = "/costInvoice")
public class CostInvoiceController {


    private final CostInvoiceEntityService costInvoiceEntityService;

    public CostInvoiceController(CostInvoiceEntityService costInvoiceEntityService) {
        this.costInvoiceEntityService = costInvoiceEntityService;
    }


    @GetMapping(path = "/addCostInvoice")
    public String loadInvoice(Model model) {
        CreateCostInvoiceForm createCostInvoiceForm = new CreateCostInvoiceForm();
        model.addAttribute("addCostInvoice", createCostInvoiceForm);
        return "addCostInvoice";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String addInvoice(@ModelAttribute CreateCostInvoiceForm createCostInvoiceForm) {
        costInvoiceEntityService.saveInvoice(createCostInvoiceForm);
        return "redirect:/";
    }



}
