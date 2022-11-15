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
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "CURRENCY_ID")
    private Currency currency;

    @Column(name = "QUANTITY")
    private int quantity;

//    public Balances(Long id, int quantity) {
//        this.id = id;
//        this.quantity = quantity;
//    }
}
