package pl.kpir.kpir.kpir.services;

import org.springframework.stereotype.Service;
import pl.kpir.kpir.kpir.forms.CreateCompanyForm;
import pl.kpir.kpir.kpir.forms.CreateContractorForm;
import pl.kpir.kpir.kpir.model.Address;
import pl.kpir.kpir.kpir.model.CompanyEntity;
import pl.kpir.kpir.kpir.model.ContractorEntity;
import pl.kpir.kpir.kpir.repositories.ContractorEntityRepository;

@Service
public class ContractorEntityService {

    private final ContractorEntityRepository contractorEntityRepository;

    public ContractorEntityService(ContractorEntityRepository contractorEntityRepository) {
        this.contractorEntityRepository = contractorEntityRepository;
    }


    public void saveContractor(CreateContractorForm contractorForm) {
        ContractorEntity contractor = convertToContractorEntity(contractorForm);
        contractorEntityRepository.save(contractor);
    }

    private ContractorEntity convertToContractorEntity(CreateContractorForm contractorForm) {




        Address address = new Address();
        address.setStreet(contractorForm.getStreet());
        address.setPostCode(contractorForm.getPostCode());
        address.setCity(contractorForm.getCity());
        address.setCountry(contractorForm.getCountry());
        ContractorEntity contractorEntity = new ContractorEntity();
        contractorEntity.setShortContractorName(contractorForm.getShortCompanyName());
        contractorEntity.setFullContractorName(contractorForm.getFullCompanyName());
        contractorEntity.setNip(contractorForm.getNip());
        contractorEntity.setAddress(address);
        contractorEntity.setTelephoneNumber(contractorForm.getTelephoneNumber());
        contractorEntity.setEmail(contractorForm.getEmail());


        return contractorEntity;
    }
}