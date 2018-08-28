package pl.kpir.kpir.kpir.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.kpir.kpir.kpir.forms.CreateUserForm;
import pl.kpir.kpir.kpir.model.CustomUser;
import pl.kpir.kpir.kpir.model.UserEntity;
import pl.kpir.kpir.kpir.repositories.UserEntityRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class UserEntityService implements UserDetailsService {

    private final UserEntityRepository userEntityRepository;

    public UserEntityService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    public void saveUser(CreateUserForm userForm) {
        UserEntity user = convertToUserEntity(userForm);
        userEntityRepository.save(user);
    }

    private UserEntity convertToUserEntity(CreateUserForm userForm) {
        return UserEntity.builder()
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .password(userForm.getPassword())
                .email(userForm.getEmail())
                .birthDate(LocalDate.parse(userForm.getBirthDate()))
                .build();
    }

    @Override
    public CustomUser loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userEntityRepository.findUserByEmail(email);
        List<GrantedAuthority> authorities = Collections.EMPTY_LIST;
        if (user == null) {
            throw new UsernameNotFoundException("Unknown User");
        }
        return new CustomUser(user, true, true, true, true, authorities);
    }


}
