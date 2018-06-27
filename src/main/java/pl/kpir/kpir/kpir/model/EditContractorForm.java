package pl.kpir.kpir.kpir.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EditContractorForm {

    private String shortContractorName;
    private String fullContractorName;
    private String nip;
    private String regon;
    private Address address;
    private String telephoneNumber;
    private String email;
}
