package pl.exchangeofficebackend.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import pl.exchangeofficebackend.domain.History;
import pl.exchangeofficebackend.domain.dto.HistoryDto;
import pl.exchangeofficebackend.mapper.HistoryMapper;
import pl.exchangeofficebackend.service.HistoryService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HistoryControllerFacade {

    @Autowired
    private HistoryService historyService;
    @Autowired
    private HistoryMapper historyMapper;

    public List<HistoryDto> findAll() {
        return historyMapper.mapToHistoryDtoList(historyService.findHistories());
    }

    public HistoryDto findHistory(@PathVariable Long historyId) throws Exception {
        return historyMapper.mapToHistoryDto(historyService.findHistoryById(historyId));
    }

    public History saveHistory(@RequestBody HistoryDto historyDto) throws Exception {
        return historyService.saveHistory(historyMapper.mapToHistory(historyDto));
    }

    public void deleteHistory(@PathVariable long historyId) throws Exception {
        historyService.deleteHistory(historyId);
    }
}
