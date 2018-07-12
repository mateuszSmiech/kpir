package pl.kpir.kpir.kpir.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.kpir.kpir.kpir.forms.CreateCompanyForm;
import pl.kpir.kpir.kpir.services.CompanyEntityService;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/company")

public class CompanyController {
    private final CompanyEntityService companyEntityService;

    public CompanyController(CompanyEntityService companyEntityService) {
        this.companyEntityService = companyEntityService;
    }


    @GetMapping(path = "/registerCompany")
    public String loadRegisterCompany(Model model) {
        CreateCompanyForm createCompanyForm = new CreateCompanyForm();
        model.addAttribute("createCompany", createCompanyForm);
        return "registerCompany";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String addCompany(@ModelAttribute("createCompany") @Valid CreateCompanyForm createCompanyForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "registerCompany";
        }
        companyEntityService.saveCompany(createCompanyForm);
        return "redirect:/dashboard";
    }
}
