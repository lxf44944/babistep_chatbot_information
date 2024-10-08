package com.example.retrievalbased.retrievalBased.repository;

import com.example.retrievalbased.retrievalBased.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
  List<Question> findByQuestionText(String questionText);
}
