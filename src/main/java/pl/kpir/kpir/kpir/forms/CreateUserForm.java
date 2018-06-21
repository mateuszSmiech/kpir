package pl.kpir.kpir.kpir.forms;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CreateUserForm {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String birthDate;
}
