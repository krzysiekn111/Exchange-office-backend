package pl.exchangeofficebackend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.exchangeofficebackend.domain.Currency;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Long> {
}
