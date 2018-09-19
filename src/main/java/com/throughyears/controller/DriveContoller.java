package com.throughyears.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/drive")
public class DriveContoller {
  @GetMapping
  public String about(Model model, OAuth2Authentication principal){
    model.addAttribute("userdetails", principal.getUserAuthentication().getDetails());

    return "googledrive";
  }
}
