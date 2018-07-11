package pl.kpir.kpir.kpir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.kpir.kpir.kpir.forms.CreateUserForm;
import pl.kpir.kpir.kpir.services.UserEntityService;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    private final UserEntityService userEntityService;

    public UserController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @GetMapping(path = "/registerForm")
    public String loadRegisterForm(Model model) {
        CreateUserForm createUserForm = new CreateUserForm();
        model.addAttribute("createUserForm", createUserForm);
        return "registerForm";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("createUserForm") @Valid CreateUserForm createUserForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "registerForm";
        }
        userEntityService.saveUser(createUserForm);
        return "redirect:/";
    }
}
