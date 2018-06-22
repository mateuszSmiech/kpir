package pl.kpir.kpir.kpir.services;


import org.springframework.stereotype.Service;
import pl.kpir.kpir.kpir.forms.CreateCompanyForm;
import pl.kpir.kpir.kpir.forms.CreateUserForm;
import pl.kpir.kpir.kpir.model.Address;
import pl.kpir.kpir.kpir.model.CompanyEntity;
import pl.kpir.kpir.kpir.model.UserEntity;
import pl.kpir.kpir.kpir.repositories.CompanyEntityRepository;

@Service
public class CompanyEntityService {

    private final CompanyEntityRepository companyEntityRepository;


    public CompanyEntityService(CompanyEntityRepository companyEntityRepository) {
        this.companyEntityRepository = companyEntityRepository;
    }


    public void saveCompany(CreateCompanyForm companyForm) {
        CompanyEntity company = convertToCompanyEntity(companyForm);
        companyEntityRepository.save(company);
    }

    private CompanyEntity convertToCompanyEntity(CreateCompanyForm companyForm) {
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

        return companyEntity;
    }
}
