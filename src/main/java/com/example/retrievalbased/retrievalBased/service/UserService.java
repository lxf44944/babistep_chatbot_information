package com.example.retrievalbased.retrievalBased.service;

import com.example.retrievalbased.retrievalBased.model.User;
import com.example.retrievalbased.retrievalBased.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
public class UserService {
  @Autowired private UserRepository userRepository;

  public Page<User> findAllByPage(int pageNo, int pageSize) {
    Sort sort = Sort.by(ASC, "userId");
    Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
    return userRepository.findAll(pageable);
  }
}
