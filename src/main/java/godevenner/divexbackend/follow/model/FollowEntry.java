package godevenner.divexbackend.follow.model;

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
public class FollowEntry {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private FollowList followList;

    @ManyToOne
    private Stock stock;
}
