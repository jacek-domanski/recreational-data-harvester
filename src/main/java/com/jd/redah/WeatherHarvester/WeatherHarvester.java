package com.jd.redah.WeatherHarvester;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableScheduling
public class WeatherHarvester {

	// @Scheduled(cron = "0 0 1 * * * ", zone = "Europe/Warsaw")
	@Scheduled(fixedDelay = 120000)
	public static void harvest() {
        RestTemplate restTemplate = new RestTemplateBuilder().build();

	    System.out.println("Performing request...");

        String lat = "50.061389";
        String lon = "19.938333";
        String dt = "1623771931";
        String weatherApiKey = readWeatherApiKey();

        HistoricalWeather response = 
            restTemplate.getForObject(
                String.format(
                    "https://api.openweathermap.org/data/2.5/onecall/timemachine?lat=%s&lon=%s&dt=%s&appid=%s", 
                    lat, lon, dt, weatherApiKey), 
                HistoricalWeather.class);
        
	    System.out.println("Request performed!");
        
	    System.out.println(response);
	}

    private static String readWeatherApiKey() {
        try {
            File apiKeyFile = new File("apiKey.txt");
            Scanner scanner = new Scanner(apiKeyFile);
            if (scanner.hasNext()) {
                String apiKey = scanner.nextLine();
                scanner.close();
                return apiKey;

            } else {
                scanner.close();
                throw new Exception();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error while retrieving weather api key");
            e.printStackTrace();
            return new String();

        } catch (Exception e) {
            System.out.println("Error while retrieving weather api key");
            e.printStackTrace();
            return new String();

        }
    }
    
}
