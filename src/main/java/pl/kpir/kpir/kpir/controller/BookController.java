package pl.kpir.kpir.kpir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kpir.kpir.kpir.model.CostInvoiceDTO;
import pl.kpir.kpir.kpir.model.SalesInvoiceDTO;
import pl.kpir.kpir.kpir.services.BookService;
import pl.kpir.kpir.kpir.services.UserUtils;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping(path = "/book")
public class BookController {

    private final BookService bookService;
    private final UserUtils userUtils;

    public BookController(BookService bookService, UserUtils userUtils) {
        this.bookService = bookService;
        this.userUtils = userUtils;
    }


    @GetMapping(path = "/")
    public String loadBookList(Model model,
                               @RequestParam(name = "month", required = false) String month,
                               @RequestParam(name = "year", required = false) String year) {
        Long companyId = userUtils.getLoggedInCompany();
        BigDecimal currentCostMonthSum = bookService.sumCurrentMonthCostInvoiceAmount(companyId, month, year);
        BigDecimal previousCostMonthSum = bookService.sumCostInvoiceAmountFromYearStart(companyId, month, year);
        BigDecimal currentSalesMonthSum = bookService.sumCurrentMonthSalesInvoiceAmount(companyId, month, year);
        BigDecimal previousSalesMonthSum = bookService.sumSalesInvoiceAmountFromYearStart(companyId, month, year);

        model.addAttribute("costInvoices", bookService.findCostInvoiceByDate(companyId, month, year));
        model.addAttribute("salesInvoices", bookService.findSalesInvoiceByDate(companyId, month, year));
        
        
        model.addAttribute("currentMonthCostInvoiceSum", currentCostMonthSum);
        model.addAttribute("totalCostInvoicesSum", previousCostMonthSum);
        model.addAttribute("totalCostSumForCurrentMonth", currentCostMonthSum.add(previousCostMonthSum));

        model.addAttribute("currentMonthSalesInvoiceSum", currentSalesMonthSum);
        model.addAttribute("totalSalesInvoicesSum", previousSalesMonthSum);
        model.addAttribute("totalSalesSumForCurrentMonth", currentSalesMonthSum.add(previousSalesMonthSum));
        return "book";
    }



}
