package book.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NothingController {
    @RequestMapping("/error")
    public String register(){
        return "500";
    }
}
