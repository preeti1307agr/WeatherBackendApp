package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Forecast {

	@JsonProperty("time")
	private String time;
	
	@JsonProperty("temperature")
	private  Temperature temperature;
	
	@JsonProperty("weather")
	private Weather weather;
	
	@JsonProperty("wind")
	private WindSpeed windSpeed;

}