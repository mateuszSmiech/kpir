package pl.kpir.kpir.kpir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kpir.kpir.kpir.forms.BookListForm;

@Controller
@RequestMapping(path = "/book")
public class BookController {

    @GetMapping(path = "/")
    public String loadBookList(Model model, @ModelAttribute BookListForm bookListForm) {
        BookListForm bookListFormToModel = new BookListForm();
        model.addAttribute("bookListForm", bookListFormToModel);
        return "book";
    }



}
