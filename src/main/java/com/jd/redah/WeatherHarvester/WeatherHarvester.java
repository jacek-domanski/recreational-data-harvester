package com.jd.redah.WeatherHarvester;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherHarvester {
  public static void harvest() {
    System.out.println("Performing request...");

    String lat = "50.061389";
    String lon = "19.938333";

    long dt = 
      LocalDateTime.now()
        .withHour(12)
        .minusDays(1)
        .atZone(ZoneId.of("UTC"))
        .toEpochSecond();

    String weatherApiKey = readWeatherApiKey();

    RestTemplate restTemplate = new RestTemplateBuilder().build();

    String requestUrl = "https://api.openweathermap.org/data/2.5/onecall/timemachine?";
    String requestParams = 
      "lat=" + lat + 
      "&lon=" + lon + 
      "&dt=" + dt + 
      "&appid=" + weatherApiKey;

    HistoricalWeather response = 
      restTemplate.getForObject(
        requestUrl + requestParams,
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
