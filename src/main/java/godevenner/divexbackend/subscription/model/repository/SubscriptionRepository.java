package godevenner.divexbackend.subscription.model.repository;

import godevenner.divexbackend.subscription.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

}
