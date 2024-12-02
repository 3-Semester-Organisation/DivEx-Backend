package godevenner.divexbackend.stock.dto;

import godevenner.divexbackend.stock.model.AccessType;

public record PopularityResponse(
    long accessDate,
    long stockId,
    long userId,
    AccessType accessType
){
}
