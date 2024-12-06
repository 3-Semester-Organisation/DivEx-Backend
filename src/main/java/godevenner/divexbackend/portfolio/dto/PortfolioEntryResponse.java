package godevenner.divexbackend.portfolio.dto;

import godevenner.divexbackend.stock.dto.StockResponse;

public record PortfolioEntryResponse(
        StockResponse stock,
        double stockPrice,
        int quantity,
        long entryDate
) {
}
