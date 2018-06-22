package pl.kpir.kpir.kpir.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;



@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class Address {
    @Column (nullable = false)
    private String street;
    @Column (nullable = false)
    private String postCode;
    @Column (nullable = false)
    private String city;
    private String country;
}
