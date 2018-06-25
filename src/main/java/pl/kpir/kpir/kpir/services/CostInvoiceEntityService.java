package pl.kpir.kpir.kpir.services;

import org.springframework.stereotype.Service;
import pl.kpir.kpir.kpir.forms.CreateCostInvoiceForm;
import pl.kpir.kpir.kpir.model.CostInvoiceEntity;
import pl.kpir.kpir.kpir.repositories.CostInvoiceEntityRepository;


@Service
public class CostInvoiceEntityService {

    private final CostInvoiceEntityRepository costInvoiceEntityRepository;


    public CostInvoiceEntityService(CostInvoiceEntityRepository costInvoiceEntityRepository) {
        this.costInvoiceEntityRepository = costInvoiceEntityRepository;
    }

    public void saveInvoice(CreateCostInvoiceForm invoiceForm) {
        CostInvoiceEntity invoice = convertToInvoiceEntity(invoiceForm);
        costInvoiceEntityRepository.save(invoice);
    }


    private CostInvoiceEntity convertToInvoiceEntity(CreateCostInvoiceForm invoiceForm) {

        CostInvoiceEntity costInvoiceEntity = new CostInvoiceEntity();

        costInvoiceEntity.setIvoiceNumber(invoiceForm.getInvoiceNumber());
        costInvoiceEntity.setDate(invoiceForm.getDate());
        costInvoiceEntity.setNetValue(invoiceForm.getNetValue());
        costInvoiceEntity.setVatValue(invoiceForm.getVatValue());
        costInvoiceEntity.setInvoiceType(invoiceForm.getInvoiceType());

        return costInvoiceEntity;
    }


}
