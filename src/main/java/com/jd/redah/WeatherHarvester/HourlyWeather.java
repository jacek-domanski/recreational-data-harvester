package com.jd.redah.WeatherHarvester;

import java.util.List;

public class HourlyWeather {
  protected long dt;

  protected double temp;
  protected double feels_like;

  protected int pressure;
  protected int humidity;
  protected double dew_point;
  protected int clouds;
  protected int visibility;

  protected double wind_speed;
  protected int wind_deg;

  protected List<Weather> weather;

  public HourlyWeather() {
  }
  
  @Override
  public String toString() {
    return "HourlyWeather [clouds=" + clouds + ", dew_point=" + dew_point + ", dt=" + dt + ", feels_like=" + feels_like
        + ", humidity=" + humidity + ", pressure=" + pressure + ", temp=" + temp + ", visibility=" + visibility
        + ", weather=" + weather + ", wind_deg=" + wind_deg + ", wind_speed=" + wind_speed + "]\n";
  }

  public long getDt() {
    return dt;
  }

  public void setDt(long dt) {
    this.dt = dt;
  }

  public double getTemp() {
    return temp;
  }

  public void setTemp(double temp) {
    this.temp = temp;
  }

  public double getFeels_like() {
    return feels_like;
  }

  public void setFeels_like(double feels_like) {
    this.feels_like = feels_like;
  }

  public int getPressure() {
    return pressure;
  }

  public void setPressure(int pressure) {
    this.pressure = pressure;
  }

  public int getHumidity() {
    return humidity;
  }

  public void setHumidity(int humidity) {
    this.humidity = humidity;
  }

  public double getDew_point() {
    return dew_point;
  }

  public void setDew_point(double dew_point) {
    this.dew_point = dew_point;
  }

  public int getClouds() {
    return clouds;
  }

  public void setClouds(int clouds) {
    this.clouds = clouds;
  }

  public int getVisibility() {
    return visibility;
  }

  public void setVisibility(int visibility) {
    this.visibility = visibility;
  }

  public double getWind_speed() {
    return wind_speed;
  }

  public void setWind_speed(double wind_speed) {
    this.wind_speed = wind_speed;
  }

  public int getWind_deg() {
    return wind_deg;
  }

  public void setWind_deg(int wind_deg) {
    this.wind_deg = wind_deg;
  }

  public List<Weather> getWeather() {
    return weather;
  }

  public void setWeather(List<Weather> weather) {
    this.weather = weather;
  }
}
