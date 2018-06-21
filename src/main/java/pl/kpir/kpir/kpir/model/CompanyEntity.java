package pl.kpir.kpir.kpir.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;


@Entity
@NoArgsConstructor
@Getter
@Setter

public class CompanyEntity {

private String shortCompanyName;
private String fullCompanyName;
private String nip;
@Embedded
    private Address address;

}
