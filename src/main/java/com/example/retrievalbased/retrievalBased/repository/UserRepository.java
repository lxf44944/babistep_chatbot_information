package com.example.retrievalbased.retrievalBased.repository;

import com.example.retrievalbased.retrievalBased.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
  Page<User> findAll(Pageable pageable);
}
