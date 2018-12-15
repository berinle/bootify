package com.berinle.bootify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@EnableDiscoveryClient
@SpringBootApplication
public class BootifyApplication {

  public static void main(String[] args) {
    SpringApplication.run(BootifyApplication.class, args);
  }

}

@RestController
class BootifyController {
  @GetMapping("/date")
  public ResponseEntity<?> getDate() {
    return new ResponseEntity<>(new Date(), HttpStatus.OK);
  }

  @GetMapping("/jokes")
  public ResponseEntity<?> getJokes(@RequestParam(required = false, defaultValue = "3") Integer size) {
    RestTemplate rest = new RestTemplate();
    int fetchSize = 3;
    if (size != null) {
      fetchSize = size;
    }
    return rest.getForEntity("http://api.icndb.com/jokes/random/{fetchSize}?exclude=[nerdy,explicit]", Object.class, fetchSize);
  }
}

