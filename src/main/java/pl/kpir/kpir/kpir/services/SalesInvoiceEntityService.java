package pl.kpir.kpir.kpir.services;

import org.springframework.stereotype.Service;
import pl.kpir.kpir.kpir.forms.CreateSalesInvoiceForm;
import pl.kpir.kpir.kpir.model.SalesInvoiceEntity;
import pl.kpir.kpir.kpir.repositories.SalesInvoiceEntityRepository;


@Service

public class SalesInvoiceEntityService {

    private final SalesInvoiceEntityRepository salesInvoiceEntityRepository;

    public SalesInvoiceEntityService(SalesInvoiceEntityRepository salesInvoiceEntityRepository) {
        this.salesInvoiceEntityRepository = salesInvoiceEntityRepository;
    }

    public void saveInvoice(CreateSalesInvoiceForm invoiceForm) {
        SalesInvoiceEntity invoice = convertToSalesInvoiceEntity(invoiceForm);
        salesInvoiceEntityRepository.save(invoice);
    }

    private SalesInvoiceEntity convertToSalesInvoiceEntity(CreateSalesInvoiceForm invoiceForm) {

        SalesInvoiceEntity salesInvoiceEntity = new SalesInvoiceEntity();

        salesInvoiceEntity.setInvoiceNumber(invoiceForm.getInvoiceNumber());
        salesInvoiceEntity.setDate(invoiceForm.getDate());
        salesInvoiceEntity.setNetValue(invoiceForm.getNetValue());
        salesInvoiceEntity.setVatValue(invoiceForm.getVatValue());

        return salesInvoiceEntity;
    }
}
