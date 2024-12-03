package godevenner.divexbackend.stock.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


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

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private String industry;
    private String sector;

    @OneToOne(fetch = FetchType.EAGER)
    private Dividend dividend;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "stock_id")
    private List<HistoricalDividend> historicalDividends;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "stock_id")
    private List<HistoricalPricing> historicalPricings;


}
