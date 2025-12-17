package com.eks.cityimages.config;

import org.springframework.boot.web.server.MimeMappings;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MimeConfig {

  @Bean
  public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> mimeCustomizer() {
    return factory -> {
      MimeMappings mappings = new MimeMappings(MimeMappings.DEFAULT);
      mappings.add("avif", "image/avif"); // key: serve AVIF with correct MIME
      mappings.add("webp", "image/webp"); // optional
      factory.setMimeMappings(mappings);
    };
  }
}
