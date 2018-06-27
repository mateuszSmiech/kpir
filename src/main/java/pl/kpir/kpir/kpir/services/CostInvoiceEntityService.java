package pl.kpir.kpir.kpir.services;

import org.springframework.stereotype.Service;
import pl.kpir.kpir.kpir.forms.CreateCostInvoiceForm;
import pl.kpir.kpir.kpir.model.CompanyEntity;
import pl.kpir.kpir.kpir.model.ContractorDTO;
import pl.kpir.kpir.kpir.model.ContractorEntity;
import pl.kpir.kpir.kpir.model.CostInvoiceEntity;
import pl.kpir.kpir.kpir.repositories.CompanyEntityRepository;
import pl.kpir.kpir.kpir.repositories.ContractorEntityRepository;
import pl.kpir.kpir.kpir.repositories.CostInvoiceEntityRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CostInvoiceEntityService {

    private final CostInvoiceEntityRepository costInvoiceEntityRepository;
    private final UserUtils userUtils;
    private final CompanyEntityRepository companyEntityRepository;

    public CostInvoiceEntityService(CostInvoiceEntityRepository costInvoiceEntityRepository, UserUtils userUtils, CompanyEntityRepository companyEntityRepository) {
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
        CompanyEntity companyByUserId = companyEntityRepository.findByUserId(userUtils.getLoggedInUserId()).get(0);
        costInvoiceEntity.setCompanyId(companyByUserId);

        return costInvoiceEntity;
    }


}
