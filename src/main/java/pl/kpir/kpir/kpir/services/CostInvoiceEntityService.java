package pl.kpir.kpir.kpir.services;

import org.springframework.stereotype.Service;
import pl.kpir.kpir.kpir.forms.CreateCostInvoiceForm;
import pl.kpir.kpir.kpir.model.CostInvoiceEntity;
import pl.kpir.kpir.kpir.repositories.CompanyEntityRepository;
import pl.kpir.kpir.kpir.repositories.ContractorEntityRepository;
import pl.kpir.kpir.kpir.repositories.CostInvoiceEntityRepository;


@Service
public class CostInvoiceEntityService {

    private final ContractorEntityRepository contractorEntityRepository;
    private final CostInvoiceEntityRepository costInvoiceEntityRepository;
    private final UserUtils userUtils;
    private final CompanyEntityRepository companyEntityRepository;

    public CostInvoiceEntityService(ContractorEntityRepository contractorEntityRepository, CostInvoiceEntityRepository costInvoiceEntityRepository, UserUtils userUtils, CompanyEntityRepository companyEntityRepository) {
        this.contractorEntityRepository = contractorEntityRepository;
        this.costInvoiceEntityRepository = costInvoiceEntityRepository;
        this.userUtils = userUtils;
        this.companyEntityRepository = companyEntityRepository;
    }


    public void saveInvoice(CreateCostInvoiceForm invoiceForm) {
        CostInvoiceEntity invoice = convertToCostInvoiceEntity(invoiceForm);
        costInvoiceEntityRepository.save(invoice);
    }

    private CostInvoiceEntity convertToCostInvoiceEntity(CreateCostInvoiceForm invoiceForm) {
        CostInvoiceEntity costInvoiceEntity = new CostInvoiceEntity();

        costInvoiceEntity.setInvoiceNumber(invoiceForm.getInvoiceNumber());
        costInvoiceEntity.setDate(invoiceForm.getDate());
        costInvoiceEntity.setNetValue(invoiceForm.getNetValue());
        costInvoiceEntity.setVatValue(invoiceForm.getVatValue());
        costInvoiceEntity.setInvoiceType(invoiceForm.getInvoiceType());

        return costInvoiceEntity;
    }
}
