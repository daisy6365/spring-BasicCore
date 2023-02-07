package com.example.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

// 로그를 출력하기 위한 클래스
@Component
// CLASS에 가짜 프록시 적용 -> 가짜프록시 클래스를 다른 빈에 주입
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) // http 요청당 하나씩 생성되고 요청이 끝나는 시점에 소멸
public class MyLogger {
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL){
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "] [" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init(){
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] reqeust scope bean create: " + this);
    }

    @PreDestroy
    public void close(){
        System.out.println("[" + uuid + "] reqeust scope bean close: " + this);
    }
}
