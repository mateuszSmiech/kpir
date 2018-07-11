package pl.kpir.kpir.kpir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.kpir.kpir.kpir.forms.CreateContractorForm;
import pl.kpir.kpir.kpir.forms.EditContractorForm;
import pl.kpir.kpir.kpir.model.ContractorDTO;
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

    //"addContractor?returnTo=addInvoice"
    @GetMapping(path = "/addContractor")
    public String loadRegisterCompany(@RequestParam(name = "returnTo", required = false) String returnTo, Model model) {
        CreateContractorForm createContractorForm = new CreateContractorForm();
        model.addAttribute("addContractor", createContractorForm);

        if (returnTo != null) {
            model.addAttribute("returnTo", returnTo);
        }
        return "addContractor";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String addContractor(@ModelAttribute CreateContractorForm createContractorForm,
        @RequestParam(name = "returnTo", required = false) String returnTo) {
        contractorEntityService.saveContractor(createContractorForm);
        if (returnTo != null) {
            return "redirect:" + returnTo;
        }
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
        if(contractorEntityService.validateEntry(id)) {
            contractorEntityService.deleteById(id);
            return "redirect:/contractor/contractorList";
        }
        return "redirect:/error/403";
    }

    @GetMapping(path = "/{id}/contractorDetails")
    public String contractorDetails(@PathVariable Long id, Model model) {
        if(contractorEntityService.validateEntry(id)) {
            ContractorDTO contractor = contractorEntityService.findById(id);
            model.addAttribute("contractor", contractor);
            return "contractorDetails";
        }
        return "redirect:/error/403";
    }

    @GetMapping(path="/{id}/editContractor")
    public String editContractorForm(@PathVariable Long id, Model model) {
        if(contractorEntityService.validateEntry(id)){
        EditContractorForm editContractorForm = new EditContractorForm();
        ContractorDTO contractor = contractorEntityService.findById(id);
        model.addAttribute("currentContractor", contractor);
        model.addAttribute("editContractor", editContractorForm);
        return "editContractor";
        }
        return "redirect:/error/403";
    }
    @PostMapping(path = "/edit")
    public String editContractor(@ModelAttribute EditContractorForm editContractorForm) {
        contractorEntityService.editContractor(editContractorForm);
        return "redirect:/contractor/contractorList";
    }
}


