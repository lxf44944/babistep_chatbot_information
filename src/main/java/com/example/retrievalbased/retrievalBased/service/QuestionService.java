package com.example.retrievalbased.retrievalBased.service;

import com.example.retrievalbased.retrievalBased.model.Answer;
import com.example.retrievalbased.retrievalBased.model.Question;

import java.util.List;

public interface QuestionService {
  Question save(Question q);

  List<Question> findByQuestionText(String questionText);

  Answer getSimilarQuestionAnswer(String questionText);

  Question ask(String questionText);
}
