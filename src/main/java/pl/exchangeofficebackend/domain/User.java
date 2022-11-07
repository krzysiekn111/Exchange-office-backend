package pl.exchangeofficebackend.domain;

import lombok.*;
import javax.persistence.*;


@Entity(name = "USER")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "USER_USERNAME")
    private String userName;

    @Column(name = "USER_LOGIN")
    private String login;

    @Column(name = "USER_PASSWORD")
    private String password;

}
