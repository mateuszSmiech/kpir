package pl.kpir.kpir.kpir.services;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.kpir.kpir.kpir.forms.CreateCompanyForm;
import pl.kpir.kpir.kpir.model.Address;
import pl.kpir.kpir.kpir.model.CompanyEntity;
import pl.kpir.kpir.kpir.model.CustomUser;
import pl.kpir.kpir.kpir.model.UserEntity;
import pl.kpir.kpir.kpir.repositories.CompanyEntityRepository;
import pl.kpir.kpir.kpir.repositories.UserEntityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyEntityService {

    private final CompanyEntityRepository companyEntityRepository;
    private final UserEntityRepository userEntityRepository;


    public CompanyEntityService(CompanyEntityRepository companyEntityRepository, UserEntityRepository userEntityRepository) {
        this.companyEntityRepository = companyEntityRepository;
        this.userEntityRepository = userEntityRepository;
    }


    public void saveCompany(CreateCompanyForm companyForm) {
        CompanyEntity company = convertToCompanyEntity(companyForm);
        companyEntityRepository.save(company);
    }

    private CompanyEntity convertToCompanyEntity(CreateCompanyForm companyForm) {

        CustomUser principal = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = principal.getUserEntity().getId();
        Optional<UserEntity> byId = userEntityRepository.findById(id);
        UserEntity userEntity = byId.orElse(null);

        Address address = new Address();
        address.setStreet(companyForm.getStreet());
        address.setPostCode(companyForm.getPostCode());
        address.setCity(companyForm.getCity());
        address.setCountry(companyForm.getCountry());
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setShortCompanyName(companyForm.getShortCompanyName());
        companyEntity.setFullCompanyName(companyForm.getFullCompanyName());
        companyEntity.setNip(companyForm.getNip());
        companyEntity.setRegon(companyForm.getRegon());
        companyEntity.setAddress(address);
        companyEntity.setTelephoneNumber(companyForm.getTelephoneNumber());
        companyEntity.setEmail(companyForm.getEmail());
        companyEntity.setVat(companyForm.isVat());
        companyEntity.setTaxForm(companyForm.getTaxForm());
        companyEntity.setUserEntity(userEntity);

        return companyEntity;
    }

    public CompanyEntity findCompanyByUserId(Long id) {
        List<CompanyEntity> byUserId = companyEntityRepository.findByUserId(id);
        if(byUserId.size() == 0) {
            return null;
        } else {
            return byUserId.get(0);
        }
    }
}
