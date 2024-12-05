package godevenner.divexbackend.portfolio.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import godevenner.divexbackend.stock.model.Stock;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
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
