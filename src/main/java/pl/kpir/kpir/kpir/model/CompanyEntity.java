package pl.kpir.kpir.kpir.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private UserEntity userEntity;
    @Column(name = "short_com_name")
    private String shortCompanyName;
    @Column(name = "full_com_name")
    private String fullCompanyName;
    private String nip;
    @Embedded
    private Address address;
}
