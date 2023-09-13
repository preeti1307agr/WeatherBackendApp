package com.example.demo.service;

import com.example.demo.exception.WeatherServiceException;
import com.example.demo.models.WeatherReport;
import com.example.demo.service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class WeatherServiceTest {

    @InjectMocks
    private WeatherService weatherService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
            ReflectionTestUtils.setField(weatherService, "applicationKey", "d2929e9483efc82c82c32ee7e02d563e");
            ReflectionTestUtils.setField(weatherService, "count", "30");
            ReflectionTestUtils.setField(weatherService, "unitsForTemp", "metric");
    }

    @Test
    public void testGetWeatherForecast() throws ParseException {
        // Mock the response from the external API
        String weatherResult = "{\n" +
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
        ResponseEntity<String> responseEntity = new ResponseEntity<>(weatherResult, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
                .thenReturn(responseEntity);

        // Call the service method
        List<WeatherReport> weatherList = weatherService.getWeatherForecast("city");

        assertEquals(30, weatherList.size());
    }

    @Test
    public void testGetWeatherForecastWithError() {
        // Mocking an error response from the external API
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
                .thenThrow(new HttpStatusCodeException(HttpStatus.NOT_FOUND) {

                    public HttpStatus getStatusCode() {
                        return HttpStatus.NOT_FOUND;
                    }
                });

        // Call the service method and expect a WeatherServiceException
        try {
            weatherService.getWeatherForecast("city");
        } catch (WeatherServiceException | ParseException e) {
            assertEquals("Error while fetching weather data: 404 NOT_FOUND", e.getMessage());
        }
    }
}




