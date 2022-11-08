package pl.exchangeofficebackend.domain;


import lombok.*;
import javax.persistence.*;

@Entity(name = "CURRENCY")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CURRENCY_ID")
    private Long id;

    @Column(name = "CURRENCY_SYMBOL")
    private String symbol;

    @Column(name = "CURRENCY_NAME")
    private String name;
}
