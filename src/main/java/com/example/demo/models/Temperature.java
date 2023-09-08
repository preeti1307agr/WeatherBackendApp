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
public class Temperature {

	@JsonProperty("temp")
	private Double temp;
	
	@JsonProperty("tempMin")
	private Double tempMin;
	
	@JsonProperty("tempMax")
	private Double tempMax;
	
	@JsonProperty("pressure")
	private Double pressure;

}