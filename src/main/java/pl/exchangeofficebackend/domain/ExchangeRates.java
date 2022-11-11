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
    @JoinColumn(name = "LEFT_CURRENCY_ID")
    private Currency leftCurrency;

    @ManyToOne
    @JoinColumn(name = "RIGHT_CURRENCY_ID")
    private Currency rightCurrency;

    @Column(name = "EXCHANGE_RATE")
    private Float exchangeRate;
}
