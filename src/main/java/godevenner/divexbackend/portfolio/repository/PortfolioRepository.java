package godevenner.divexbackend.portfolio.repository;

import godevenner.divexbackend.portfolio.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Integer> {

    List<Portfolio> findByUserId(Integer userId);

    Optional<Portfolio> findByName(String name);

}
