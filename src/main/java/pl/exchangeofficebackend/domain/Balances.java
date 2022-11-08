package pl.exchangeofficebackend.domain;

import lombok.*;
import javax.persistence.*;

@Entity(name = "BALANCES")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Balances {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BALANCES_ID")
    private Long id;

    @Column(name = "USERS_ID")  /* Obiekt */
    private String userID;

    @Column(name = "BALANCES_CURRENCY") /* Obiekt */
    private String balancesCurrency;

    @Column(name = "BALANCES_QUANTITY")
    private String quantity;

}