package godevenner.divexbackend.portfolio.dto;

import godevenner.divexbackend.stock.dto.StockResponse;

public record PortfolioEntryResponse(
        StockResponse stock,
        int stockPrice,
        int quantity,
        long entryDate
) {
}
