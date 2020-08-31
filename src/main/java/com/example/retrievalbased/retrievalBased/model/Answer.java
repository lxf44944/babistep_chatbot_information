package com.example.retrievalbased.retrievalBased.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Entity
@Data
@Document(indexName = "retrievalBased", type = "answer", refreshInterval = "0s")
public class Answer implements Model {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Field(type = FieldType.Keyword)
  private String answerText;

  @Field(type = FieldType.Keyword)
  private int votes;

  @Field(type = FieldType.Keyword)
  @ManyToOne
  @JsonIgnore
  private Question question;

  @Override
  public String getString() {
    return answerText;
  }

  public int getVotes() {
    return votes;
  }

  public void setVotes(int votes) {
    this.votes = votes;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getAnswerText() {
    return answerText;
  }

  public void setAnswerText(String answerText) {
    this.answerText = answerText;
  }

  public Question getQuestion() {
    return question;
  }

  public void setQuestion(Question question) {
    this.question = question;
  }
}
