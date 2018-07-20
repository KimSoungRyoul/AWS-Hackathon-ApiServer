package org.amathon.ksr.configuration;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfiguration {

  @Value("${jsa.aws.credentials.accessKey}")
  private String awsId;

  @Value("${jsa.aws.credentials.secretKey}")
  private String awsKey;

  /* @Value("${jsa.s3.region}")
   private String region;
 */
  @Bean
  public AmazonS3 s3client() {

    BasicAWSCredentials awsCreds = new BasicAWSCredentials(awsId, awsKey);
    AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
        .withRegion(Regions.AP_SOUTHEAST_1)
        .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
        .build();

    return s3Client;
  }
}