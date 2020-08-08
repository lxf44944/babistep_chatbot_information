package com.example.retrievalbased.retrievalBased.repository;

import com.example.retrievalbased.retrievalBased.model.Answer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AnswerRepository extends ElasticsearchRepository<Answer, Integer> {}
