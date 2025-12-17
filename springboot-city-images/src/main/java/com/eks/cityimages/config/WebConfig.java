// src/main/java/com/eks/cityimages/config/WebConfig.java
package com.eks.cityimages.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Override public void addResourceHandlers(ResourceHandlerRegistry r) {
    r.addResourceHandler("/images/**")
     .addResourceLocations("classpath:/static/images/");
  }
}