package com.example.demo.models;


import javax.persistence.Entity;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WeatherReport {
	
		@JsonProperty("weatherDate")
		private String weatherDate;

		@JsonProperty("forecastWeather")
		private Forecast forecastWeather;

}
