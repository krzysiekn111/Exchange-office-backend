package pl.exchangeofficebackend.domain;


import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity(name = "CURRENCY")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "SYMBOL")
    private String symbol;

    @Column(name = "NAME")
    private String name;

    @OneToMany(
            targetEntity = Balances.class,
            mappedBy = "currency",
            fetch = FetchType.LAZY
    )
    private List<Balances> balances;

    @OneToMany(
            targetEntity = History.class,
            mappedBy = "currencyBought",
            fetch = FetchType.LAZY
    )
    private List<History> currencyBought;

    @OneToMany(
            targetEntity = History.class,
            mappedBy = "currencySold",
            fetch = FetchType.LAZY
    )
    private List<History> currencySold;

    @OneToMany(
            targetEntity = ExchangeRates.class,
            mappedBy = "leftCurrency",
            fetch = FetchType.LAZY
    )
    private List<ExchangeRates> leftCurrency;

    @OneToMany(
            targetEntity = ExchangeRates.class,
            mappedBy = "rightCurrency",
            fetch = FetchType.LAZY
    )
    private List<ExchangeRates> rightCurrency;

    public Currency(Long id, String symbol, String name) {
        this.id = id;
        this.symbol = symbol;
        this.name = name;
    }
}
