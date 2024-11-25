package godevenner.divexbackend.stock.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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

    private double previousDailyClosingPrice;
    private LocalDate closingDate;

    @ManyToOne
    private Stock stock;
}
