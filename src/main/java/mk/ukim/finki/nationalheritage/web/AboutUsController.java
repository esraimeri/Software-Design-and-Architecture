package mk.ukim.finki.nationalheritage.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/aboutus")
public class AboutUsController {

        @RequestMapping(method = RequestMethod.GET)
        @ResponseBody
        ModelAndView index()
        {
            return  new ModelAndView("aboutus");

        }



}
