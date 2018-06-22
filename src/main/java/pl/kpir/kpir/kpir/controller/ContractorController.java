package pl.kpir.kpir.kpir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.kpir.kpir.kpir.forms.CreateCompanyForm;
import pl.kpir.kpir.kpir.forms.CreateContractorForm;
import pl.kpir.kpir.kpir.services.ContractorEntityService;


@Controller
@RequestMapping(path = "/contractor")

public class ContractorController {

    private final ContractorEntityService contractorEntityService;

    public ContractorController(ContractorEntityService contractorEntityService) {
        this.contractorEntityService = contractorEntityService;
    }


    @GetMapping(path = "/addContractor")
    public String loadRegisterCompany(Model model) {
        CreateContractorForm createContractorForm = new CreateContractorForm();
        model.addAttribute("addContractor", createContractorForm);
        return "addContractor";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String addContractor(@ModelAttribute CreateContractorForm createContractorForm) {
        contractorEntityService.saveContractor(createContractorForm);
        return "redirect:/";
    }
}


