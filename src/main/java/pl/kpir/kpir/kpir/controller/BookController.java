package pl.kpir.kpir.kpir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kpir.kpir.kpir.model.CostInvoiceEntity;
import pl.kpir.kpir.kpir.model.SalesInvoiceEntity;
import pl.kpir.kpir.kpir.services.BookService;

import java.util.List;

@Controller
@RequestMapping(path = "/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping(path = "/")
    public String loadBookList(Model model,
                               @RequestParam(name = "month", required = false) String month,
                               @RequestParam(name = "year", required = false) String year) {

        List<CostInvoiceEntity> costInvoiceByDate = bookService.findCostInvoiceByDate(month, year);
        model.addAttribute("costInvoices", costInvoiceByDate);
        List<SalesInvoiceEntity> salesInvoiceByDate = bookService.findSalesInvoiceByDate(month, year);
        model.addAttribute("salesInvoices", salesInvoiceByDate);

        return "book";
    }



}
