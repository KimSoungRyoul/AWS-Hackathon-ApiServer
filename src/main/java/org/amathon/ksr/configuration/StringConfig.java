package org.amathon.ksr.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class StringConfig {


  @Bean
  @Profile("dev")
  public String dev() {
    return "C:\\Users\\KimSoungRyoul\\Documents\\ArachneProject\\arachneServer_fiileStorage";
  }

  @Bean
  @Profile("product")
  public String product() {
    return "/home/ubuntu/FileFolder";
  }
}
