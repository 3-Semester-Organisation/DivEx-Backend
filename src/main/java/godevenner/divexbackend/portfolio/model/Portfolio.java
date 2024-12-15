package godevenner.divexbackend.portfolio.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import godevenner.divexbackend.user.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int goal;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "portfolio_id")
    private List<PortfolioEntry> portfolioEntries;

    @ManyToOne()
    private User user;
}
