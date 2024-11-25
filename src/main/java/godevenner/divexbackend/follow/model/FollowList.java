package godevenner.divexbackend.follow.model;

import godevenner.divexbackend.user.User;
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
public class FollowList {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @ManyToOne
    private User user;
}
