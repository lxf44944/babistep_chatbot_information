package com.example.retrievalbased.retrievalBased.model;

import java.util.List;

/**
 * 基于Google Place API官方文档给予的样例：https://developers.google.com/places/web-service/search。A Find Place
 * response contains only the data types that were specified using the fields parameter, plus
 * html_attributions. The following class shows the response for a Find Place request, including the
 * formatted_address, geometry, name, opening_hours, photos, rating fields.
 */
public class Place {
  private List<Candidate> candidates;
  private DebugLog debug_log;
  private String status;

  public class Candidate {
    private String formatted_address;
    private Geometry geometry;
    private String name;
    private OpeningHours opening_hours;
    private List<Photo> photos;
    private Long rating;
  }

  public class DebugLog {
    private List<String> line;
  }

  public class Geometry {
    private Location location;
    private Viewport viewport;
  }

  public class Viewport {
    private Northeast northeast;
    private Southwest southwest;
  }

  public class Location {
    private Long lat;
    private Long lng;
  }

  public class Northeast {
    private Long lat;
    private Long lng;
  }

  public class Southwest {
    private Long lat;
    private Long lng;
  }

  public class OpeningHours {
    private boolean open_now;
    private List<String> weekday_text;
  }

  public class Photo {
    private int height;
    private List<String> html_attributions;
    private String photo_reference;
    private int width;
  }
}
