package pl.kpir.kpir.kpir.services;

import org.springframework.stereotype.Service;
import pl.kpir.kpir.kpir.model.CostInvoiceEntity;
import pl.kpir.kpir.kpir.model.SalesInvoiceEntity;
import pl.kpir.kpir.kpir.repositories.CostInvoiceEntityRepository;
import pl.kpir.kpir.kpir.repositories.SalesInvoiceEntityRepository;

import java.util.List;

@Service
public class BookService {

    private final CostInvoiceEntityRepository costInvoiceEntityRepository;
    private final SalesInvoiceEntityRepository salesInvoiceEntityRepository;


    public BookService(CostInvoiceEntityRepository costInvoiceEntityRepository, SalesInvoiceEntityRepository salesInvoiceEntityRepository) {
        this.costInvoiceEntityRepository = costInvoiceEntityRepository;

        this.salesInvoiceEntityRepository = salesInvoiceEntityRepository;
    }

    public List<CostInvoiceEntity> findCostInvoiceByDate(String month, String year) {
        return costInvoiceEntityRepository.findInvoiceByDate(month, year);
    }

    public List<SalesInvoiceEntity> findSalesInvoiceByDate(String month, String year) {
        return salesInvoiceEntityRepository.findInvoiceByDate(month, year);
    }
}
