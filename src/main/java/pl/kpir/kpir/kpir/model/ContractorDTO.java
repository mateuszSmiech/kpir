package pl.kpir.kpir.kpir.model;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ContractorDTO {

    private String shortContractorName;
    private String fullContractorName;
    private String nip;
    private String regon;
    private Address address;
    private String telephoneNumber;
    private String email;

}
