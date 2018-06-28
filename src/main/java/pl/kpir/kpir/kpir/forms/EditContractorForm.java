package pl.kpir.kpir.kpir.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EditContractorForm {

    private Long id;
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
}
