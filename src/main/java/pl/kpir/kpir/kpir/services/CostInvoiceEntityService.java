package pl.kpir.kpir.kpir.services;

import org.springframework.stereotype.Service;
import pl.kpir.kpir.kpir.forms.CreateCostInvoiceForm;
import pl.kpir.kpir.kpir.forms.EditCostInvoice;
import pl.kpir.kpir.kpir.model.CompanyEntity;
import pl.kpir.kpir.kpir.model.CostInvoiceDTO;
import pl.kpir.kpir.kpir.model.CostInvoiceEntity;
import pl.kpir.kpir.kpir.repositories.CompanyEntityRepository;
import pl.kpir.kpir.kpir.repositories.ContractorEntityRepository;
import pl.kpir.kpir.kpir.repositories.CostInvoiceEntityRepository;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CostInvoiceEntityService {

    private final CostInvoiceEntityRepository costInvoiceEntityRepository;
    private final UserUtils userUtils;
    private final CompanyEntityRepository companyEntityRepository;
    private final ContractorEntityRepository contractorEntityRepository;
    private final ContractorEntityService contractorEntityService;

    public CostInvoiceEntityService(CostInvoiceEntityRepository costInvoiceEntityRepository, UserUtils userUtils,
                                    CompanyEntityRepository companyEntityRepository, ContractorEntityRepository contractorEntityRepository,
                                    ContractorEntityService contractorEntityService) {
        this.costInvoiceEntityRepository = costInvoiceEntityRepository;
        this.userUtils = userUtils;
        this.companyEntityRepository = companyEntityRepository;
        this.contractorEntityRepository = contractorEntityRepository;
        this.contractorEntityService = contractorEntityService;
    }


    public void saveInvoice(CreateCostInvoiceForm invoiceForm) {
        CostInvoiceEntity invoice = convertToCostInvoiceEntity(invoiceForm);
        costInvoiceEntityRepository.save(invoice);
    }

    private CostInvoiceEntity convertToCostInvoiceEntity(CreateCostInvoiceForm invoiceForm) {
        CostInvoiceEntity costInvoiceEntity = new CostInvoiceEntity();
        costInvoiceEntity.setInvoiceNumber(invoiceForm.getInvoiceNumber());
        costInvoiceEntity.setDesc(invoiceForm.getDesc());
        costInvoiceEntity.setDate(Date.valueOf(invoiceForm.getDate()));
        costInvoiceEntity.setNetValue(invoiceForm.getNetValue());
        costInvoiceEntity.setVatValue(invoiceForm.getVatValue());
        costInvoiceEntity.setVatAmount(invoiceForm.getNetValue().multiply(invoiceForm.getVatValue().divide(BigDecimal.valueOf(100), new MathContext(2))));
        costInvoiceEntity.setInvoiceAmount(invoiceForm.getNetValue().add(invoiceForm.getNetValue().multiply(invoiceForm.getVatValue().divide(BigDecimal.valueOf(100), new MathContext(2)))));
        costInvoiceEntity.setInvoiceType(invoiceForm.getInvoiceType());

        CompanyEntity companyByUserId = companyEntityRepository.findByUserId(userUtils.getLoggedInUserId()).get(0);
        costInvoiceEntity.setCompanyId(companyByUserId);

        costInvoiceEntity.setContractorEntity(contractorEntityRepository.getOne(invoiceForm.getContractorId()));
        return costInvoiceEntity;
    }

    private CostInvoiceDTO convertToCostInvoicesDTO(CostInvoiceEntity costInvoiceEntity) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return CostInvoiceDTO.builder()
                .id(costInvoiceEntity.getId())
                .invoiceNumber(costInvoiceEntity.getInvoiceNumber())
                .desc(costInvoiceEntity.getDesc())
                .date(dateFormat.format(costInvoiceEntity.getDate()))
                .netValue(costInvoiceEntity.getNetValue())
                .vatAmount(costInvoiceEntity.getVatAmount())
                .invoiceAmount(costInvoiceEntity.getInvoiceAmount())
                .contractorId(contractorEntityService.convertToContractorDTO(costInvoiceEntity.getContractorEntity()))
                .build();
    }

    public List<CostInvoiceDTO> findByCompanyId(Long id) {
        return costInvoiceEntityRepository.findByCompanyId(id).stream().map(this::convertToCostInvoicesDTO).collect(Collectors.toList());
    }

    public boolean validateEntry(Long id) {
        Long loggedInUserId = userUtils.getLoggedInUserId();

        Optional<Long> loggedCompanyId = companyEntityRepository.findByUserId(loggedInUserId)
                .stream()
                .map(CompanyEntity::getId)
                .findFirst();

        return costInvoiceEntityRepository.findById(id)
                .map(CostInvoiceEntity::getCompanyId)
                .map(CompanyEntity::getId)
                .filter(costId -> loggedCompanyId.get().equals(costId))
                .isPresent();
    }

    public void deleteById(Long id) {
        costInvoiceEntityRepository.deleteById(id);
    }

    public CostInvoiceDTO findById(Long id) {
        return convertToCostInvoicesDTO(costInvoiceEntityRepository.findById(id).orElse(null));
    }

    public void editCostInvoice(EditCostInvoice editCostInvoice) {

        CostInvoiceEntity costInvoiceEntity = new CostInvoiceEntity();
        costInvoiceEntity.setId(editCostInvoice.getId());
        costInvoiceEntity.setInvoiceNumber(editCostInvoice.getInvoiceNumber());
        costInvoiceEntity.setDesc(editCostInvoice.getDesc());
        costInvoiceEntity.setDate(Date.valueOf(editCostInvoice.getDate()));
        costInvoiceEntity.setNetValue(editCostInvoice.getNetValue());
        costInvoiceEntity.setVatValue(editCostInvoice.getVatValue());
        costInvoiceEntity.setVatAmount(editCostInvoice.getNetValue().multiply(editCostInvoice.getVatValue().divide(BigDecimal.valueOf(100), new MathContext(2))));
        costInvoiceEntity.setInvoiceAmount(editCostInvoice.getNetValue().add(editCostInvoice.getNetValue().multiply(editCostInvoice.getVatValue().divide(BigDecimal.valueOf(100), new MathContext(2)))));
        costInvoiceEntity.setInvoiceType(editCostInvoice.getInvoiceType());

        CompanyEntity companyByUserId = companyEntityRepository.findByUserId(userUtils.getLoggedInUserId()).get(0);
        costInvoiceEntity.setCompanyId(companyByUserId);

        costInvoiceEntity.setContractorEntity(contractorEntityRepository.getOne(editCostInvoice.getContractorId()));
        costInvoiceEntityRepository.save(costInvoiceEntity);
    }

    public List<CostInvoiceDTO> findByCostInvoiceByDate(String month, String year) {
        return costInvoiceEntityRepository.findInvoiceByDate(month, year).stream().map(this::convertToCostInvoicesDTO).collect(Collectors.toList());
    }
}
