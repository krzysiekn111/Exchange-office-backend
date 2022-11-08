package pl.exchangeofficebackend.domain;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "HISTORY")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HISTORY_ID")
    private Long id;

    @Column(name = "HISTORY_CURRENCY_BOUGHT")  /* Obiekt */
    private String currencyBought;

    @Column(name = "HISTORY_CURRENCY_SOLD")  /* Obiekt */
    private String currencySold;

    @Column(name = "HISTORY_QUANTITY_BOUGHT")
    private int boughtQuantity;

    @Column(name = "HISTORY_QUANTITY_SOLD")
    private int soldQuantity;

    @Column(name = "HISTORY_DATE")
    private LocalDateTime date;

    @Column(name = "HISTORY_EXCHANGE_RATE")
    private Float exchangeRate;
}
