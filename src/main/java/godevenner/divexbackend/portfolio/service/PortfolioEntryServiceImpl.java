package godevenner.divexbackend.portfolio.service;

import godevenner.divexbackend.portfolio.dto.PortfolioEntryRequest;
import godevenner.divexbackend.portfolio.dto.PortfolioEntryResponse;
import godevenner.divexbackend.portfolio.mapper.PortfolioEntryMapper;
import godevenner.divexbackend.portfolio.model.PortfolioEntry;
import godevenner.divexbackend.portfolio.repository.PortfolioEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PortfolioEntryServiceImpl implements PortfolioEntryService {

    private final PortfolioEntryRepository portfolioEntryRepository;
    private final PortfolioEntryMapper portfolioEntryMapper;

    @Override
    public PortfolioEntryResponse createPortfolioEntry(PortfolioEntryRequest request) {
        PortfolioEntry portfolioEntry = portfolioEntryMapper.toPortfolioEntry(request);
        PortfolioEntry savedRequest = portfolioEntryRepository.save(portfolioEntry);
        PortfolioEntryResponse response = portfolioEntryMapper.toPortfolioEntryResponse(savedRequest);
        return response;
    }

    @Override
    public void deletePortfolioEntry(long portfolioEntryId) {
        portfolioEntryRepository.deletePortfolioEntryById(portfolioEntryId);
    }


}
