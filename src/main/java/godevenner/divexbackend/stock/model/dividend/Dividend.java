package godevenner.divexbackend.stock.model.dividend;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
public class Dividend {

    @Id
    @GeneratedValue
    private long id;
    private double dividendRate;
    private double dividendYield;
    private double dividendRatio;
    private double fiveYearAvgDividendYield;
    private LocalDate exDividendDate;
}
