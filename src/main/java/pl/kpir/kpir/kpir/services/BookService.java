package pl.kpir.kpir.kpir.services;

import org.springframework.stereotype.Service;
import pl.kpir.kpir.kpir.model.CostInvoiceEntity;
import pl.kpir.kpir.kpir.repositories.CostInvoiceEntityRepository;
import pl.kpir.kpir.kpir.repositories.SalesInvoiceEntityRepository;

import java.util.List;

@Service
public class BookService {

    private final CostInvoiceEntityRepository costInvoiceEntityRepository;


    public BookService(CostInvoiceEntityRepository costInvoiceEntityRepository) {
        this.costInvoiceEntityRepository = costInvoiceEntityRepository;

    }

    public List<CostInvoiceEntity> findCostInvoiceByDate(String month, String year) {
        return costInvoiceEntityRepository.findInvoiceByDate(month, year);
    }
}
