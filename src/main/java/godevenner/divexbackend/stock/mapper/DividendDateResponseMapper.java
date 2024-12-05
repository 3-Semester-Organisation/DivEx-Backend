package godevenner.divexbackend.stock.mapper;

import godevenner.divexbackend.stock.dto.DividendDateResponse;
import godevenner.divexbackend.stock.model.Dividend;
import org.springframework.stereotype.Component;

@Component
public class DividendDateResponseMapper {

    public DividendDateResponse toDividendDateResponse(Dividend dividend) {
        return new DividendDateResponse(
                dividend.getExDividendDate()
        );
    }
}
