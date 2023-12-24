package mk.ukim.finki.nationalheritage.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//TODO COMPLETE OCNTROLLERS
@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping("/users")
    public String showUserList() {
        return "user/home.html";
    }

//    @GetMapping("/login_register")
//    public String showUserForm() {
//        return "user/login_register.html";
//    }
}
