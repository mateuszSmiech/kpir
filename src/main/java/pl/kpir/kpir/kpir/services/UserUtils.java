package pl.kpir.kpir.kpir.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.kpir.kpir.kpir.model.CustomUser;

@Service
public class UserUtils {


    public Long getLoggedInUserId() {
        CustomUser principal = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = principal.getUserEntity().getId();
        return id;
    }
}
