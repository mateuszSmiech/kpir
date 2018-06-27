package pl.kpir.kpir.kpir.services;

import org.springframework.stereotype.Service;
import pl.kpir.kpir.kpir.forms.CreateSalesInvoiceForm;
import pl.kpir.kpir.kpir.model.CompanyEntity;
import pl.kpir.kpir.kpir.model.ContractorEntity;
import pl.kpir.kpir.kpir.model.SalesInvoiceEntity;
import pl.kpir.kpir.kpir.repositories.CompanyEntityRepository;
import pl.kpir.kpir.kpir.repositories.ContractorEntityRepository;
import pl.kpir.kpir.kpir.repositories.SalesInvoiceEntityRepository;


@Service

public class SalesInvoiceEntityService {

    private final SalesInvoiceEntityRepository salesInvoiceEntityRepository;
    private final UserUtils userUtils;
    private final CompanyEntityRepository companyEntityRepository;
    private final ContractorEntityRepository contractorEntityRepository;

    public SalesInvoiceEntityService(SalesInvoiceEntityRepository salesInvoiceEntityRepository, UserUtils userUtils, CompanyEntityRepository companyEntityRepository, ContractorEntityRepository contractorEntityRepository) {
        this.salesInvoiceEntityRepository = salesInvoiceEntityRepository;
        this.userUtils = userUtils;
        this.companyEntityRepository = companyEntityRepository;
        this.contractorEntityRepository = contractorEntityRepository;
    }

    public void saveInvoice(CreateSalesInvoiceForm invoiceForm) {
        SalesInvoiceEntity invoice = convertToSalesInvoiceEntity(invoiceForm);
        salesInvoiceEntityRepository.save(invoice);
    }

    private SalesInvoiceEntity convertToSalesInvoiceEntity(CreateSalesInvoiceForm invoiceForm) {

        SalesInvoiceEntity salesInvoiceEntity = new SalesInvoiceEntity();
        CompanyEntity companyByUserId = companyEntityRepository.findByUserId(userUtils.getLoggedInUserId()).get(0);
        salesInvoiceEntity.setInvoiceNumber(invoiceForm.getInvoiceNumber());
        salesInvoiceEntity.setDate(invoiceForm.getDate());
        salesInvoiceEntity.setNetValue(invoiceForm.getNetValue());
        salesInvoiceEntity.setVatValue(invoiceForm.getVatValue());
        salesInvoiceEntity.setCompanyId(companyByUserId);
        salesInvoiceEntity.setContractorEntity(contractorEntityRepository.getOne(invoiceForm.getContractorId()));


        return salesInvoiceEntity;
    }
}
