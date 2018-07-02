package pl.kpir.kpir.kpir.services;

import org.springframework.stereotype.Service;
import pl.kpir.kpir.kpir.forms.CreateSalesInvoiceForm;
import pl.kpir.kpir.kpir.model.*;
import pl.kpir.kpir.kpir.repositories.CompanyEntityRepository;
import pl.kpir.kpir.kpir.repositories.ContractorEntityRepository;
import pl.kpir.kpir.kpir.repositories.SalesInvoiceEntityRepository;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.stream.Collectors;


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
        salesInvoiceEntity.setDesc(invoiceForm.getDesc());
        salesInvoiceEntity.setDate(invoiceForm.getDate());
        salesInvoiceEntity.setNetValue(invoiceForm.getNetValue());
        salesInvoiceEntity.setVatValue(invoiceForm.getVatValue());
        salesInvoiceEntity.setVatAmount(invoiceForm.getNetValue().multiply(invoiceForm.getVatValue().divide(BigDecimal.valueOf(100), new MathContext(2))));
        salesInvoiceEntity.setInvoiceAmount(invoiceForm.getNetValue().add(invoiceForm.getNetValue().multiply(invoiceForm.getVatValue().divide(BigDecimal.valueOf(100), new MathContext(2)))));
        salesInvoiceEntity.setCompanyId(companyByUserId);
        salesInvoiceEntity.setContractorEntity(contractorEntityRepository.getOne(invoiceForm.getContractorId()));


        //.vatAmount(salesInvoiceEntity.getNetValue().multiply(salesInvoiceEntity.getVatValue()).divide(BigDecimal.valueOf(100)))
        //                .invoiceAmount(salesInvoiceEntity.getNetValue().add(salesInvoiceEntity.getNetValue().multiply(salesInvoiceEntity.getVatValue()).divide(BigDecimal.valueOf(100))))

        return salesInvoiceEntity;
    }


    private SalesInvoiceDTO convertToSalesInvoicesDTO(SalesInvoiceEntity salesInvoiceEntity) {

        return SalesInvoiceDTO.builder()
                .id(salesInvoiceEntity.getId())
                .invoiceNumber(salesInvoiceEntity.getInvoiceNumber())
                .desc(salesInvoiceEntity.getDesc())
                .date(salesInvoiceEntity.getDate())
                .netValue(salesInvoiceEntity.getNetValue())
                .vatValue(salesInvoiceEntity.getVatValue())
                .vatAmount(salesInvoiceEntity.getVatAmount())
                .invoiceAmount(salesInvoiceEntity.getInvoiceAmount())
                .build();
    }

    public List<SalesInvoiceDTO> findByCompanyId(Long id) {
        return salesInvoiceEntityRepository.findByCompanyId(id).stream().map(this::convertToSalesInvoicesDTO).collect(Collectors.toList());
    }
}
