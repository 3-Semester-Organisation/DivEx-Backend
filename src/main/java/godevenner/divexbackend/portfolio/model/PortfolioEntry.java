package godevenner.divexbackend.portfolio.model;

import godevenner.divexbackend.stock.model.Stock;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioEntry {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Portfolio portfolio;

    @ManyToOne
    private Stock stock;
}
