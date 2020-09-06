package com.example.retrievalbased.retrievalBased.model;

/**
 * 基于Google Place API官方文档给予的样例：https://developers.google.com/places/web-service/search。仅选取经纬度+地址+名称
 */
public class Place {
  private long lat;
  private long lng;
  private String formattedAddress;
  private String name;

  public long getLat() {
    return lat;
  }

  public void setLat(long lat) {
    this.lat = lat;
  }

  public long getLng() {
    return lng;
  }

  public void setLng(long lng) {
    this.lng = lng;
  }

  public String getFormattedAddress() {
    return formattedAddress;
  }

  public void setFormattedAddress(String formattedAddress) {
    this.formattedAddress = formattedAddress;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
