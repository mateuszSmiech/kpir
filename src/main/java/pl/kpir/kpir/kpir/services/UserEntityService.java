package pl.kpir.kpir.kpir.services;

import org.springframework.stereotype.Service;
import pl.kpir.kpir.kpir.forms.CreateUserForm;
import pl.kpir.kpir.kpir.model.UserEntity;
import pl.kpir.kpir.kpir.repositories.UserEntityRepository;

import java.time.LocalDate;

@Service
public class UserEntityService {
    private final UserEntityRepository userEntityRepository;

    public UserEntityService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    public void saveUser(CreateUserForm userForm) {
        UserEntity user = convertToUserEntity(userForm);
        userEntityRepository.save(user);
    }

    private UserEntity convertToUserEntity(CreateUserForm userForm) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(userForm.getFirstName());
        userEntity.setLastName(userForm.getLastName());
        userEntity.setPassword(userForm.getPassword());
        userEntity.setEmail(userForm.getEmail());
        LocalDate birthDate = LocalDate.parse(userForm.getBirthDate());
        userEntity.setBirthDate(birthDate);

        return userEntity;
    }

}
