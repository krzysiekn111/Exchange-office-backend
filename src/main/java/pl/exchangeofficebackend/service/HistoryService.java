package pl.exchangeofficebackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.exchangeofficebackend.domain.ExchangeRates;
import pl.exchangeofficebackend.domain.History;
import pl.exchangeofficebackend.repository.HistoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private HistoryRepository historyRepository;

    @Autowired
    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public List<History> findHistories() {
        return historyRepository.findAll();
    }

    public History findHistoryById(Long id) throws Exception {
        return historyRepository.findById(id).orElseThrow(Exception::new);
    }

    public History saveHistory(History history) {
        return historyRepository.save(history);
    }

    public void deleteHistory(Long id) {
        historyRepository.deleteById(id);
    }
}
