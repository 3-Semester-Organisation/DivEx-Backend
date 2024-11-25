package godevenner.divexbackend.stock.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

    @Id
    @GeneratedValue
    private long id;

    private String ticker;
    private String name;
    private String country;
    private String exchange;
    private Currency currency;
    private String industry;
    private String sector;

    @OneToOne(fetch = FetchType.EAGER)
    private Dividend dividend;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "stock_id")
    private Set<HistoricalDividend> historicalDividends;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "stock_id")
    private Set<HistoricalPricing> historicalPricings;
}
