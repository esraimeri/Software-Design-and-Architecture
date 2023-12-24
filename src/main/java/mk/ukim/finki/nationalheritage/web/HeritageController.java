package mk.ukim.finki.nationalheritage.web;

import mk.ukim.finki.nationalheritage.model.Heritage;
import mk.ukim.finki.nationalheritage.model.HeritageType;
import mk.ukim.finki.nationalheritage.service.HeritageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//TODO ADJUST ACCORDING TO THE NECESSARY FILTERS
@Controller
@RequestMapping("/home")
public class HeritageController {

    private final HeritageService heritageService;

    public HeritageController(HeritageService heritageService) {
        this.heritageService = heritageService;
    }

    @GetMapping
    public String showList(@RequestParam(required = false) HeritageType heritageType,
                           @RequestParam(required = false) String location, Model model) {
        List<Heritage> heritages;
        if (heritageType == null && location == null) {
            heritages = heritageService.listAll();
        } else {
            heritages = heritageService.filter(heritageType, location);
        }

        model.addAttribute("heritages", heritages);
        return "home/home.html";
    }

    @GetMapping("/add")
    public String showAdd(Model model) {
        return "home/home.html";
    }


}
