package pl.kpir.kpir.kpir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.kpir.kpir.kpir.forms.CreateContractorForm;
import pl.kpir.kpir.kpir.model.ContractorDTO;
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
        return "redirect:contractorList";
    }

    @GetMapping(path = "/contractorList")
    public String contractorList(Model model) {
        Long loggedInUserId = userUtils.getLoggedInUserId();

        List<ContractorDTO> contractorList = contractorEntityService.findByCompanyId(loggedInUserId);
        model.addAttribute("contractorList", contractorList);

        return "contractorList";
    }
    @GetMapping(path ="/{id}/delete")
    public String deleteContractor(@PathVariable Long id) {
        contractorEntityService.deleteById(id);
        return "redirect:/contractor/contractorList";
    }

    @GetMapping(path = "/{id}/contractorDetails")
    public String contractorDetails(@PathVariable Long id, Model model) {
        ContractorDTO contractor = contractorEntityService.findById(id);
        model.addAttribute("contractor", contractor);
        return "contractorDetails";
    }

    @GetMapping(path="/{id}/editContractor")
    public String editContractor(@PathVariable Long id, Model model) {
        return "editContractorForm";
    }
}


