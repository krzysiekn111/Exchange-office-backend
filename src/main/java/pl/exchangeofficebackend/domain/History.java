package pl.exchangeofficebackend.domain;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

//@NamedNativeQuery(
//        name = "History.callFillUpHistory",
//        query = "call fillUpHistory()",
//        resultClass = History.class
//)

@Entity(name = "HISTORY")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class History {

    /*    uzupełniane za pomocą procedury   */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BOUGHT_CURRENCY_ID")
    private Currency currencyBought;

    @ManyToOne
    @JoinColumn(name = "SOLD_CURRENCY_ID")
    private Currency currencySold;

    @Column(name = "QUANTITY_BOUGHT")
    private int boughtQuantity;

    @Column(name = "QUANTITY_SOLD")
    private int soldQuantity;

    @Column(name = "DATE")
    private LocalDateTime date;

    @Column(name = "EXCHANGE_RATE")
    private Float exchangeRate;

    public History(Long id, Currency currencyBought, Currency currencySold, int boughtQuantity, int soldQuantity, Float exchangeRate) {
        this.id = id;
        this.currencyBought = currencyBought;
        this.currencySold = currencySold;
        this.boughtQuantity = boughtQuantity;
        this.soldQuantity = soldQuantity;
        this.exchangeRate = exchangeRate;
    }
}
