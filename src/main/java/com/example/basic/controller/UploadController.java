package com.example.basic.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.basic.model.Article;
import com.example.basic.model.FileInfo;
import com.example.basic.repository.ArticleRepository;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;

@Controller
public class UploadController {

  @GetMapping("/article")
  public String article(Model model) {
    List<Article> list = 
      articleRepository.findAll();
    model.addAttribute("list", list);
    return "article/list";
  }

  @GetMapping("/article/add")
  public String articleAdd() {
    return "article/add";
  }

  @Autowired
  ArticleRepository articleRepository;

  @PostMapping("/article/add")
  public String articleAddPost(
    @ModelAttribute Article article,
    @RequestParam("file") MultipartFile mFile
  ) {
    SimpleDateFormat sf =
          new SimpleDateFormat("yyyy-MM-dd");
    Date now = new Date();
    String date = sf.format(now);

    // 나중에 추가해야 될 기능
    // 1. 오늘 날짜 폴더 생성하기
    File f = new File("c:/files/" + date);
    if(!f.exists()) {
      f.mkdirs();
    }
    // 2. 중복 파일명 처리하기
    String oName = mFile.getOriginalFilename();
    
    File isFile = new File("c:/files/" + date + "/" + oName);
    if(isFile.exists()) {
      int 점의위치 = oName.lastIndexOf(".");
      String 파일명 = oName.substring(0, 점의위치);
      String 확장자 = oName.substring(점의위치);
      oName = 파일명 + "__" + System.currentTimeMillis() + 확장자;
    }

    try {
      mFile.transferTo(new File("c:/files/" + date + "/" + oName));
    } catch (IllegalStateException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    article.setFileInfo("c:/files/" + date + "/" + oName);
    articleRepository.save(article);
    return "article/add";
  }


  @GetMapping("/upload1")
  public String upload1() {
    return "upload1";
  }

  @PostMapping("/upload1")
  @ResponseBody
  public String upload1Post(
      MultipartHttpServletRequest mRequest,
      @RequestParam String a) {
    Iterator<String> iter = mRequest.getFileNames();
    while (iter.hasNext()) {
      String inputName = iter.next(); // input 태그의 name 값
      List<MultipartFile> mFiles = mRequest.getFiles(inputName);
      for (MultipartFile mFile : mFiles) {
        System.out.println(mFile.getOriginalFilename());
      }
    }

    return "업로드 완료";
  }

  @GetMapping("/upload2")
  public String upload2() {
    return "upload2";
  }

  @PostMapping("/upload2")
  @ResponseBody
  public String upload2(
    @RequestParam("file") MultipartFile mFile) {
    // 내일.. 중복파일이 존재하는지 검사
    // 업로드될 폴더가 없다면 폴더 생성
    // a.jpg -> a.jpg48425987345
    //          a48425987345.jpg
    String result = "";
    String oName = mFile.getOriginalFilename();

    SimpleDateFormat sf =
          new SimpleDateFormat("yyyy-MM-dd");
    Date now = new Date();
    String date = sf.format(now);
    System.out.println(date);

    File f = new File("c:/files/" + date);
    if(!f.exists()) {
      f.mkdirs();
    }

    File isFile = new File("c:/files/" + date + "/" + oName);
    if(isFile.exists()) {
      int 점의위치 = oName.lastIndexOf(".");
      String 파일명 = oName.substring(0, 점의위치);
      String 확장자 = oName.substring(점의위치);
      oName = 파일명 + "__" + System.currentTimeMillis() + 확장자;
    }

    try {
      //                         c:/files/2023-11-16/a.jpg
      mFile.transferTo(new File("c:/files/" + date + "/" + oName));
    } catch (IllegalStateException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    result += oName + "<br>" + mFile.getSize();
    return result;
  }

  @GetMapping("/upload3")
  public String upload3() {
    return "upload3";
  }

  @PostMapping("/upload3")
  @ResponseBody
  public String upload3Post(@ModelAttribute FileInfo info) {
    String result = "";
    String oName = info.getFile().getOriginalFilename();
    result += oName + "<br>" + info.getFile().getSize();
    return result;
  }
} 
