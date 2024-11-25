package godevenner.divexbackend.subscription.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {

    @Id
    @GeneratedValue
    private Long id;

    private boolean isSubscribed;
    private SubscriptionType subscriptionType;
    private LocalDateTime lastPayment;
    private LocalDateTime nextPayment;
}
