package godevenner.divexbackend.stock;

import godevenner.divexbackend.stock.dto.StockPopularity;

import java.util.Comparator;

public class PopularityComparator implements Comparator<StockPopularity> {
    @Override
    public int compare(StockPopularity o1, StockPopularity o2) {
        return Integer.compare(o1.visits(), o2.visits());
    }
}
