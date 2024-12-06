package godevenner.divexbackend.portfolio.mapper;

import godevenner.divexbackend.portfolio.dto.PortfolioResponse;
import godevenner.divexbackend.portfolio.model.Portfolio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PortfolioMapper {

    private final PortfolioEntryMapper portfolioEntryMapper;

    public PortfolioResponse toPortfolioResponse(Portfolio portfolio) {
        return new PortfolioResponse(
                portfolio.getId(),
                portfolio.getName(),
                portfolio.getPortfolioEntries().stream()
                        .map(portfolioEntryMapper::toPortfolioEntryResponse)
                        .toList());
    }
}
