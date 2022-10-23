package com.example.demo.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.http.HttpMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class JsonAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private final AuthenticationManager authenticationManager;

  public JsonAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;

    // ログインパスを設定
    setRequiresAuthenticationRequestMatcher(
      new AntPathRequestMatcher("/api/login", HttpMethod.POST.toString())
    );

    // ログイン用パラメータの設定
    setUsernameParameter("username");
    setPasswordParameter("password");

    this.setAuthenticationSuccessHandler((req, res, ex) -> {
      // ログイン成功時、トークンをヘッダに付けて終了する
      String token = JWT.create()
        .withIssuer("com.volkruss.toaru")             // 発行者
        .withClaim("username", ex.getName())    // keyに対してvalueの設定。汎用的な様々な値を保持できる
        .sign(Algorithm.HMAC256("secret"))     // 利用アルゴリズムを指定してJWTを新規作成
      ;
      res.setHeader("X-AUTH-TOKEN", token);     // tokeをX-AUTH-TOKENというKeyにセットする
      res.setStatus(HttpServletResponse.SC_OK);
    });

    this.setAuthenticationFailureHandler((req, res, ex) -> {
      // ログイン失敗時
      res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    });
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    if (!request.getMethod().equals(HttpMethod.POST.toString())){
      return null;
    }
    String username = obtainUsername(request);
    String password = obtainPassword(request);
    // これでデフォルトのProviderを利用しつつ、ユーザーレコードの取得に関してはUserDetailsServiceの実装クラスのloadUserByUsernameを利用する
    return this.authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>())
    );
  }
}
