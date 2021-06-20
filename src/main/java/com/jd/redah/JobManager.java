package com.jd.redah;

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
  public static void harvestWeather() {
    WeatherHarvester.harvest();
  }
}
