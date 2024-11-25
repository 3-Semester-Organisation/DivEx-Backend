package godevenner.divexbackend.stock.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String ticker;
    private String name;
    private String country;
    private String exchange;
    private Currency currency;
    private String industry;
    private String sector;

    @OneToOne
    private Dividend dividend;

    @OneToMany
    @JoinColumn(name = "stock_id")
    Set<HistoricalPricing> historicalPricingSet = new HashSet<HistoricalPricing>();

}
