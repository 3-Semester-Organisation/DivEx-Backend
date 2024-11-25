package godevenner.divexbackend.portfolio;

import godevenner.divexbackend.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Portfolio {

    @Id
    @GeneratedValue
    private long id;
    private String name;

    @ManyToOne
    private User user;
}
