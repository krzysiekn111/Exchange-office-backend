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
    @Column(name = "EXCHANGE_RATES_ID")
    private Long id;

    @Column(name = "EXCHANGE_RATES_LEFT_CURRENCY")  /* Obiekt */
    private String leftCurrency;

    @Column(name = "EXCHANGE_RATES_RIGHT_CURRENCY")  /* Obiekt */
    private String rightCurrency;

    @Column(name = "EXCHANGE_RATES_EXCHANGE_RATE")
    private Float exchangeRate;
}
