package godevenner.divexbackend.stock.repository;

import godevenner.divexbackend.stock.model.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Integer> {

    Optional<Stock> findByTicker(String ticker);

    @Query("SELECT s FROM Stock s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(s.ticker) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Stock> findByNameOrTicker(String searchTerm);
    Page<Stock> findByTickerContainingIgnoreCase(String ticker, Pageable pageable);

    Page<Stock> findAllByDividend_ExDividendDate(Long date, Pageable pageable);
}
