package com.jd.redah.WeatherHarvester;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class HistoricalWeather {
    private double lat;
    private double lon;
    private String timezone;
    private String timezone_offset;
    private CurrentWeather current;
    private List<HourlyWeather> hourly;
    
    public HistoricalWeather() {
    }

    @Override
    public String toString() {
        return "HistoricalWeather [current=" + current + ", hourly=" + hourly + ", lat=" + lat + ", lon=" + lon
                + ", timezone=" + timezone + ", timezone_offset=" + timezone_offset + "]\n";
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTimezone_offset() {
        return timezone_offset;
    }

    public void setTimezone_offset(String timezone_offset) {
        this.timezone_offset = timezone_offset;
    }

    public CurrentWeather getCurrent() {
        return current;
    }

    public void setCurrent(CurrentWeather current) {
        this.current = current;
    }

    public List<HourlyWeather> getHourly() {
        return hourly;
    }

    public void setHourly(List<HourlyWeather> hourly) {
        this.hourly = hourly;
    }

        
}
