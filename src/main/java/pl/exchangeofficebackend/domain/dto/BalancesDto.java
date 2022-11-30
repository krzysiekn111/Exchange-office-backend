package pl.exchangeofficebackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BalancesDto {

    private Long id;
    private Long userId;
    private int quantity;
    private Long currencyId;


    public BalancesDto(Long id, Long userId, int quantity, Long currencyId) {
        this.id = id;
        this.userId = userId;
        this.quantity = quantity;
        this.currencyId = currencyId;
    }


    public BalancesDto(Long id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }
}
