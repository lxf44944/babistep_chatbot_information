package com.example.retrievalbased.retrievalBased.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int userId;

  private String userHeadUrl;

  private String userName;

  private int userGender; // 用户性别：0代表未知，1代表男生，2代表女生

  @OneToMany(
      mappedBy = "answer",
      cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
  private List<Post> posts;
}
