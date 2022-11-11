package pl.exchangeofficebackend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.exchangeofficebackend.domain.ExchangeRates;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface ExchangeRatesRepository extends CrudRepository<ExchangeRates, Long> {

    @Override
    public List<ExchangeRates> findAll();
}
