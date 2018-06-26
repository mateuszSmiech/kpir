package pl.kpir.kpir.kpir.services;

import org.springframework.stereotype.Service;
import pl.kpir.kpir.kpir.forms.CreateContractorForm;
import pl.kpir.kpir.kpir.model.Address;
import pl.kpir.kpir.kpir.model.CompanyEntity;
import pl.kpir.kpir.kpir.model.ContractorDTO;
import pl.kpir.kpir.kpir.model.ContractorEntity;
import pl.kpir.kpir.kpir.repositories.CompanyEntityRepository;
import pl.kpir.kpir.kpir.repositories.ContractorEntityRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractorEntityService {

    private final ContractorEntityRepository contractorEntityRepository;
    private final CompanyEntityRepository companyEntityRepository;
    private final UserUtils userUtils;

    public ContractorEntityService(ContractorEntityRepository contractorEntityRepository, CompanyEntityRepository companyEntityRepository, UserUtils userUtils) {
        this.contractorEntityRepository = contractorEntityRepository;
        this.companyEntityRepository = companyEntityRepository;
        this.userUtils = userUtils;
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
        contractorEntity.setRegon(contractorForm.getRegon());
        contractorEntity.setAddress(address);
        contractorEntity.setTelephoneNumber(contractorForm.getTelephoneNumber());
        contractorEntity.setEmail(contractorForm.getEmail());
        CompanyEntity companyByUserId = companyEntityRepository.findByUserId(userUtils.getLoggedInUserId()).get(0);
        contractorEntity.setCompanyId(companyByUserId);

        return contractorEntity;
    }

    private ContractorDTO convertToContractorDTO(ContractorEntity contractorEntity) {
        return ContractorDTO.builder()
                .fullContractorName(contractorEntity.getFullContractorName())
                .shortContractorName(contractorEntity.getShortContractorName())
                .email(contractorEntity.getEmail())
                .nip(contractorEntity.getNip())
                .regon(contractorEntity.getRegon())
                .telephoneNumber(contractorEntity.getTelephoneNumber())
                .address(contractorEntity.getAddress())
                .build();
    }

    public List<ContractorDTO> findByCompanyId(Long id) {
        return contractorEntityRepository.findByCompanyId(id).stream().map(this::convertToContractorDTO).collect(Collectors.toList());
    }
}




