package pl.exchangeofficebackend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.exchangeofficebackend.domain.Balances;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface BalancesRepository extends CrudRepository<Balances, Long> {

    @Override
    public List<Balances> findAll();
}
