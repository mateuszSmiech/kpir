package pl.kpir.kpir.kpir.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import pl.kpir.kpir.kpir.model.CustomUser;
import pl.kpir.kpir.kpir.model.UserEntity;
import pl.kpir.kpir.kpir.services.CompanyEntityService;
import pl.kpir.kpir.kpir.services.UserEntityService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SuccessAuthHandler implements AuthenticationSuccessHandler {

    @Autowired
    private CompanyEntityService companyEntityService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        CustomUser principal = (CustomUser) authentication.getPrincipal();
        Long id = principal.getUserEntity().getId();
        if(companyEntityService.findCompanyByUserId(id) == null) {
            new DefaultRedirectStrategy().sendRedirect(request, response, "/company/registerCompany");
        } else {
            new DefaultRedirectStrategy().sendRedirect(request, response, "/dashboard");
        }

    }


}
