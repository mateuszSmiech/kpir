package pl.kpir.kpir.kpir.services;

import org.springframework.stereotype.Service;
import pl.kpir.kpir.kpir.forms.CreateSalesInvoiceForm;
import pl.kpir.kpir.kpir.forms.EditSaleInvoice;
import pl.kpir.kpir.kpir.model.CompanyEntity;
import pl.kpir.kpir.kpir.model.SalesInvoiceDTO;
import pl.kpir.kpir.kpir.model.SalesInvoiceEntity;
import pl.kpir.kpir.kpir.repositories.CompanyEntityRepository;
import pl.kpir.kpir.kpir.repositories.ContractorEntityRepository;
import pl.kpir.kpir.kpir.repositories.SalesInvoiceEntityRepository;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service

public class SalesInvoiceEntityService {

    private final SalesInvoiceEntityRepository salesInvoiceEntityRepository;
    private final UserUtils userUtils;
    private final CompanyEntityRepository companyEntityRepository;
    private final ContractorEntityRepository contractorEntityRepository;
    private final ContractorEntityService contractorEntityService;


    public SalesInvoiceEntityService(SalesInvoiceEntityRepository salesInvoiceEntityRepository, UserUtils userUtils,
                                     CompanyEntityRepository companyEntityRepository, ContractorEntityRepository contractorEntityRepository,
                                     ContractorEntityService contractorEntityService) {
        this.salesInvoiceEntityRepository = salesInvoiceEntityRepository;
        this.userUtils = userUtils;
        this.companyEntityRepository = companyEntityRepository;
        this.contractorEntityRepository = contractorEntityRepository;
        this.contractorEntityService = contractorEntityService;
    }

    public void saveInvoice(CreateSalesInvoiceForm invoiceForm) {
        SalesInvoiceEntity invoice = convertToSalesInvoiceEntity(invoiceForm);
        salesInvoiceEntityRepository.save(invoice);
    }

    public boolean validateEntry(Long id) {
        Long loggedInUserId = userUtils.getLoggedInUserId();

        Optional<Long> loggedCompanyId = companyEntityRepository.findByUserId(loggedInUserId)
                .stream()
                .map(CompanyEntity::getId)
                .findFirst();

        return salesInvoiceEntityRepository.findById(id)
                .map(SalesInvoiceEntity::getCompanyId)
                .map(CompanyEntity::getId)
                .filter(costId -> loggedCompanyId.get().equals(costId))
                .isPresent();
    }

    public void deleteById(Long id) {
        salesInvoiceEntityRepository.deleteById(id);
    }

    private SalesInvoiceEntity convertToSalesInvoiceEntity(CreateSalesInvoiceForm invoiceForm) {

        SalesInvoiceEntity salesInvoiceEntity = new SalesInvoiceEntity();
        CompanyEntity companyByUserId = companyEntityRepository.findByUserId(userUtils.getLoggedInUserId()).get(0);
        salesInvoiceEntity.setInvoiceNumber(invoiceForm.getInvoiceNumber());
        salesInvoiceEntity.setDesc(invoiceForm.getDesc());
        salesInvoiceEntity.setDate(Date.valueOf(invoiceForm.getDate()));
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return SalesInvoiceDTO.builder()
                .id(salesInvoiceEntity.getId())
                .invoiceNumber(salesInvoiceEntity.getInvoiceNumber())
                .desc(salesInvoiceEntity.getDesc())
                .date(dateFormat.format(salesInvoiceEntity.getDate()))
                .netValue(salesInvoiceEntity.getNetValue())
                .vatValue(salesInvoiceEntity.getVatValue())
                .vatAmount(salesInvoiceEntity.getVatAmount())
                .invoiceAmount(salesInvoiceEntity.getInvoiceAmount())
                .contractorId(contractorEntityService.convertToContractorDTO(salesInvoiceEntity.getContractorEntity()))
                .build();
    }

    public List<SalesInvoiceDTO> findByCompanyId(Long id) {
        return salesInvoiceEntityRepository.findByCompanyId(id).stream().map(this::convertToSalesInvoicesDTO).collect(Collectors.toList());
    }

    public SalesInvoiceDTO findById(Long id) {

        return convertToSalesInvoicesDTO(salesInvoiceEntityRepository.findById(id).orElse(null));
    }

    public void editSaleInvoice(EditSaleInvoice editSaleInvoice) {

        SalesInvoiceEntity salesInvoiceEntity = new SalesInvoiceEntity();
        salesInvoiceEntity.setId(editSaleInvoice.getId());
        salesInvoiceEntity.setInvoiceNumber(editSaleInvoice.getInvoiceNumber());
        salesInvoiceEntity.setDesc(editSaleInvoice.getDesc());
        salesInvoiceEntity.setDate(Date.valueOf(editSaleInvoice.getDate()));
        salesInvoiceEntity.setNetValue(editSaleInvoice.getNetValue());
        salesInvoiceEntity.setVatValue(editSaleInvoice.getVatValue());
        salesInvoiceEntity.setVatAmount(editSaleInvoice.getNetValue().multiply(editSaleInvoice.getVatValue().divide(BigDecimal.valueOf(100), new MathContext(2))));
        salesInvoiceEntity.setInvoiceAmount(editSaleInvoice.getNetValue().add(editSaleInvoice.getNetValue().multiply(editSaleInvoice.getVatValue().divide(BigDecimal.valueOf(100), new MathContext(2)))));

        CompanyEntity companyByUserId = companyEntityRepository.findByUserId(userUtils.getLoggedInUserId()).get(0);
        salesInvoiceEntity.setCompanyId(companyByUserId);

        salesInvoiceEntity.setContractorEntity(contractorEntityRepository.getOne(editSaleInvoice.getContractorId()));
        salesInvoiceEntityRepository.save(salesInvoiceEntity);

    }

    public List<SalesInvoiceDTO> findSalesInvoiceByDate(String month, String year) {
        return salesInvoiceEntityRepository.findInvoiceByDate(month, year).stream().map(this::convertToSalesInvoicesDTO).collect(Collectors.toList());
    }
}
