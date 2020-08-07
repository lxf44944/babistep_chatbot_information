package com.example.retrievalbased.retrievalBased.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int postId;

  private String postDesc;

  private int postMediaType; // 多媒体类型：0代表无，1代表图片，2代表视频

  private List<String> postMediaUrls;

  private int postLikeNum; // 点赞量

  private int postShareNum; // 转发量

  @ManyToOne @JsonIgnore private User user;
}
