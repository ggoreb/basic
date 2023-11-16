package com.example.basic.controller;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.basic.model.Article;
import com.example.basic.repository.ArticleRepository;

@Controller
public class DownloadController {
  @Autowired
  ArticleRepository articleRepository;

  @GetMapping("/download")
  public ResponseEntity<Resource> download(
    @RequestParam int id
  ) throws Exception {
    
    Optional<Article> opt = 
        articleRepository.findById(id);
    String fileInfo = "";
    if(opt.isPresent()) {
      Article article = opt.get();
      fileInfo = article.getFileInfo();
    }

    File file = new File(fileInfo);
    InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
    return ResponseEntity.ok()
        .header("content-disposition",
            "filename=" + URLEncoder.encode(file.getName(), "utf-8"))
        .contentLength(file.length())
        .contentType(MediaType.parseMediaType(
          "application/octet-stream"))
        .body(resource);
  }
}