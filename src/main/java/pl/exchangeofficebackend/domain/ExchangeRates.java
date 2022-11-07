package pl.exchangeofficebackend.domain;

import lombok.*;
import javax.persistence.*;

@Entity(name = "EXCHANGE_RATES")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExchangeRates {

    @Id
    @GeneratedValue
    @Column(name = "EXCHANGE_RATES_ID")
    private Long id;

    @Column(name = "EXCHANGE_RATES_LEFT_CURRENCY")  /* Obiekt */
    private String leftCurrency;

    @Column(name = "EXCHANGE_RATES_RIGHT_CURRENCY")  /* Obiekt */
    private String rightCurrency;

    @Column(name = "EXCHANGE_RATES_EXCHANGE_RATE")
    private Float exchangeRate;
}
