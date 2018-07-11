package pl.kpir.kpir.kpir.forms;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class CreateUserForm {

    @NotEmpty(message = "{validation.firstName.notEmpty}")
    private String firstName;
    @NotEmpty(message = "{validation.lastName.notEmpty}")
    private String lastName;
    @NotEmpty (message = "{validation.email.notEmpty}")
    @Email (message = "{validation.email.wrongFormat}")
    private String email;
    @NotEmpty(message = "{validation.password.notEmpty}")
    @Size(min=5, max = 15, message = "{validation.password.length}")
    private String password;
    @NotEmpty(message = "{validation.birthDate.notEmpty}")
    @Pattern(regexp = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))", message = "{validation.date.wrongFormat}")
    private String birthDate;
}
