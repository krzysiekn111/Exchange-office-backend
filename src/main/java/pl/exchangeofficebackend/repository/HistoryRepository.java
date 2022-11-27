package pl.exchangeofficebackend.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.exchangeofficebackend.domain.History;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface HistoryRepository extends CrudRepository<History, Long> {

//    @Query
//    public void callFillUpHistory();

    @Override
    public List<History> findAll();
}
