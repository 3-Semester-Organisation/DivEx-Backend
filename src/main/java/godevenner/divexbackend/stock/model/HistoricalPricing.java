package godevenner.divexbackend.stock.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoricalPricing {

    @Id
    @GeneratedValue
    private long id;

    private double openingPrice;
    private LocalDate openingDate;
    private double previousDailyClosingPrice;
    private LocalDate closingDate;
}
