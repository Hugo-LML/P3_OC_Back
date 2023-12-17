package com.openclassrooms.chatop.services;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {

  @Value("${file.upload.directory}")
  private String fileUploadDirectory;
  @Value("${server.location.images}")
  private String serverLocation;
  
  public String storeFile(MultipartFile file) throws IOException {
    if (file.isEmpty() || file.getSize() > 5 * 1024 * 1024 || !isImage(file)) {
      return null;
    }

    Random random = new Random();
    String randomNumber = String.valueOf(random.nextInt(900000) + 100000);
    
    String fileName = System.currentTimeMillis() + "_" + randomNumber + ".png";
    Path filePath = Paths.get(fileUploadDirectory).resolve(fileName);
    file.transferTo(filePath.toFile());
    return serverLocation + fileName;
  }
  
  private boolean isImage(MultipartFile file) {
    String contentType = file.getContentType();
    if (StringUtils.hasText(contentType)) {
      return contentType.startsWith("image/");
    }
    return false;
  }

}
