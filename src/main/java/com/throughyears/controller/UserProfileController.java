package com.throughyears.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProfileController {
  @RequestMapping("/user")
  public Principal user(Principal principal) {
    return principal;
  }
}
