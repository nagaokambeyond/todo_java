package com.example.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LogAspect {
  @Around("execution(* com.example.demo.api.*..*(..))")
  public Object monitor(ProceedingJoinPoint joinPoint) throws Throwable {
    final long start = System.currentTimeMillis();  // メソッド開始前のシステム時刻
    final Object proceed = joinPoint.proceed();     // メソッド実行
    final long end = System.currentTimeMillis();    // メソッド終了後のシステム時刻
    log.info("{} {}   method latency: {} ms."       // メソッドの実行時間の出力
            , joinPoint.getTarget().getClass().getName()
            , joinPoint.getSignature().getName()
            , end - start);
    return proceed;
  }
}
