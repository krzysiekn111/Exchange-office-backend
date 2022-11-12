package pl.exchangeofficebackend.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.exchangeofficebackend.domain.History;
import pl.exchangeofficebackend.domain.dto.HistoryDto;
import pl.exchangeofficebackend.service.CurrencyService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryMapper {

    @Autowired
    private CurrencyService currencyService;

    public History mapToHistory(HistoryDto historyDto) throws Exception {
        return new History(historyDto.getId(),
                currencyService.findCurrencyById(historyDto.getCurrencyBoughtID()),
                currencyService.findCurrencyById(historyDto.getCurrencySoldId()),
                historyDto.getBoughtQuantity(),
                historyDto.getSoldQuantity(),
                historyDto.getDate(),
                historyDto.getExchangeRate());
    }

    public HistoryDto mapToHistoryDto(History history) {
        return new HistoryDto(
                history.getId(),
                history.getCurrencyBought().getId(),
                history.getCurrencySold().getId(),
                history.getBoughtQuantity(),
                history.getSoldQuantity(),
                history.getDate(),
                history.getExchangeRate()
        );
    }

    public List<History> mapToHistoryList(List<HistoryDto> historyDtos) {
        return historyDtos.stream()
                .map(a -> {
                    try {
                        return mapToHistory(a);
                    } catch (Exception e) {
                        return null;
                    }
                })
                .collect(Collectors.toList());
    }

    public List<HistoryDto> mapToHistoryDtoList(List<History> historyDtos) {
        return historyDtos.stream()
                .map(this::mapToHistoryDto)
                .collect(Collectors.toList());
    }
}
