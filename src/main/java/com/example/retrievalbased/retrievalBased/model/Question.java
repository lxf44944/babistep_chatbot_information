package com.example.retrievalbased.retrievalBased.model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@Document(indexName = "retrievalBased", type = "question", refreshInterval = "0s")
public class Question implements Model {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Field(type = FieldType.Keyword)
  @OneToMany(
      mappedBy = "question",
      cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
  private List<Answer> answers;

  @Field(type = FieldType.Keyword)
  private String questionText;

  @Field(type = FieldType.Keyword)
  private int votes;

  @Temporal(TemporalType.TIMESTAMP)
  private Date pubDate;

  public int getVotes() {
    return votes;
  }

  public void setVotes(int votes) {
    this.votes = votes;
  }

  @Override
  public String getString() {
    return questionText;
  }

  public boolean wasPublishedRecently() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    calendar.add(Calendar.DATE, -1);
    return pubDate.compareTo(calendar.getTime()) <= 1;
  }

  public Answer getBestAnswer() {
    answers.sort(Comparator.comparingInt(Answer::getVotes));
    return answers.get(answers.size() - 1);
  }

  public List<Answer> getAnswers() {
    return answers;
  }

  public void setAnswers(List<Answer> answers) {
    this.answers = answers;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setQuestionText(String questionText) {
    this.questionText = questionText;
  }

  public Date getPubDate() {
    return pubDate;
  }

  public void setPubDate(Date pubDate) {
    this.pubDate = pubDate;
  }
}
