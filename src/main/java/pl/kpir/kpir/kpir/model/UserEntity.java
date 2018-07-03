package pl.kpir.kpir.kpir.model;


import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
@AllArgsConstructor
@Builder

public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String password;
    @Column (unique = true, nullable = false)
    private String email;
    @Column(name="birth_date")
    private Date birthDate;


}
