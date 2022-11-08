package pl.exchangeofficebackend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.exchangeofficebackend.domain.ExchangeRates;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface ExchangeRatesRepository extends CrudRepository<ExchangeRates, Long> {
}
