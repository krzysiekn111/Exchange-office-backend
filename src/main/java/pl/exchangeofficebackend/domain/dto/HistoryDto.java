package pl.exchangeofficebackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HistoryDto {

    private Long id;
    private Long currencyBoughtID;
    private Long currencySoldId;
    private int boughtQuantity;
    private int soldQuantity;
    private LocalDateTime date;
    private Float exchangeRate;

}
