package pl.exchangeofficebackend.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "BALANCES")
//@AllArgsConstructor
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

    @Column(name = "CURRENCY_NAME")
    private String currencyName;

    public Balances(Long id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Balances(Long id, User user, Currency currency, int quantity) {
        this.id = id;
        this.user = user;
        this.currency = currency;
        this.quantity = quantity;
        if (currency != null) {
            this.currencyName = currency.getName();
        } else this.currencyName = "currencyName";
    }
}
