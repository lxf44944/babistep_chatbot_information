package com.example.retrievalbased.retrievalBased.service;

import com.example.retrievalbased.retrievalBased.model.*;
import com.example.retrievalbased.retrievalBased.repository.ElasticSearchQuestionRepository;
import com.example.retrievalbased.retrievalBased.repository.QuestionRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;

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
  public Place GooglePlace(String name) throws IOException, InterruptedException {
    final String apiKey = "YOUR_API_KEY";
    String baseUrl =
        "https://maps.googleapis.com/maps/api/place/textsearch/json?query="
            + name
            + "&key="
            + apiKey;
    HashMap<String, Object> map = convertHelper(baseUrl);
    HashMap candidates = (HashMap) map.get("candidates");
    HashMap geometry = (HashMap) candidates.get("geometry");
    HashMap location = (HashMap) geometry.get("location");
    long lat = (long) location.get("lat");
    long lng = (long) location.get("lng");
    String formatted_address = (String) candidates.get("formatted_address");
    String placeName = (String) candidates.get("name");
    Place place = new Place();
    place.setLat(lat);
    place.setLng(lng);
    place.setFormattedAddress(formatted_address);
    place.setName(placeName);
    return place;
  }

  @Override
  public Weather askWeather(String name) throws IOException, InterruptedException {
    final String apiKey = "439d4b804bc8187953eb36d2a8c26a02";
    String baseUrl = "api.openweathermap.org/data/2.5/weather?q=" + name + "&appid=" + apiKey;
    HashMap<String, Object> map = convertHelper(baseUrl);
    HashMap wind = (HashMap) map.get("wind");
    long speed = (long) wind.get("speed");
    HashMap main = (HashMap) map.get("main");
    long temp = (long) wind.get("temp");
    Weather weather = new Weather();
    weather.setWindSpeed(speed);
    weather.setTemp(temp);
    return weather;
  }

  /**
   * Helper method to convert JSONString to HashMap based on the json answer returned by the given
   * url
   *
   * @param baseUrl
   * @return
   */
  private HashMap<String, Object> convertHelper(String baseUrl) {
    HttpClient client = HttpClientBuilder.create().build();
    HttpGet request = new HttpGet(baseUrl);
    String content = null;
    try {
      HttpResponse response = client.execute(request);
      HttpEntity entity = response.getEntity();
      // Read the contents of an entity and return it as a String.
      content = EntityUtils.toString(entity);
      System.out.println(content);
    } catch (IOException e) {
      e.printStackTrace();
    }
    Place place = new Place();
    HashMap<String, Object> map =
        new Gson().fromJson(content, new TypeToken<HashMap<String, Object>>() {}.getType());
    return map;
  }
}
