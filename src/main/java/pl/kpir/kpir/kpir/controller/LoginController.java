package pl.kpir.kpir.kpir.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.kpir.kpir.kpir.forms.LoginForm;
import pl.kpir.kpir.kpir.services.LoginService;

@Controller
public class LoginController {


//    private final LoginService loginService;

//    public LoginController(LoginService loginService) {
//        this.loginService = loginService;
//    }

    @GetMapping(path = "/login")
    public String loadLogInForm() {
        return "loginForm";
    }



//    @PostMapping(path = "/loginUser")
//    public String logInUser(@ModelAttribute LoginForm loginForm){
//        if(loginService.logIn(loginForm)) {
//
//            return "redirect:/dashboard";
//        } else {
//            return "redirect:/login";
//        }


//    }
}
