package com.example.basic.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IPCheckFilter implements Filter {
  @Override
  public void doFilter(
      ServletRequest request, ServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    log.debug("filter begin");
    HttpServletRequest req = (HttpServletRequest) request;
    String ip = request.getRemoteAddr();
    log.debug("ip : " + ip);

    String referer = ((HttpServletRequest) request).getHeader("Referer");
    log.debug("referer : " + referer);

    String userAgent = ((HttpServletRequest) request).getHeader("user-agent");
    log.debug("user-agent : " + userAgent);

    chain.doFilter(req, response);
    log.debug("filter end");
  }
}