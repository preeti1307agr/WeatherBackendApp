package com.example.demo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.example.demo.client.OpenWeatherMapFeignClient;
import com.example.demo.exception.WeatherServiceException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.models.Forecast;
import com.example.demo.models.WeatherReport;
import com.example.demo.models.WindSpeed;
import com.example.demo.models.Temperature;
import com.example.demo.models.Weather;

@Service
public class WeatherService {

	@Value("${service.open-weather-map.api-key}")
	String applicationKey;

	@Value("${service.open-weather-map.count}")
	String count;

	@Value("${service.open-weather-map.units}")
	String unitsForTemp;

	private final OpenWeatherMapFeignClient openWeatherMapFeignClient;

	private Logger logger = LoggerFactory.getLogger(WeatherService.class);

	public WeatherService(OpenWeatherMapFeignClient openWeatherMapFeignClient) {
		this.openWeatherMapFeignClient = openWeatherMapFeignClient;
	}

	// This method retrieves weather forecast data for a given city.
	public List<WeatherReport> getWeatherForecast(String city) throws WeatherServiceException, ParseException {

		// Making an API call to openweathermap.org
		RestTemplate restTemplate = new RestTemplate();

		String weatherURL = "https://api.openweathermap.org/data/2.5/forecast?q="+city+"&appid="+applicationKey+"&cnt="+count+"&units="+unitsForTemp+"";

		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_TYPE,"application/json");
		headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		headers.set(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "86400");
        headers.setAccessControlAllowMethods(Arrays.asList(HttpMethod.GET, HttpMethod.OPTIONS));
        HttpEntity <String> entity = new HttpEntity<String>(headers);

		// Sending an HTTP GET request to retrieve weather data
	    String weatherResult =  restTemplate.exchange(weatherURL, HttpMethod.GET, entity, String.class).getBody();
//		String weatherResult = openWeatherMapFeignClient.getForecast(city, applicationKey, count, unitsForTemp);

		if (weatherResult == null) {
			throw new WeatherServiceException(
					"Invalid response from Open Weather Map");
		}

		// Variables to store weather data
		double temp = 0;
    	double tempMin = 0;
    	double tempMax = 0;
    	double pressure = 0;
    	String description = "";
    	String weatherDateAndTime = "";
    	String weatherCondition = "";
    	String weatherIcon = "";
    	double windSpeed = 0.00;
    	SimpleDateFormat timeFormat = new SimpleDateFormat("HH");
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	SimpleDateFormat timeAndDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date dateAndTime = null;
        
        List<WeatherReport> weatherList = new ArrayList<>();

		// Parsing the JSON response from the weather API
        JSONObject weatherForecast = new JSONObject(weatherResult);
        logger.info("Weather Forecast: "+ weatherResult);

        JSONArray weatherForecastObject = weatherForecast.getJSONArray("list");
        logger.info("Weather Forecast object: "+ weatherForecastObject);

        for(int i = 0; i < weatherForecastObject.length(); i++) {

        	JSONObject weatherObject = weatherForecastObject.getJSONObject(i);
        
        	JSONObject main = weatherObject.getJSONObject("main");
        	windSpeed = weatherObject.getJSONObject("wind").getDouble("speed");
        	description = weatherObject.getJSONArray("weather").getJSONObject(0).getString("description");
        	weatherCondition = weatherObject.getJSONArray("weather").getJSONObject(0).getString("main");
        	weatherIcon = weatherObject.getJSONArray("weather").getJSONObject(0).getString("icon");
        	weatherDateAndTime = weatherObject.getString("dt_txt");
        	
        	Forecast forecast = new Forecast();
        	Temperature temperature = new Temperature();
        	WeatherReport weatherReport = new WeatherReport();
        	Weather weather = new Weather();
        	WindSpeed wind = new WindSpeed();
        
        	temp = main.getDouble("temp");
        	tempMin = main.getDouble("temp_min");
        	tempMax = main.getDouble("temp_max");
        	pressure = main.getDouble("pressure");
        	
            dateAndTime = timeAndDateFormat.parse(weatherDateAndTime);
       
            temperature.setTemp(temp);
            temperature.setTempMax(tempMax);
            temperature.setTempMin(tempMin);
            temperature.setPressure(pressure);
             
            weather.setDescription(description);
            weather.setWeatherCondition(weatherCondition);
            weather.setWeatherIcon(weatherIcon);
            
            wind.setWindSpeed(windSpeed);
             
            forecast.setTime(timeFormat.format(dateAndTime));
            forecast.setTemperature(temperature);
            forecast.setWeather(weather);
            forecast.setWindSpeed(wind);
            
            weatherReport.setWeatherDate(dateFormat.format(dateAndTime));                                                           							                                				                                
            weatherReport.setForecastWeather(forecast);
            
               
            weatherList.add(weatherReport);              
        }
        return weatherList;
    }

}
