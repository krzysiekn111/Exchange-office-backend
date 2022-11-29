package pl.exchangeofficebackend.service;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.exchangeofficebackend.domain.ExchangeRates;
import pl.exchangeofficebackend.repository.ExchangeRatesRepository;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeRatesService {

    private ExchangeRatesRepository exchangeRatesRepository;
    private CurrencyService currencyService;

    @Autowired
    public ExchangeRatesService(ExchangeRatesRepository exchangeRatesRepository, CurrencyService currencyService) {
        this.exchangeRatesRepository = exchangeRatesRepository;
        this.currencyService = currencyService;
    }

    public List<ExchangeRates> findExchangeRates() {
        return exchangeRatesRepository.findAll();
    }

    public ExchangeRates findExchangeRateById(Long id) throws Exception {
        return exchangeRatesRepository.findById(id).orElseThrow(Exception::new);
    }

    public ExchangeRates saveExchangeRate(ExchangeRates exchangeRates) {
        return exchangeRatesRepository.save(exchangeRates);
    }

    @Scheduled(fixedRate = 5000)
    private void getCurrency() throws Exception {
        getConnection("chf", 1L);
        getConnection("usd", 2L);
        getConnection("eur", 3L);
        getConnection("gbp", 4L);

    }

    private void getConnection(String currencyURL, Long currencyID) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://api.nbp.pl/api/exchangerates/rates/a/" + currencyURL+ "/"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String jsonObject1 = new JSONObject(response.body()).toString();
        String[] s = jsonObject1.split(",\"effectiveDate");
        String[] sc = s[0].split("\"mid\":");
        Float exchangeRate = Float.valueOf(sc[1]);



        ExchangeRates exchangeRates = new ExchangeRates(
                currencyID,
                currencyService.findCurrencyById(currencyID),
                exchangeRate);
        saveExchangeRate(exchangeRates);
    }
}
