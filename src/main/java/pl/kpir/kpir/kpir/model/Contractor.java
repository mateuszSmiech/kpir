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
public class Contractor {

    //TODO add company id to contractor
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
    @Column(name = "short_com_name", unique = true, nullable = false)
    private String shortCompanyName;
    @Column(name = "full_com_name", unique = true, nullable = false)
    private String fullCompanyName;
    @Column(unique = true, nullable = false)
    private String nip;
    @Column(unique = true)
    private String regon;
    @Embedded
    private Address address;
    @Column(name = "tel_number")
    private String telephoneNumber;
    private String email;
    @Column(nullable = false)
    private boolean vat;
    private String taxForm;
}
