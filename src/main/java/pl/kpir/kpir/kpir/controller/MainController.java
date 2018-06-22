package pl.kpir.kpir.kpir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(path = "/")
    public String loadIndexPage() {
        return "redirect:/dashboard";
    }

    @GetMapping(path = "/dashboard")
    public String loadDashboard() {
        return "dashboard";
    }

}

