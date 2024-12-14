package godevenner.divexbackend.portfolio.repository;

import godevenner.divexbackend.portfolio.model.PortfolioEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioEntryRepository extends JpaRepository<PortfolioEntry, Long> {

    List<PortfolioEntry> findByPortfolioId(Long portfolioId);
    PortfolioEntry findByStockIdAndPortfolioId(Long stockId, Long portfolioId);

    @Modifying
    @Query(value = "DELETE FROM PortfolioEntry WHERE id = :entryId")
    void deletePortfolioEntry(long entryId);

    @Modifying
    @Query(value = "DELETE FROM Portfolio_Entry WHERE stock_id = :stockId" +
            " AND portfolio_id = :portfolioId", nativeQuery = true) //native might not be necessary
    void deleteByStockIdAndPortfolioId(
            @Param("stockId")long stockId,
            @Param("portfolioId") long portfolioId
    );
    //@param is probably not necessary here either, but I am scared to touch it since it works

}
