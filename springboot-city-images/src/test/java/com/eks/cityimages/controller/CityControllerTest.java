package com.eks.cityimages.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CityController.class)
class CityControllerTest {

  @Autowired private MockMvc mvc;

  @Test void canada_forwards_to_html() throws Exception {
    mvc.perform(get("/canada"))
       .andExpect(status().isOk())
       .andExpect(forwardedUrl("/canada.html"));
  }

  @Test void paris_forwards_to_html() throws Exception {
    mvc.perform(get("/paris"))
       .andExpect(status().isOk())
       .andExpect(forwardedUrl("/paris.html"));
  }

  @Test void losangeles_forwards_to_html() throws Exception {
    mvc.perform(get("/losangeles"))
       .andExpect(status().isOk())
       .andExpect(forwardedUrl("/losangeles.html"));
  }

  @Test void newdelhi_forwards_to_html() throws Exception {
    mvc.perform(get("/newdelhi"))
       .andExpect(status().isOk())
       .andExpect(forwardedUrl("/newdelhi.html"));
  }

  @Test void root_forwards_to_index() throws Exception {
    mvc.perform(get("/"))
       .andExpect(status().isOk())
       .andExpect(forwardedUrl("/index.html"));
  }
}
