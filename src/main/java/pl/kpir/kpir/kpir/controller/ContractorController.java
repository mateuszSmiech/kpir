package pl.kpir.kpir.kpir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.kpir.kpir.kpir.forms.CreateContractorForm;
import pl.kpir.kpir.kpir.model.ContractorEntity;
import pl.kpir.kpir.kpir.services.ContractorEntityService;
import pl.kpir.kpir.kpir.services.UserUtils;

import java.util.List;


@Controller
@RequestMapping(path = "/contractor")

public class ContractorController {

    private final ContractorEntityService contractorEntityService;
    private final UserUtils userUtils;

    public ContractorController(ContractorEntityService contractorEntityService, UserUtils userUtils) {
        this.contractorEntityService = contractorEntityService;
        this.userUtils = userUtils;
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

    @RequestMapping(path = "/contractorList", method = RequestMethod.POST)
    public String contractorList(Model model) {
        Long loggedInUserId = userUtils.getLoggedInUserId();

        List<ContractorEntity> contractorList = contractorEntityService.findByCompanyId(loggedInUserId);
        model.addAttribute("contractorList", contractorList);



        return "contractorList";
    }
}


