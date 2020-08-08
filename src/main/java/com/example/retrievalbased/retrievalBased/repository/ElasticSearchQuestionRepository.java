package com.example.retrievalbased.retrievalBased.repository;

import com.example.retrievalbased.retrievalBased.model.Question;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ElasticSearchQuestionRepository
    extends ElasticsearchRepository<Question, Integer> {

  List<Question> findByQuestionText(String questionText);
}
