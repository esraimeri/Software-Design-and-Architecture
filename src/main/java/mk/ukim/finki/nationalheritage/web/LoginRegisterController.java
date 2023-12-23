package mk.ukim.finki.nationalheritage.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login_register")
public class LoginRegisterController {

    @GetMapping
    public String showLoginPage(Model model) {
        model.addAttribute("RUsername", "");
        model.addAttribute("LUsername", "");
        return "login_register";
    }

    @PostMapping("/login")
    public String login(@RequestParam String LUsername, Model model) {
        model.addAttribute("LUsername", LUsername);
        return "redirect:/home";
    }

    @PostMapping("/register")
    public String register(@RequestParam String RUsername, Model model) {
        model.addAttribute("RUsername", RUsername);
        return "redirect:/home";
    }
}
