package godevenner.divexbackend.portfolio.service;

import godevenner.divexbackend.portfolio.dto.DeletePortfolioEntryRequest;
import godevenner.divexbackend.portfolio.dto.PortfolioEntryRequest;
import godevenner.divexbackend.portfolio.dto.PortfolioEntryResponse;
import godevenner.divexbackend.portfolio.mapper.PortfolioEntryMapper;
import godevenner.divexbackend.portfolio.model.PortfolioEntry;
import godevenner.divexbackend.portfolio.repository.PortfolioEntryRepository;
import godevenner.divexbackend.stock.service.StockService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PortfolioEntryServiceImpl implements PortfolioEntryService {

    private final PortfolioEntryRepository portfolioEntryRepository;
    private final PortfolioEntryMapper portfolioEntryMapper;
    private final StockService stockService;

    @Override
    public PortfolioEntryResponse createPortfolioEntry(PortfolioEntryRequest request) {
        PortfolioEntry portfolioEntry = portfolioEntryMapper.toPortfolioEntry(request);
        PortfolioEntry savedRequest = portfolioEntryRepository.save(portfolioEntry);
        PortfolioEntryResponse response = portfolioEntryMapper.toPortfolioEntryResponse(savedRequest);
        return response;
    }

    @Override
    @Transactional //this is necessary else it complains
    public void deletePortfolioEntry(DeletePortfolioEntryRequest request) {

        portfolioEntryRepository.deletePortfolioEntry(request.portfolioEntryId());
    }
}
