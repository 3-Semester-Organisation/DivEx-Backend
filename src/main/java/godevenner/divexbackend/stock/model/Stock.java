package godevenner.divexbackend.stock.model;

import godevenner.divexbackend.stock.model.dividend.Dividend;
import godevenner.divexbackend.stock.model.historicaldividend.HistoricalDividend;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



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

    @OneToOne
    private Dividend dividend;
}
