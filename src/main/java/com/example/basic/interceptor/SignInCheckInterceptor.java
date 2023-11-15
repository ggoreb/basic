package com.example.basic.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.basic.model.Join;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SignInCheckInterceptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response,
      Object handler) throws Exception {
    log.debug("preHandle");
    HttpSession session = 
       request.getSession();
    Join user = 
      (Join) session.getAttribute("user");
    if (user == null) {
      response.sendRedirect("/login");
      return false;
    }
    return true;
  }

  @Override
  public void postHandle(
      HttpServletRequest request, HttpServletResponse response,
      Object handler, ModelAndView modelAndView) throws Exception {
    log.debug("postHandle");
  }

  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {
    log.debug("afterCompletion");
  }
}
