package com.emse.spring.faircorp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.client.RestTemplate;

/****
 * Here is the main class
 *
 * In order to run a Spring Boot application, it needs to have a class with the @SpringBootApplication annotation. It simply invokes the SpringApplication.run method. This starts the Spring application as a standalone application, runs the embedded servers and loads the beans.
 * It enables component scanning to scan all the sub-packages for beans.
 *
 * @author Nushrat Jahan
 */



@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true)
public class FaircorpApplication {
	public static void main(String[] args) {
		SpringApplication.run(FaircorpApplication.class, args);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://192.168.67.225/data";
		String jsonString = restTemplate.getForObject(url, String.class);
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode root = mapper.readTree(jsonString);
			String temperature = root.get("temperature").asText();
			String humidity = root.get("humidity").asText();
			String CO2 = root.get("CO2").asText();
			String time = root.get("time").asText();
			boolean presence = root.get("presence").asBoolean();
			System.out.println("Temperature: " + temperature);
			System.out.println("Humidity: " + humidity);
			System.out.println("CO2: " + CO2);
			System.out.println("Time: " + time);
			System.out.println("Presence: " + presence);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}