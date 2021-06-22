package com.jd.redah.WeatherHarvester;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherHarvester {
  private List<HistoricalWeather> yesterdaysWeathers;

    private class Place {
      private String name;
      private String latitude;
      private String longitude;
      
      public Place() {
      }

      public Place(String line) throws Exception {
        String[] parts = line.split("\t");
        if (parts.length != 3) {
          throw new Exception(line + "Cannot parse file");
        }

        this.name = parts[0];
        this.latitude = parts[1];
        this.longitude = parts[2];
      }

      public Place(String name, String latitude, String longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
      }

      @Override
      public String toString() {
        return "Place [ " + "name=" + name + ", latitude=" + latitude + ", longitude=" + longitude + "]";
      }

      public String getName() {
        return name;
      }

      public void setName(String name) {
        this.name = name;
      }

      public String getLatitude() {
        return latitude;
      }

      public void setLatitude(String latitude) {
        this.latitude = latitude;
      }

      public String getLongitude() {
        return longitude;
      }

      public void setLongitude(String longitude) {
        this.longitude = longitude;
      }

      


    }

  public void harvest() throws FileNotFoundException {
    System.out.println("Performing requests...");

    List<Place> places = readPlaces();

    long dt = 
      LocalDateTime.now()
        .withHour(12)
        .minusDays(1)
        .atZone(ZoneId.of("UTC"))
        .toEpochSecond();

    requestWeathersForPlaces(places, dt);    
}

  public void parse() {
    for (HistoricalWeather weather : yesterdaysWeathers) {
      double nighttimeTemperature = 
        weather.getHourly().stream()
          .filter(w -> 
            w.dt < weather.getCurrent().getSunrise() || 
            w.dt >= weather.getCurrent().getSunset())
          .mapToDouble(w -> w.temp)
          .average()
          .orElseThrow();

        double daytimeTemperature = 
          weather.getHourly().stream()
            .filter(w -> 
              w.dt >= weather.getCurrent().getSunrise() && 
              w.dt < weather.getCurrent().getSunset())
            .mapToDouble(w -> w.temp)
            .average()
            .orElseThrow();
        
        double dailyTemperature = 
        weather.getHourly().stream()
          .mapToDouble(w -> w.temp)
          .average()
          .orElseThrow();

      System.out.println(
        "Averages for " + weather.getName() + 
        ": nighttime: " + nighttimeTemperature + 
        " daytime: " + daytimeTemperature + 
        " daily: " + dailyTemperature);
    }
  }

  private void requestWeathersForPlaces(List<Place> places, long dt) {
    yesterdaysWeathers = new ArrayList<>();

    RestTemplate restTemplate = new RestTemplateBuilder().build();
    String weatherApiKey = readWeatherApiKey();
    String requestUrl = "https://api.openweathermap.org/data/2.5/onecall/timemachine?units=metric";

    for (Place place : places) {
      String requestParams = 
        "&lat=" + place.latitude + 
        "&lon=" + place.longitude + 
        "&dt=" + dt + 
        "&appid=" + weatherApiKey;
  
      HistoricalWeather response = 
        restTemplate.getForObject(
          requestUrl + requestParams,
          HistoricalWeather.class);

      response.setName(place.getName());
  
      System.out.println("Request performed for " + place.name + "!");
  
      System.out.println(response);
      yesterdaysWeathers.add(response);
    }
  }

  private List<Place> readPlaces() throws FileNotFoundException {
    List<Place> places = new ArrayList<>();
    Scanner scanner;
    File file = new File("weatherPlaces.txt");
    try {
      scanner = new Scanner(file);
    } catch (FileNotFoundException e) {
      throw e;
    }

    while (scanner.hasNextLine()) {
      try {
        places.add(new Place(scanner.nextLine()));
      } catch (Exception e) {
        continue;
      }
    }

    scanner.close();
    return places;
  }

  private String readWeatherApiKey() {
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
