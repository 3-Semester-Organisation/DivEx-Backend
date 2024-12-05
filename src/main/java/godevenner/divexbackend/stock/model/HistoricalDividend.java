package godevenner.divexbackend.stock.model;

import jakarta.persistence.*;
import lombok.*;



@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoricalDividend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double dividend;
    private long exDividendDate;
}
