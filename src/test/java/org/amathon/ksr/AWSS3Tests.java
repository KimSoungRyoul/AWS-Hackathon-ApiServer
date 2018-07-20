package org.amathon.ksr;

import lombok.extern.log4j.Log4j;
import org.amathon.ksr.application.S3Services;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
@Log4j
public class AWSS3Tests {

  @Autowired
  private S3Services s3Services;

  @Test
  public void contextLoads() throws Exception {

    // s3Services.uploadFile("testHello2.jpg", "C:\\Users\\KimSoungRyoul\\test.jpg");

    s3Services.downloadFile("testHello2.jpg");

  }
}
