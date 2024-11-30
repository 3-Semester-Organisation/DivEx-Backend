package godevenner.divexbackend.portfolio.model.service;

import godevenner.divexbackend.portfolio.model.Portfolio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PortfolioService {

    List<Portfolio> getPortfolios(Integer userId);


}
