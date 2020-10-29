package com.wenance.challenge;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("com.wenance.challenge")
public class Config {
   @Bean
   public RestTemplate restTemplate() {
      return new RestTemplate();
   }
}
