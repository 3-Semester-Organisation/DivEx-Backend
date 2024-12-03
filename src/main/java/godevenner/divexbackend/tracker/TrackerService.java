package godevenner.divexbackend.tracker;

import godevenner.divexbackend.stock.model.Stock;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

@RequiredArgsConstructor
@Getter
@Setter
@Service
public class TrackerService {

    private final TrackLogRepository trackLogRepository;

    public void track(long stockId, String ipAdress) {
        TrackLog trackLog = TrackLog.builder()
                .stockId(stockId)
                .ipAddress(ipAdress)
                .timestamp(System.currentTimeMillis()/1000)
                .build();
        trackLogRepository.save(trackLog);
        System.out.println("#################  TRACKED    #############################################");
    }

}
