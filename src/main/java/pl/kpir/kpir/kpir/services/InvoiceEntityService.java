package pl.kpir.kpir.kpir.services;

import org.springframework.stereotype.Service;
import pl.kpir.kpir.kpir.forms.CreateContractorForm;
import pl.kpir.kpir.kpir.forms.CreateInvoiceForm;
import pl.kpir.kpir.kpir.model.Address;
import pl.kpir.kpir.kpir.model.ContractorEntity;
import pl.kpir.kpir.kpir.model.InvoiceEntity;
import pl.kpir.kpir.kpir.repositories.InvoiceEntityRepository;


@Service
public class InvoiceEntityService {

    private final InvoiceEntityRepository invoiceEntityRepository;


    public InvoiceEntityService(InvoiceEntityRepository invoiceEntityRepository) {
        this.invoiceEntityRepository = invoiceEntityRepository;
    }

    public void saveInvoice(CreateInvoiceForm invoiceForm) {
        InvoiceEntity invoice = convertToInvoiceEntity(invoiceForm);
        invoiceEntityRepository.save(invoice);
    }


    private InvoiceEntity convertToInvoiceEntity(CreateInvoiceForm invoiceForm) {

        InvoiceEntity invoiceEntity = new InvoiceEntity();

        invoiceEntity.setIvoiceNumber(invoiceForm.getIvoiceNumber());
        invoiceEntity.setDate(invoiceForm.getDate());
        invoiceEntity.setNetValue(invoiceForm.getNetValue());
        invoiceEntity.setVatValue(invoiceForm.getVatValue());
        invoiceEntity.setInvoiceType(invoiceForm.getInvoiceType());

        return invoiceEntity;
    }


}
