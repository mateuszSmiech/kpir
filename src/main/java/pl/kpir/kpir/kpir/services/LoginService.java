package pl.kpir.kpir.kpir.services;

import org.springframework.stereotype.Service;
import pl.kpir.kpir.kpir.forms.LoginForm;
import pl.kpir.kpir.kpir.model.UserEntity;
import pl.kpir.kpir.kpir.repositories.LoginRepository;

@Service
public class LoginService {

    private final LoginRepository loginRepository;
    private String validationMessage;


    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

//    public boolean logIn(LoginForm loginForm) {
//        UserEntity userEntity = loginRepository.findUserByEmail(loginForm.getEmail());
//        if (userValidation(userEntity, loginForm)) {
//            return false;
//        }
//        return true;
//    }

//    private boolean userValidation(UserEntity userEntity, LoginForm loginForm) {
//        if (!loginForm.getEmail().equals("") && !loginForm.getPassword().equals("")) {
//            if (userEntity.getEmail().equals(loginForm.getEmail()) && userEntity.getPassword().equals(loginForm.getPassword())) {
//                return true;
//            } else {
//                validationMessage = "Pole email lub hasło jest błędne.";
//                return false;
//            }
//        } else {
//            validationMessage = "Pole email lub hasło jest puste.";
//            return false;
//        }
//    }
}
