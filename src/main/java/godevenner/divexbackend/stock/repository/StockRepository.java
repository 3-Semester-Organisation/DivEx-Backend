package godevenner.divexbackend.stock.repository;

import godevenner.divexbackend.stock.model.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByTicker(String ticker);

    Page<Stock> findByTickerContainingIgnoreCase(String ticker, Pageable pageable);

    Page<Stock> findAllByDividend_ExDividendDate(Long date, Pageable pageable);
}
