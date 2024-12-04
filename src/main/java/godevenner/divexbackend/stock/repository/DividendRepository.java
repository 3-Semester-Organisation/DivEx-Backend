package godevenner.divexbackend.stock.repository;

import godevenner.divexbackend.stock.model.Dividend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DividendRepository extends JpaRepository<Dividend, Long> {


}
