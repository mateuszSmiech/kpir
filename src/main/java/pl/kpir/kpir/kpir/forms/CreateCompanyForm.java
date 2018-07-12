package pl.kpir.kpir.kpir.forms;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.pl.NIP;
import org.hibernate.validator.constraints.pl.REGON;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class CreateCompanyForm {
    @NotEmpty(message = "{validation.shortName.NotEmpty}")
    private String shortCompanyName;
    @NotEmpty(message = "{validation.fullName.NotEmpty}")
    private String fullCompanyName;
    @NotEmpty(message = "{validation.nip.NotEmpty}")
    @NIP(message = "{validation.nip.format}")
    private String nip;
    @REGON(message = "{validation.regon.format}")
    private String regon;
    @NotEmpty(message = "{validation.street.NotEmpty}")
    private String street;
    @NotEmpty(message = "{validation.postCode.NotEmpty}")
    @Pattern(regexp = "\\d{2}-\\d{3}", message = "{validation.postCode.pattern}")
    private String postCode;
    @NotEmpty(message = "{validatino.city.NotEmpty}")
    private String city;
    private String country;
    private String telephoneNumber;
    @Email(message = "{validation.email.wrongFormat}")
    private String email;
    private boolean vat;
    private String taxForm;
}
