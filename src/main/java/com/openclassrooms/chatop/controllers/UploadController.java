package com.openclassrooms.chatop.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class UploadController {

  @Value("${file.upload.directory}")
  private String fileUploadDirectory;

  @GetMapping("/images/{fileName:.+}")
  public ResponseEntity<Resource> getImage(@PathVariable String fileName) {
    try {
      String decodedFileName = URLDecoder.decode(fileName, StandardCharsets.UTF_8);

      Path filePath = Paths.get(fileUploadDirectory).resolve(decodedFileName);
      Resource resource = new UrlResource(filePath.toUri());

      if (resource.exists() && resource.isReadable()) {
        return ResponseEntity.ok().body(resource);
      } else {
        return ResponseEntity.notFound().build();
      }
    } catch (MalformedURLException e) {
      return ResponseEntity.notFound().build();
    }
  }
}