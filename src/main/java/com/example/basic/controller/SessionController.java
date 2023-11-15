package com.example.basic.controller;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.basic.model.Join;
import com.example.basic.model.Team;
import com.example.basic.model.User;
import com.example.basic.repository.JoinRepository;
import com.example.basic.repository.TeamRepository;
import com.example.basic.util.EncryptUtil;

import jakarta.servlet.http.HttpSession;

@Controller
public class SessionController {
  @Autowired
  HttpSession session;

  @Autowired EncryptUtil encryptUtil;
  @Autowired PasswordEncoder passwordEncoder;

  @GetMapping("/join")
  public String join() {
    return "join";
  }

  @Autowired JoinRepository joinRepository;
  @PostMapping("/join")
  public String joinPost(@ModelAttribute Join join) {
    String pw = join.getPw();
    String encodedPw = passwordEncoder.encode(pw);
    join.setPw(encodedPw);

    // 가입일자 추가하기
    join.setJoinDate(LocalDateTime.now().toString());

    joinRepository.save(join);

    return "join";
  }

  @GetMapping("/logout")
  public String logout() {
    session.removeAttribute("email");
    session.removeAttribute("age");
    session.removeAttribute("user");
    session.invalidate();
    return "login";
  }

  @GetMapping("/login")
  public String login(Model model) throws NoSuchAlgorithmException {
    String raw = "password1234";
    MessageDigest md = MessageDigest.getInstance("SHA-256");

    md.update(raw.getBytes());
    String hex = String.format("%064x", new BigInteger(1, md.digest()));
    System.out.println("raw의 해시값 : "+hex);


    session.setAttribute("email", "abc@abc.com");
    session.setAttribute("age", 40);

    // EncryptUtil util = new EncryptUtil();
    // util.encrypt(null, null, null, null);

    String plainText = "Hello, MadPlay!";

    String result2 = passwordEncoder.encode(plainText);
    model.addAttribute("result2", result2);

    String specName = "AES/CBC/PKCS5Padding";
    SecretKey key;
    try {
      key = encryptUtil.getKey();
      IvParameterSpec ivParameterSpec = encryptUtil.getIv();
      String result = 
          encryptUtil.encrypt(specName, key, ivParameterSpec, plainText);
      model.addAttribute("result", result);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "login";
  }

  @PostMapping("/login")
  @ResponseBody
  public String loginPost(
    @ModelAttribute Join join,
    User user
  ) {
    session.setAttribute("user", user);

    // 왜 비밀번호는 함께 조회하지 않는가??
    String id = join.getId();
    
    // boolean isExist = joinRepository.existsById(id);
    // if(isExist) { }

    Optional<Join> opt = joinRepository.findById(id);
    if (opt.isPresent()) {
      Join dbJoin = opt.get();
      String encodedPw = dbJoin.getPw(); // 암호화된 비밀번호 (DB 저장)
      String pw = join.getPw(); // 로그인하는 사용자가 입력한 비밀번호
      
      boolean isMatch = passwordEncoder.matches(pw, encodedPw);

      if(isMatch) { // 로그인 성공
        session.setAttribute("user", dbJoin);
        return "로그인되었습니다.";
        // return "redirect:/main";
      } else { // 로그인 실패
        return "아이디와 비밀번호를 확인해주세요.";
        // return "redirect:/login";
      }
    } else {
      return "아이디와 비밀번호를 확인해주세요.";
    }
    

  }

  @GetMapping("/main")
  public String main() {
    Object obj = session.getAttribute("email");
    String email = (String) obj;
    System.out.println(email);

    Integer age = (Integer) session.getAttribute("age");
    System.out.println(age);

    Join user = (Join) session.getAttribute("user");
    System.out.println(user);

    return "main";
  }

  @GetMapping("/joinAjax")
  @ResponseBody
  public String joinAjax(
    @RequestParam String id
  ) {
    boolean isExist = joinRepository.existsById(id);
    return isExist + "";
  }

  @GetMapping("/team/add")
  public String teamAdd() {
    return "teamAdd";
  }

  @Autowired TeamRepository teamRepository;

  @PostMapping("/team/add")
  @ResponseBody
  public String teamAddPost(
    @ModelAttribute Team team
  ) {
    teamRepository.save(team);
    return "등록완료";
  }
}









