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
public class Weather {

	@JsonProperty("weatherCondition")
	private String weatherCondition;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("weatherIcon")
	private String weatherIcon;

}