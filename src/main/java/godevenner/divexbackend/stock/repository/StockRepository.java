package godevenner.divexbackend.stock.repository;

import godevenner.divexbackend.stock.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Integer> {

    Optional<Stock> findByTicker(String ticker);
}
