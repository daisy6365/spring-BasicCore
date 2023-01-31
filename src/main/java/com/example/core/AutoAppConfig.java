package com.example.core;

import com.example.core.member.MemberService;
import com.example.core.member.MemberServiceImpl;
import com.example.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration // 설정정보
// @ComponentScan // 스프링 빈을 자동으로 등록
// @Component가 붙은 class를 자동으로 스프링 빈에 등록
// 필터링을 통해 빼고싶은 class들을 설정
// AppConfig.class와 TestConfig.class는 수동으로 등록하는 구조의 Class이므로 빼줌
@ComponentScan(
        // 탐색할 패키지의 시작 위치를 지정 -> 하는 이유 (전체 탐색은 시간이 오래걸림, 시간 효율성 위해)
        basePackages = "com.example.core.member",
        excludeFilters =  @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    // 자동과 똑같이 수동이 등록된다면? -> Overriding bean definition for bean 'memoryMemberRepository' 됨
    // but 스프링빈이 내부적으로 수십, 수백 개가 돌아가므로 꼬이게 됨 -> 최근에는 오류가 발생하도록 설정 됨
    // CoreApplication 실행 시
    // Consider renaming one of the beans or enabling overriding by setting spring.main.allow-bean-definition-overriding=true 에러 발생
//    @Bean(name = "memoryMemberRepository")
//    MemoryMemberRepository memoryMemberRepository(){
//        return new MemoryMemberRepository();
//    }
}
