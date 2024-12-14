package godevenner.divexbackend.portfolio.dto;

import java.util.List;

public record PortfolioResponse(
        long id,
        String name,
        int goal,
        List<PortfolioEntryResponse> portfolioEntries
) {
}
