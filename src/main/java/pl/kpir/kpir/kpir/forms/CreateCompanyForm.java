package pl.kpir.kpir.kpir.forms;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CreateCompanyForm {
    private String shortCompanyName;
    private String fullCompanyName;
    private String nip;
    private String regon;
    private String street;
    private String postCode;
    private String city;
    private String country;
    private String telephoneNumber;
    private String email;
    private boolean vat;
    private String taxForm;
}
