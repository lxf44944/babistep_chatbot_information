package com.example.retrievalbased.retrievalBased.model;

/** 基于https://openweathermap.org/current所示范例 */
public class Weather {
  private long windSpeed;
  private long temp;

  public long getWindSpeed() {
    return windSpeed;
  }

  public void setWindSpeed(long windSpeed) {
    this.windSpeed = windSpeed;
  }

  public long getTemp() {
    return temp;
  }

  public void setTemp(long temp) {
    this.temp = temp;
  }
}
