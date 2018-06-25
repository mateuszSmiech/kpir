package pl.kpir.kpir.kpir.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "contractors")
public class ContractorEntity {

    //TODO add company id to contractor
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private UserEntity companyId;
    @Column(name = "short_con_name", unique = true, nullable = false)
    private String shortContractorName;
    @Column(name = "full_con_name", unique = true, nullable = false)
    private String fullContractorName;
    @Column(unique = true, nullable = false)
    private String nip;
    @Column(unique = true)
    private String regon;
    @Embedded
    private Address address;
    @Column(name = "tel_number")
    private String telephoneNumber;
    private String email;
}
