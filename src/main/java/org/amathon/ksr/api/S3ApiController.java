package org.amathon.ksr.api;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import lombok.extern.log4j.Log4j;
import org.amathon.ksr.domain.Restaurant;
import org.amathon.ksr.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/files")
@Log4j
@MultipartConfig(
    location = "C:\\Users\\KimSoungRyoul\\Documents\\ArachneProject\\arachneServer_fiileStorage",
    maxFileSize = 1024 * 1024 * 10,
    fileSizeThreshold = 1024 * 1024,
    maxRequestSize = 1024 * 1024 * 20
)
public class S3ApiController {

//  private S3Services s3Services;

  @Autowired
  private RestaurantRepository restaurantRepository;


  private String imgDefaultUrl = "C:\\Users\\KimSoungRyoul\\Documents\\ArachneProject\\arachneServer_fiileStorage";


 /* @GetMapping("/{restaurantSerialNum}")
  public ResponseEntity<InputStreamResource> asdf(@PathVariable("restaurantSerialNum") String serialNum,@RequestParam("fileType")String fileType) {

    //return new ResponseEntity(new File("C:\\Users\\KimSoungRyoul\\download.jpg"), HttpStatus.OK);
    return download(serialNum, "testHello.jpg",fileType);
  }*/

  @PostMapping("/{serialNum}")
  @ApiImplicitParams({
      @ApiImplicitParam(value = "file", dataType = "file", name = "file")


  })
  public ResponseEntity asdf(HttpServletRequest request, @PathVariable long serialNum)
      throws Exception {

    Restaurant restaurant = restaurantRepository.findOne(serialNum);

    String fileName = "main";

    restaurant.setImgUrl(imgDefaultUrl + "/" + fileName);

    Part filePart = null;
    for (Part part : request.getParts()) {
      System.out.println(part);
      for (String headerName : part.getHeaderNames()) {
        System.out.println(headerName);
        System.out.println("-");
        System.out.println(part.getHeader(headerName));
        // To find out file name, parse header value of content-disposition
        // e.g. form-data; name="file"; filename=""
      }
      // Get a normal parameter
      if (part.getName().equals("name")) {
        String paramValue = getStringFromStream(part.getInputStream());
        System.out.println(paramValue);
        fileName = paramValue;
      }
      if (part.getName().equals("file")) {
        filePart = part; // Absolute path doesn't work.
      }
    }
    filePart.write(fileName);
    restaurantRepository.save(restaurant);

    return new ResponseEntity(HttpStatus.OK);
  }

  public String getStringFromStream(InputStream stream)
      throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
    StringBuilder sb = new StringBuilder();
    String line = null;

    try {
      while ((line = br.readLine()) != null) {
        sb.append(line + "\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        stream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return sb.toString();
  }


}
