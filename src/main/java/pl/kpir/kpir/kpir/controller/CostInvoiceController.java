package pl.kpir.kpir.kpir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.kpir.kpir.kpir.forms.CreateCostInvoiceForm;
import pl.kpir.kpir.kpir.model.ContractorDTO;
import pl.kpir.kpir.kpir.model.CostInvoiceDTO;
import pl.kpir.kpir.kpir.services.ContractorEntityService;
import pl.kpir.kpir.kpir.services.CostInvoiceEntityService;
import pl.kpir.kpir.kpir.services.UserUtils;

import java.util.List;

@Controller
@RequestMapping(path = "/costInvoice")
public class CostInvoiceController {

    private final CostInvoiceEntityService costInvoiceEntityService;
    private final ContractorEntityService contractorEntityService;
    private final UserUtils userUtils;


    public CostInvoiceController(CostInvoiceEntityService costInvoiceEntityService, ContractorEntityService contractorEntityService, UserUtils userUtils) {
        this.costInvoiceEntityService = costInvoiceEntityService;
        this.contractorEntityService = contractorEntityService;
        this.userUtils = userUtils;
    }

    @GetMapping(path = "/addCostInvoice")
    public String loadInvoice(Model model) {
        CreateCostInvoiceForm createCostInvoiceForm = new CreateCostInvoiceForm();
        model.addAttribute("addCostInvoice", createCostInvoiceForm);
        Long loggedInUserId = userUtils.getLoggedInUserId();
        List<ContractorDTO> contractorList = contractorEntityService.findByCompanyId(loggedInUserId);
        model.addAttribute("contractorList", contractorList);
        return "addCostInvoice";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String addInvoice(@ModelAttribute CreateCostInvoiceForm createCostInvoiceForm) {
        costInvoiceEntityService.saveInvoice(createCostInvoiceForm);
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
