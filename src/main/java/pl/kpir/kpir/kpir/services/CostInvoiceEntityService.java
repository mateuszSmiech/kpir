package pl.kpir.kpir.kpir.services;

import org.springframework.stereotype.Service;
import pl.kpir.kpir.kpir.forms.CreateCostInvoiceForm;
import pl.kpir.kpir.kpir.model.Address;
import pl.kpir.kpir.kpir.model.ContractorEntity;
import pl.kpir.kpir.kpir.model.CostInvoiceEntity;
import pl.kpir.kpir.kpir.repositories.ContractorEntityRepository;
import pl.kpir.kpir.kpir.repositories.CostInvoiceEntityRepository;


@Service
public class CostInvoiceEntityService {

    private final CostInvoiceEntityRepository costInvoiceEntityRepository;
   // private final ContractorEntityRepository contractorEntityRepository;

    public CostInvoiceEntityService(CostInvoiceEntityRepository costInvoiceEntityRepository, ContractorEntityRepository contractorEntityRepository) {
        this.costInvoiceEntityRepository = costInvoiceEntityRepository;
        //this.contractorEntityRepository = contractorEntityRepository;
    }

    public void saveInvoice(CreateCostInvoiceForm invoiceForm) {
        CostInvoiceEntity invoice = convertToInvoiceEntity(invoiceForm);
        costInvoiceEntityRepository.save(invoice);
    }

    private CostInvoiceEntity convertToInvoiceEntity(CreateCostInvoiceForm invoiceForm) {
//        Address address = new Address();
//        address.setStreet(invoiceForm.getStreet());
//        address.setPostCode(invoiceForm.getPostCode());
//        address.setCity(invoiceForm.getCity());
//        address.setCountry(invoiceForm.getCountry());
//        ContractorEntity contractorEntity = new ContractorEntity();
//
//        contractorEntity.setFullContractorName(invoiceForm.getFullCompanyName());
//        contractorEntity.setShortContractorName(invoiceForm.getShortCompanyName());
//        contractorEntity.setNip(invoiceForm.getNip());
//        contractorEntity.setAddress(address);
//        contractorEntity.setTelephoneNumber(invoiceForm.getTelephoneNumber());
//        contractorEntity.setEmail(invoiceForm.getEmail());

        CostInvoiceEntity costInvoiceEntity = new CostInvoiceEntity();

        costInvoiceEntity.setInvoiceNumber(invoiceForm.getInvoiceNumber());
        costInvoiceEntity.setDate(invoiceForm.getDate());
        costInvoiceEntity.setNetValue(invoiceForm.getNetValue());
        costInvoiceEntity.setVatValue(invoiceForm.getVatValue());
        costInvoiceEntity.setInvoiceType(invoiceForm.getInvoiceType());

        //costInvoiceEntity.setContractorEntity(contractorEntity);

        return costInvoiceEntity;
    }
}
