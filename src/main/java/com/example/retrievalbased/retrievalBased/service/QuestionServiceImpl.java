package com.example.retrievalbased.retrievalBased.service;

import com.example.retrievalbased.retrievalBased.model.Answer;
import com.example.retrievalbased.retrievalBased.model.Question;
import com.example.retrievalbased.retrievalBased.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

  @Autowired private QuestionRepository questionRepository;

  @Override
  public Question save(Question q) {
    return questionRepository.save(q);
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
    List<Question> questions = findByQuestionText(questionText);
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
    return questionRepository.save(q);
  }
}
