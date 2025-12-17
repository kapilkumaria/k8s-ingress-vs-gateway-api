package com.eks.cityimages.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityApiController {
  private static final Logger log = LoggerFactory.getLogger(CityApiController.class);

  private final Counter addedCounter;
  private final Timer listTimer;

  // Constructor injection of the beans we created
  public CityApiController(Counter cityImagesAddedCounter,
                           Timer cityImagesListTimer) {
    this.addedCounter = cityImagesAddedCounter;
    this.listTimer = cityImagesListTimer;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<String> list() {
    // Wrap logic in the timer so Micrometer tracks duration
    return listTimer.record(() -> {
      log.info("Listing cities");
      return List.of("Calgary", "Toronto", "Vancouver");
    });
  }

  @PostMapping(consumes = { MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_VALUE },
               produces = MediaType.TEXT_PLAIN_VALUE)
  public String add(@RequestBody String name) {
    addedCounter.increment();
    log.info("Added city {}", name);
    return name;
  }
}
