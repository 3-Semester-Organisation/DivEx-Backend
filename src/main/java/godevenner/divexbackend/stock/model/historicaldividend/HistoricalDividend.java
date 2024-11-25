package godevenner.divexbackend.stock.model.historicaldividend;

import godevenner.divexbackend.stock.model.Stock;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoricalDividend {

    @Id
    @GeneratedValue
    private long id;

    private double dividendRate;
    private LocalDate exDividendDate;

    @ManyToOne
    private Stock stock;
}
