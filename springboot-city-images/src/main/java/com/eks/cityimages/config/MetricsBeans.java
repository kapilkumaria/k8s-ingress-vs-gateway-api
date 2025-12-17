package com.eks.cityimages.config;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsBeans {

  @Bean
  public Counter cityImagesAddedCounter(MeterRegistry registry) {
    return Counter.builder("city_images.added.total")
        .description("Total number of cities added")
        .register(registry);
  }

  @Bean
  public Timer cityImagesListTimer(MeterRegistry registry) {
    return Timer.builder("city_images.list.duration")
        .description("Time taken to list cities")
        .publishPercentileHistogram(true)   // expose buckets + sum + count
        .register(registry);
  }
}
