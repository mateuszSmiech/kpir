package pl.kpir.kpir.kpir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kpir.kpir.kpir.model.CostInvoiceDTO;
import pl.kpir.kpir.kpir.model.SalesInvoiceDTO;
import pl.kpir.kpir.kpir.services.BookService;

import java.math.BigDecimal;
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
        BigDecimal currentMonthSum = bookService.sumCurrentMonthCostInvoiceAmount(month, year);
        BigDecimal previousMonthSum = bookService.sumCostInvoiceAmountFromYearStart(month, year);

        List<CostInvoiceDTO> costInvoiceByDate = bookService.findCostInvoiceByDate(month, year);
        model.addAttribute("costInvoices", costInvoiceByDate);
        List<SalesInvoiceDTO> salesInvoiceByDate = bookService.findSalesInvoiceByDate(month, year);
        model.addAttribute("salesInvoices", salesInvoiceByDate);
        model.addAttribute("currentMonthCostInvoiceSum", currentMonthSum);
        model.addAttribute("totalCostInvoicesSum", previousMonthSum);

        BigDecimal totalSumForCurrentMonth = currentMonthSum.add(previousMonthSum);
        model.addAttribute("totalSumForCurrentMonth", totalSumForCurrentMonth);
        return "book";
    }



}
