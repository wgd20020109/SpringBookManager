package book.manager.controller.api;

import book.manager.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.security.PublicKey;

@Controller
@RequestMapping("/api/admin")
public class AdminApiController {

    @Resource
    BookService service;

    @RequestMapping(value = "/del-book",method = RequestMethod.GET)
    public String deleteBook(@RequestParam("bid") int bid){
        service.deleteBook(bid);
        return "redirect:/page/admin/book";
    }

    @RequestMapping(value = "/add-book",method = RequestMethod.POST)
    public String addBook(@RequestParam("title") String title,
                          @RequestParam("price") double price,
                          @RequestParam("desc") String desc){
        service.addBook(title,price,desc);
        return "redirect:/page/admin/book";
    }

}
