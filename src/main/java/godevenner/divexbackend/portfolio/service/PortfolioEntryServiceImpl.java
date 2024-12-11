package godevenner.divexbackend.portfolio.service;

import godevenner.divexbackend.portfolio.dto.DeleteRequest;
import godevenner.divexbackend.portfolio.dto.PortfolioEntryRequest;
import godevenner.divexbackend.portfolio.dto.PortfolioEntryResponse;
import godevenner.divexbackend.portfolio.mapper.PortfolioEntryMapper;
import godevenner.divexbackend.portfolio.model.PortfolioEntry;
import godevenner.divexbackend.portfolio.repository.PortfolioEntryRepository;
import godevenner.divexbackend.stock.dto.StockResponse;
import godevenner.divexbackend.stock.model.Stock;
import godevenner.divexbackend.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void deletePortfolioEntry(DeleteRequest request) {
        StockResponse stock = stockService.getStockByTicker(request.ticker());
        PortfolioEntry entry = portfolioEntryRepository.findByStockIdAndPortfolioId(
                stock.stockId(), request.portfolioId());

        portfolioEntryRepository.deletePortfolioEntryById(entry.getId());
    }
}
