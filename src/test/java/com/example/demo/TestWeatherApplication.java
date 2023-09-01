package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestWeatherApplication {

	public static void main(String[] args) {
		SpringApplication.from(WeatherApplication::main).with(TestWeatherApplication.class).run(args);
	}

}
