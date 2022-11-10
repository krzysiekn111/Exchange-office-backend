package pl.exchangeofficebackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDto {

    private Long id;
    private String symbol;
    private String name;
}
