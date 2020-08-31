package com.example.retrievalbased.retrievalBased.service;

import com.example.retrievalbased.retrievalBased.model.*;
import com.example.retrievalbased.retrievalBased.repository.ElasticSearchQuestionRepository;
import com.example.retrievalbased.retrievalBased.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

  // manipulate ElasticSearch
  @Autowired private ElasticSearchQuestionRepository elasticSearchQuestionRepository;
  // manipulate MySQL
  @Autowired private QuestionRepository questionRepository;

  @Override
  public Question save(Question q) {
    return elasticSearchQuestionRepository.save(q);
  }

  @Override
  public List<Question> elasticsearchFindByQuestionText(String questionText) {
    return elasticSearchQuestionRepository.findByQuestionText(questionText);
  }

  @Override
  public List<Question> findByQuestionText(String questionText) {
    return questionRepository.findByQuestionText(questionText);
  }

  @Override
  public Answer getSimilarQuestionAnswer(String questionText) {
    List<Question> questions = findByQuestionText(questionText);
    Answer answer = new Answer();
    for (Question question : questions) {
      if (question.getAnswers().size() > 0) {
        answer = question.getBestAnswer();
      }
    }
    return answer;
  }

  @Override
  public Question ask(String questionText) {
    Answer answer = getSimilarQuestionAnswer(questionText);
    // 查找是否有人问过相同问题
    List<Question> questions = elasticsearchFindByQuestionText(questionText);
    Question q = new Question();
    if (questions.size() > 0) {
      // 相同问题记录
      q = questions.get((int) Math.random() % questions.size());
      q.setVotes(q.getVotes() + 1);
    } else {
      // 新问题保存
      Calendar calendar = Calendar.getInstance();
      Date now = calendar.getTime();
      q.setQuestionText(questionText);
      q.setPubDate(now);
    }
    System.out.println("{question: " + q + ", answer: " + answer + "}");
    return save(q);
  }

  /**
   * 基于name在google place api中检索相应地理位置
   *
   * @param name
   * @return
   */
  @Override
  public List<Place> GooglePlace(String name) throws IOException, InterruptedException {
    final String apiKey = "YOUR_API_KEY";
    String baseUrl =
        "https://maps.googleapis.com/maps/api/place/textsearch/json?query="
            + name
            + "&key="
            + apiKey;
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://webcode.me")).build();

    HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
    BufferedReader reader =
        new BufferedReader(
            new InputStreamReader(response.getEntity().getContent(), "UTF-8")); // 待解决
    StringBuilder builder = new StringBuilder();
    for (String line = null; (line = reader.readLine()) != null; ) {
      builder.append(line).append("\n");
    }
    String arrayStr = builder.toString();
    // 转化为list
    List<Place> answer =
        (List<Place>) JSONArray.toList(JSONArray.fromObject(arrayStr), Place.class); // 待解决
    return answer;
  }
}
