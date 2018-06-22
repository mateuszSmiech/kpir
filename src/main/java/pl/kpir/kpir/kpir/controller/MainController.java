package pl.kpir.kpir.kpir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(path = "/")
    public String loadIndexPage() {
        return "index";
    }

    @GetMapping(path = "/dashboard")
    public String loadDashboard() {
        return "dashboard";
    }

    @GetMapping(path = "/login")
    public String loadLogInForm() {
        return "loginForm";
    }

}

