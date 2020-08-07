package com.example.retrievalbased.retrievalBased.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Entity
public class Answer implements Model {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String answerText;

  private int votes;

  @OneToMany(
      mappedBy = "answer",
      cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
  private List<Question> questions;

  @Override
  public String getString() {
    return answerText;
  }
}
