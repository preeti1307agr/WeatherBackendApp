package com.example.demo.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class CorsConfigurationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCorsConfiguration() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.options("/test")
                        .header("Origin", "http://localhost:3000")
                        .header("Access-Control-Request-Method", "GET")
                        .header("Access-Control-Request-Headers", "Content-Type"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.header().string("Access-Control-Allow-Origin", "http://localhost:3000"))
                .andExpect(MockMvcResultMatchers.header().string("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE"))
                .andExpect(MockMvcResultMatchers.header().string("Access-Control-Allow-Headers", "Content-Type"));
    }
}

