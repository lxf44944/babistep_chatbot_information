package com.example.retrievalbased.retrievalBased.controller;

import com.example.retrievalbased.retrievalBased.model.User;
import com.example.retrievalbased.retrievalBased.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home/list.json")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
  @Autowired private UserService userService;

  @GetMapping(
      "/pageNo/{pageNo}/pageSize/{pageSize}/currentUserId/{currentUserId}/userClient/{userClient}")
  public Page<User> findAllByPage(
      @PathVariable int pageNo,
      @PathVariable int pageSize,
      @PathVariable int currentUserId,
      @PathVariable int userClient) {
    return userService.findAllByPage(pageNo, pageSize);
  }
}
