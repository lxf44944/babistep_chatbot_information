package com.example.retrievalbased.retrievalBased.controller;

import com.example.retrievalbased.retrievalBased.model.Place;
import com.example.retrievalbased.retrievalBased.model.Question;
import com.example.retrievalbased.retrievalBased.model.Weather;
import com.example.retrievalbased.retrievalBased.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class QuestionController {
  @Autowired private QuestionService questionService;

  @PostMapping("/question/{questionText}")
  public Question findQuestionByQuestionText(@PathVariable("questionText") String questionText) {
    System.out.println("Question Text = " + questionText);
    return questionService.ask(questionText);
  }

  @PostMapping("/question/googleMap/{name}") // 暂定使用该restful api
  public Place googlePlace(@PathVariable("name") String name)
      throws IOException, InterruptedException {
    return questionService.GooglePlace(name);
  }

  @PostMapping("/question/weather/{name}") // 暂定使用该restful api
  public Weather askWeather(@PathVariable("name") String name)
      throws IOException, InterruptedException {
    return questionService.askWeather(name);
  }
}
