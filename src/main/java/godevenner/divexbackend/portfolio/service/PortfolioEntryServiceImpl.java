package godevenner.divexbackend.portfolio.service;

import godevenner.divexbackend.portfolio.model.PortfolioEntry;
import godevenner.divexbackend.portfolio.repository.PortfolioEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PortfolioEntryServiceImpl implements PortfolioEntryService {

    private final PortfolioEntryRepository portfolioEntryRepository;

    public List<PortfolioEntry> getPortfolioEntries(Long userId) {
        return portfolioEntryRepository.findByPortfolioId(userId);
    }

    public PortfolioEntry createPortfolioEntry(PortfolioEntry portfolioEntry) {
        return portfolioEntryRepository.save(portfolioEntry);
    }


}
