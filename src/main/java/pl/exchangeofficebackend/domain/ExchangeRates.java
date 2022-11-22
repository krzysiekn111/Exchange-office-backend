package pl.exchangeofficebackend.domain;

import lombok.*;
import javax.persistence.*;

@Entity(name = "EXCHANGE_RATES")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExchangeRates {

    /*  uzupełniane za pomocą Schedulera  */
    /*  Wykorzystywane do wyświetlania danych o kursach walut w tabeli generowanej za pomocą fronendu w Vaadin*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CURRENCY_ID")
    private Currency currency;

    @Column(name = "EXCHANGE_RATE_TO_PLN")
    private Float exchangeRateToPLN;

    @Column(name = "CURRENCY_NAME")
    private String currencyName;

    public ExchangeRates(Long id, Currency currency, Float exchangeRateToPLN) {
        this.id = id;
        this.currency = currency;
        this.exchangeRateToPLN = exchangeRateToPLN;
        this.currencyName = this.currency.getName();
    }
}
