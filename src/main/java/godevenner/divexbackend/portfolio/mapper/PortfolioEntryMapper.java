package godevenner.divexbackend.portfolio.mapper;

import godevenner.divexbackend.portfolio.dto.PortfolioEntryRequest;
import godevenner.divexbackend.portfolio.dto.PortfolioEntryResponse;
import godevenner.divexbackend.portfolio.model.Portfolio;
import godevenner.divexbackend.portfolio.model.PortfolioEntry;
import godevenner.divexbackend.portfolio.repository.PortfolioRepository;
import godevenner.divexbackend.stock.dto.StockResponse;
import godevenner.divexbackend.stock.mapper.StockMapper;
import godevenner.divexbackend.stock.model.Stock;
import godevenner.divexbackend.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PortfolioEntryMapper {

    private final PortfolioRepository portfolioRepository;
    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    public PortfolioEntry toPortfolioEntry(PortfolioEntryRequest request) {
        System.out.println("2@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + request.portfolioId());
        Portfolio portfolio = portfolioRepository.findById(request.portfolioId()).get();
        Stock stock = stockRepository.findByTicker(request.ticker()).get();

        return PortfolioEntry.builder()
                .stockPrice(request.stockPrice())
                .quantity(request.quantity())
                .entryDate(System.currentTimeMillis()/1000)
                .portfolio(portfolio)
                .stock(stock)
                .build();
    }

    public PortfolioEntryResponse toPortfolioEntryResponse(PortfolioEntry response) {
        StockResponse stockResponse = stockMapper.toStockResponse(response.getStock());

        return new PortfolioEntryResponse(stockResponse, response.getStockPrice(), response.getQuantity(), response.getEntryDate(), response.getId());
    }
}
