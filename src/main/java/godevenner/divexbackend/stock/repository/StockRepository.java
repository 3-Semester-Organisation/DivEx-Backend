package godevenner.divexbackend.stock.repository;

import godevenner.divexbackend.stock.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Integer> {
}
