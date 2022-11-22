package pl.exchangeofficebackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRatesDto {

    private Long id;
    private Long currencyId;
    private Float exchangeRateToPLN;
    private String currencyName;
}
