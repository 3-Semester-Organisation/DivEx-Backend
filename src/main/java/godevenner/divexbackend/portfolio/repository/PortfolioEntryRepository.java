package godevenner.divexbackend.portfolio.repository;

import godevenner.divexbackend.portfolio.model.PortfolioEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioEntryRepository extends JpaRepository<PortfolioEntry, Integer> {

    List<PortfolioEntry> findByPortfolioId(Long portfolioId);
}
