package pl.exchangeofficebackend.domain;

import lombok.*;
import javax.persistence.*;
import java.util.List;


@Entity(name = "USER")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    public User(Long id, String userName, String login, String password) {
        this.id = id;
        this.userName = userName;
        this.login = login;
        this.password = password;
    }

    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "PASSWORD")
    private String password;

    @OneToMany(
            targetEntity = Balances.class,
            mappedBy = "user",
            fetch = FetchType.LAZY
    )
    private List<Balances> balances;

}
