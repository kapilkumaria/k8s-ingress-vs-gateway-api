package com.eks.cityimages;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StaticAssetsTest {

  @LocalServerPort int port;
  @Autowired TestRestTemplate rest;

  @Test
  void servesAvifWithCorrectMime() {
    ResponseEntity<byte[]> resp =
        rest.getForEntity("http://localhost:" + port + "/images/paris.avif", byte[].class);

    assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(resp.getHeaders().getContentType().toString()).isEqualTo("image/avif");
    assertThat(resp.getBody()).isNotEmpty();
  }
}
