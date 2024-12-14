package godevenner.divexbackend.stock.repository;

import godevenner.divexbackend.stock.model.Stock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StockRepositoryTest {

    @Autowired
    private StockRepository stockRepository;

    @Test
    void findByTicker() {
        String expectedStockName = "Novo Nordisk B A/S";
        String actualStockName = stockRepository.findByTicker("NOVO-B.CO").get().getName();
        assertEquals(expectedStockName, actualStockName);
    }

    @Test
    void searchByName() {
        String expectedStockName = "Novo Nordisk B A/S";
        String actualStockName = stockRepository.findByNameOrTicker("novo").getFirst().getName();
        assertEquals(expectedStockName, actualStockName);
    }

    @Test
    void searchByTicker() {
        String expectedStockName = "Novo Nordisk B A/S";
        String actualStockName = stockRepository.findByNameOrTicker("NOVO-B.").getFirst().getName();
        assertEquals(expectedStockName, actualStockName);
    }



}