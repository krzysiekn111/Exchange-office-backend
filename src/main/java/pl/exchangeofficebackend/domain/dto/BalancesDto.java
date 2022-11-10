package pl.exchangeofficebackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BalancesDto {

    private Long id;
    private Long userId;
    private Long CurrencyId;
    private int quantity;

}
