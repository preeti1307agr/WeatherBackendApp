package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.mockito.Mockito.when;

import com.example.demo.models.*;
import com.example.demo.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;


@WebMvcTest(WeatherController.class)
public class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    @Test
    public void testHomeEndpoint() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World!"));
    }

    @Test
    public void testGetWeatherForThreeDaysEndpoint() throws Exception {
        String city = "London";

        // Create a sample WeatherReport list for mocking
        String mockWeatherResult = "{\n" +
                "    forecastWeather: {\n" +
                "      time: 12,\n" +
                "      weather: {\n" +
                "        weatherCondition: \"Clear\",\n" +
                "        description: \"Clear sky\",\n" +
                "        weatherIcon: \"01d\",\n" +
                "      },\n" +
                "      temperature: {\n" +
                "        temp: 25,\n" +
                "        pressure: 1013,\n" +
                "      },\n" +
                "      wind: {\n" +
                "        speed: 5,\n" +
                "      },\n" +
                "    },\n" +
                "    weatherDate: \"2023-09-07\"\n" +
                "  }";
        Weather w = new Weather();
        w.setWeatherIcon("");
        w.setWeatherCondition("");
        w.setDescription("");
        Temperature temp = new Temperature();
        temp.setTemp(0.00);
        temp.setTempMax(10.00);
        temp.setTempMin(7.00);
        temp.setPressure(8.56);
        WindSpeed ws = new WindSpeed();
        ws.setWindSpeed(3.98);
        Forecast f = new Forecast();
        f.setTime("");
        f.setWeather(w);
        f.setTemperature(temp);
        f.setWindSpeed(ws);
        WeatherReport we = new WeatherReport();
        we.setWeatherDate("");
        we.setForecastWeather(f);

        List<WeatherReport> weatherReports = new ArrayList<>();
        weatherReports.add(we);
        // Add some WeatherReport objects to the list

        // Mock the behavior of the WeatherService
        when(weatherService.getWeatherForecast(city)).thenReturn(weatherReports);

        mockMvc.perform(get("/forecast/" + city))
                .andExpect(status().isOk());
        // You can add more assertions here to check the response content and structure
    }
}

