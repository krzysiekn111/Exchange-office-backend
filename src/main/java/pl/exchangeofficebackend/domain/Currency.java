package pl.exchangeofficebackend.domain;


import lombok.*;
import javax.persistence.*;

@Entity(name = "CURRENCY")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Currency {

    @Id
    @GeneratedValue
    @Column(name = "CURRENCY_ID")
    private Long id;

    @Column(name = "CURRENCY_NAME")
    private String name;
}
