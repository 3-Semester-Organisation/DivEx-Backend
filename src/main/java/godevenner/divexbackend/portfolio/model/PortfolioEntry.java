package godevenner.divexbackend.portfolio.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import godevenner.divexbackend.stock.model.Stock;
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
public class PortfolioEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int stockPrice;
    private int quantity;
    private long entryDate;

    @ManyToOne
    @JsonBackReference
    private Portfolio portfolio;

    @ManyToOne
    private Stock stock;
}
