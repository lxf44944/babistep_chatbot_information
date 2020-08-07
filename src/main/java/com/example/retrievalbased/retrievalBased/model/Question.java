package com.example.retrievalbased.retrievalBased.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

public class Question implements Model {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne @JsonIgnore private Answer answer;

  private String questionText;

  @Temporal(TemporalType.TIMESTAMP)
  private Date pubDate;

  @Override
  public String getString() {
    return questionText;
  }

  public boolean wasPublishedRecently() {
    Calendar calendar = Calendar.getInstance();
    Date now = calendar.getTime();
    return pubDate.compareTo(now) <= 1;
  }
}
