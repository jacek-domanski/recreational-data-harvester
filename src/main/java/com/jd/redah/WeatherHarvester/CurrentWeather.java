package com.jd.redah.WeatherHarvester;

public class CurrentWeather extends HourlyWeather {
  private long sunrise;
  private long sunset;

  private double uvi;

  public CurrentWeather() {
  }

  @Override
  public String toString() {
    return "CurrentWeather [sunrise=" + sunrise + ", sunset=" + sunset + ", uvi=" + uvi + "clouds=" + clouds + ", dew_point=" + dew_point + ", dt=" + dt + ", feels_like=" + feels_like
    + ", humidity=" + humidity + ", pressure=" + pressure + ", temp=" + temp + ", visibility=" + visibility
    + ", weather=" + weather + ", wind_deg=" + wind_deg + ", wind_speed=" + wind_speed + "]";
  }

  public long getSunrise() {
    return sunrise;
  }

  public void setSunrise(long sunrise) {
    this.sunrise = sunrise;
  }

  public long getSunset() {
    return sunset;
  }

  public void setSunset(long sunset) {
    this.sunset = sunset;
  }

  public double getUvi() {
    return uvi;
  }

  public void setUvi(double uvi) {
    this.uvi = uvi;
  }

  
}
