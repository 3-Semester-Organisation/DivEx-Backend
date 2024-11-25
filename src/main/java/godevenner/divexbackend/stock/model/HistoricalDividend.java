package godevenner.divexbackend.stock.model;

import jakarta.persistence.*;
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
}
