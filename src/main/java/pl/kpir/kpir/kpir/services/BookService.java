package pl.kpir.kpir.kpir.services;

import org.springframework.stereotype.Service;
import pl.kpir.kpir.kpir.model.CostInvoiceDTO;
import pl.kpir.kpir.kpir.model.SalesInvoiceDTO;
import pl.kpir.kpir.kpir.model.SalesInvoiceEntity;

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

    public List<CostInvoiceDTO> findCostInvoiceByDate(String month, String year) {
        return costInvoiceEntityService.findByCostInvoiceByDate(month, year);
    }

    public List<SalesInvoiceDTO> findSalesInvoiceByDate(String month, String year) {
        return salesInvoiceEntityService.findSalesInvoiceByDate(month, year);
    }

    public BigDecimal sumCurrentMonthCostInvoiceAmount(String month, String year) {
        return costInvoiceEntityService.sumCurrentMonthCostInvoiceAmount(month, year);
    }
}
