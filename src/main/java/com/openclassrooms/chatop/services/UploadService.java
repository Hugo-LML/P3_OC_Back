package com.openclassrooms.chatop.services;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {

  @Value("${file.upload.directory}")
  private String fileUploadDirectory;
  private String serverLocation = "http://localhost:8080/";
  
  public String storeFile(MultipartFile file) throws IOException {
    String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
    Path filePath = Paths.get(fileUploadDirectory).resolve(fileName);
    file.transferTo(filePath.toFile());
    return serverLocation + fileName;
  }
  
}
