package com.example.demo.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private final AuthenticationManager authenticationManager;

  public JwtAuthenticationFilter(final AuthenticationManager manager) {
    this.authenticationManager = manager;

    // ログインパスを設定
    setRequiresAuthenticationRequestMatcher(
      new AntPathRequestMatcher("/api/login", HttpMethod.POST.toString())
    );

    // ログイン用パラメータの設定
    setUsernameParameter("username");
    setPasswordParameter("password");

    this.setAuthenticationSuccessHandler((request, response, ex) -> {
      // ログイン成功時の挙動
      // JWTを生成してヘッダに設定する
      // レスポンスステータスをHttpServletResponse.SC_OKでレスポンスする
      final Instant now = Instant.now();
      final String token = JWT.create()
        .withIssuer("com.example.demo.test")            // 発行者
        .withIssuedAt(Date.from(now))                   // 発行時間
        .withExpiresAt(Date.from(now.plus(600, ChronoUnit.SECONDS)))  // 有効期限
        .withClaim("username", ex.getName())      // keyに対してvalueの設定。汎用的な様々な値を保持できる
        .sign(Algorithm.HMAC256("secret"));      // 利用アルゴリズムを指定してJWTを新規作成
      response.setHeader("X-AUTH-TOKEN", token);  // tokeをX-AUTH-TOKENというKeyにセットする
      response.setStatus(HttpServletResponse.SC_OK);
    });

    this.setAuthenticationFailureHandler((request, response, ex) -> {
      // ログイン失敗時の挙動
      // レスポンスステータスをHttpServletResponse.SC_UNAUTHORIZEDでレスポンスする
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    });
  }

  @Override
  public Authentication attemptAuthentication(
    final HttpServletRequest request,
    final HttpServletResponse response
  ) throws AuthenticationException {
    if (!request.getMethod().equals(HttpMethod.POST.toString())) {
      return null;
    }

    // ログインAPIのリクエスト内容を取得する
    final String username = obtainUsername(request);
    final String password = obtainPassword(request);

    // UserDetailsService.loadUserByUsername()が反応する
    return this.authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        username, password, new ArrayList<>()
      )
    );
  }
}
