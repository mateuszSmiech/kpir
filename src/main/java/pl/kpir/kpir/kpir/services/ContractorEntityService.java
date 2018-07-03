package pl.kpir.kpir.kpir.services;

import org.springframework.stereotype.Service;
import pl.kpir.kpir.kpir.forms.CreateContractorForm;
import pl.kpir.kpir.kpir.forms.EditContractorForm;
import pl.kpir.kpir.kpir.model.*;
import pl.kpir.kpir.kpir.repositories.CompanyEntityRepository;
import pl.kpir.kpir.kpir.repositories.ContractorEntityRepository;

import java.util.List;
import java.util.Optional;
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

    public ContractorDTO convertToContractorDTO(ContractorEntity contractorEntity) {
        return ContractorDTO.builder()
                .id(contractorEntity.getId())
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

    public void deleteById(Long id) {
        contractorEntityRepository.deleteById(id);
    }

    public ContractorDTO findById(Long id) {
        return convertToContractorDTO(contractorEntityRepository.findById(id).orElse(null));
    }
    
    public void editContractor(EditContractorForm editContractorForm) {
        Address address = new Address();
        address.setStreet(editContractorForm.getStreet());
        address.setPostCode(editContractorForm.getPostCode());
        address.setCity(editContractorForm.getCity());
        address.setCountry(editContractorForm.getCountry());
        ContractorEntity contractorEntity = new ContractorEntity();
        contractorEntity.setId(editContractorForm.getId());
        contractorEntity.setShortContractorName(editContractorForm.getShortCompanyName());
        contractorEntity.setFullContractorName(editContractorForm.getFullCompanyName());
        contractorEntity.setNip(editContractorForm.getNip());
        contractorEntity.setRegon(editContractorForm.getRegon());
        contractorEntity.setAddress(address);
        contractorEntity.setTelephoneNumber(editContractorForm.getTelephoneNumber());
        contractorEntity.setEmail(editContractorForm.getEmail());
        CompanyEntity companyByUserId = companyEntityRepository.findByUserId(userUtils.getLoggedInUserId()).get(0);
        contractorEntity.setCompanyId(companyByUserId);
        contractorEntityRepository.save(contractorEntity);
    }

    public boolean validateEntry(Long id) {
        Long loggedInUserId = userUtils.getLoggedInUserId();

        Optional<Long> loggedCompanyId = companyEntityRepository.findByUserId(loggedInUserId)
                .stream()
                .map(CompanyEntity::getId)
                .findFirst();

        return contractorEntityRepository.findById(id)
                .map(ContractorEntity::getCompanyId)
                .map(CompanyEntity::getId)
                .filter(companyId -> loggedCompanyId.get().equals(companyId))
                .isPresent();
    }
}




