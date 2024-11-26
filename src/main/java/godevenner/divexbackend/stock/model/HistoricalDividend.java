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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double dividend;
    private long exDividendDate;
}
