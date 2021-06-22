package com.jd.redah;

import java.io.FileNotFoundException;

import com.jd.redah.WeatherHarvester.WeatherHarvester;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@Configuration
@EnableScheduling
public class JobManager {
  
  @Autowired
  private WeatherHarvester weatherHarvester;


	// @Scheduled(cron = "0 0 1 * * * ", zone = "Europe/Warsaw")
	@Scheduled(fixedDelay = 120000)
  public void harvestWeather() {
    try {
      weatherHarvester.harvest();
    } catch (FileNotFoundException e) {
      System.out.println("File weatherPlaces.txt not found");
      System.out.println(e);
    }
    weatherHarvester.parse();
  }
}
