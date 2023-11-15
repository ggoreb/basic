package com.example.basic.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.basic.model.FileInfo;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;

@Controller
public class UploadController {

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
    String result = "";
    String oName = mFile.getOriginalFilename();
    int 점의위치 = oName.indexOf(".");
    String 파일명 = oName.substring(0, 점의위치);
    String 확장자 = oName.substring(점의위치);
    oName = 파일명 + "__" + System.currentTimeMillis() + 확장자;

    try {
      mFile.transferTo(new File("c:/springboot/" + oName));
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
