package pl.kpir.kpir.kpir.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.kpir.kpir.kpir.model.CompanyEntity;
import pl.kpir.kpir.kpir.model.CustomUser;
import pl.kpir.kpir.kpir.repositories.CompanyEntityRepository;

import java.util.Optional;

@Service
public class UserUtils {
    private final CompanyEntityRepository companyEntityRepository;

    public UserUtils(CompanyEntityRepository companyEntityRepository) {
        this.companyEntityRepository = companyEntityRepository;
    }

    public Long getLoggedInUserId() {
        CustomUser principal = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getUserEntity().getId();

    }

    public Long getLoggedInCompany() {
        return companyEntityRepository.findByUserId(getLoggedInUserId()).stream().map(CompanyEntity::getId).findFirst().orElse(null);
    }
}
