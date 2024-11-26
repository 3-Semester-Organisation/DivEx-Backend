package godevenner.divexbackend.stock.controller;

import godevenner.divexbackend.stock.service.StockService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StockService stockService;


    @Test
    void getAllStocks() throws Exception {
        mockMvc.perform(get("/api/v1/stocks")
                        .param("page", "0")
                        .param("size", "20")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0].ticker").value(""))
                .andExpect(jsonPath("$.content[0].name").value(""));
    }

    @Test
    void getStock() throws Exception {
        mockMvc.perform(get("/api/v1/stock/{ticker}", "NOVO-B.CO"))
                .andExpect(status().isOk());
    }

    @Test
    void getStockFail() throws Exception {
        mockMvc.perform(get("/api/v1/stock/{ticker}", "X"))
                .andExpect(status().isNotFound());
    }
}