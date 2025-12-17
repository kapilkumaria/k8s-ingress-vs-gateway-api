package com.eks.cityimages.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CityController {

  @GetMapping("/canada")
  public String getCanadaPage() {
    return "forward:/canada.html";
  }

  @GetMapping("/paris")
  public String getParisPage() {
    return "forward:/paris.html";
  }

  @GetMapping("/losangeles")
  public String getLosAngelesPage() {
    return "forward:/losangeles.html";
  }

  @GetMapping("/newdelhi")
  public String getNewDelhiPage() {
    return "forward:/newdelhi.html";
  }

  @GetMapping("/")
  public String getHomePage() {
    return "forward:/index.html";
  }
}
