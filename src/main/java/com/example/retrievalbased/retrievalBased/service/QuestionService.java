package com.example.retrievalbased.retrievalBased.service;

import com.example.retrievalbased.retrievalBased.model.Answer;
import com.example.retrievalbased.retrievalBased.model.Place;
import com.example.retrievalbased.retrievalBased.model.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionService {
  Question save(Question q);

  List<Question> elasticsearchFindByQuestionText(String questionText);

  List<Question> findByQuestionText(String questionText);

  Answer getSimilarQuestionAnswer(String questionText);

  Question ask(String questionText);

  /**
   * 基于name在google place api中检索相应地理位置
   *
   * @param name
   * @return
   */
  List<Place> GooglePlace(String name) throws IOException, InterruptedException;
}
