package godevenner.divexbackend.tracker;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrackLogRepository extends JpaRepository<TrackLog, Long> {

    List<TrackLog> findAllByStockId(long stockId);

    int countByStockId(long stockId);
}
