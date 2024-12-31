package com.example.demo.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebFilter(urlPatterns = {"/api/v1/*"})
public class LoginFilter extends OncePerRequestFilter {
  @Override
  protected void doFilterInternal(
    final HttpServletRequest request,
    final HttpServletResponse response,
    final FilterChain filterChain
  ) throws ServletException, IOException {
    // headerからTokenを取得する
    final String tokenHeader = request.getHeader("X-AUTH-TOKEN");
    final String tokenPrefix = "Bearer ";

    if (tokenHeader == null || !tokenHeader.startsWith(tokenPrefix)) {
      // トークンなし
      // トークンが意図した形ではない
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      return;
    }

    try {
      final String token = tokenHeader.substring(tokenPrefix.length());

      // Tokenの検証と認証を行う
      final DecodedJWT decodedJwt = JWT
        .require(Algorithm.HMAC256("secret"))
        .build()
        .verify(token);

      // ログイン状態を設定する
      final String username = decodedJwt.getClaim("username").toString();
      SecurityContextHolder.getContext().setAuthentication(
        new UsernamePasswordAuthenticationToken(
          username, null, new ArrayList<>()
        )
      );
      filterChain.doFilter(request, response);
    } catch (TokenExpiredException e) {
      // verify()時のチェックで有効期限切れなど
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      response.getWriter().write(e.getMessage());
    }
  }
}
