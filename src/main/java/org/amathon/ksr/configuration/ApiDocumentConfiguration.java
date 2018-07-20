package org.amathon.ksr.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
//@EnableAutoConfiguration
//@Import(BeanValidatorPluginsConfiguration.class)
public class ApiDocumentConfiguration {

  @Bean
  public Docket swaggerSpringMvcPlugin() {

    return new Docket(DocumentationType.SWAGGER_2)

        .apiInfo(apiInfo())
        .select()

        .apis(RequestHandlerSelectors.basePackage("org.amathon.ksr.api"))

        //.paths(PathSelectors.ant("/api"))
        .paths(PathSelectors.any())
        .build();

  }

  private ApiInfo apiInfo() {
    Contact contact = new Contact("Amathon section 9",
        "https://github.com/KimSoungRyoul/AWS-Hackathon-ApiServer",
        "KimSoungRyoul@gmail");
    String license = "본 프로젝트는 라이센스 없음.";
    String licenseUrl = "http://www.apache.org/licenses/LICENSE-2.0";
    String title = "Amathon API Document ";
    String description = "Amathon 프로젝트 서버 API문서. \" /api가 기본경로로 붙어있다 참고!!!!\" ";

    return new ApiInfoBuilder()
        .contact(contact)
        .license(license)
        .licenseUrl(licenseUrl)
        .title(title)
        .description(description)
        .version("ver 1.0")
        .termsOfServiceUrl("2018-07-20")

        .build();


  }

}