package pl.kpir.kpir.kpir.services;

import org.springframework.stereotype.Service;
import pl.kpir.kpir.kpir.model.CostInvoiceDTO;
import pl.kpir.kpir.kpir.model.SalesInvoiceDTO;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BookService {

    private final CostInvoiceEntityService costInvoiceEntityService;
    private final SalesInvoiceEntityService salesInvoiceEntityService;

    public BookService(CostInvoiceEntityService costInvoiceEntityService, SalesInvoiceEntityService salesInvoiceEntityService) {
        this.costInvoiceEntityService = costInvoiceEntityService;
        this.salesInvoiceEntityService = salesInvoiceEntityService;
    }

    public List<CostInvoiceDTO> findCostInvoiceByDate(Long id, String month, String year) {
        return costInvoiceEntityService.findByCostInvoiceByDate(id, month, year);
    }

    public List<SalesInvoiceDTO> findSalesInvoiceByDate(String month, String year) {
        return salesInvoiceEntityService.findSalesInvoiceByDate(month, year);
    }

    public BigDecimal sumCurrentMonthCostInvoiceAmount(Long id, String month, String year) {
        return costInvoiceEntityService.sumCurrentMonthCostInvoiceAmount(id, month, year);
    }

    public BigDecimal sumCostInvoiceAmountFromYearStart(String month, String year) {
        return costInvoiceEntityService.sumInvoicesValuesFromYearStart(month, year);
    }
}
