package com.example.core;

import com.example.core.member.Grade;
import com.example.core.member.Member;
import com.example.core.member.MemberService;
import com.example.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        // AppConfig의 @Configuration으로 만들어진 @Bean들을 스프링 컨테이너에 담아서 관리해줌
        // Log가 찍힘
        // 위에 5개 -> 스프링이 내부적으로 필요해서 등록한 bean
        // 아래 것들 -> 스프링 컨테이너에 등록된 bean
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        // Cmd + Option + V
        Member memberA = new Member(1L, "memberA", Grade.VIP);
        memberService.join(memberA);

        Member findMember = memberService.findMember(1L);
        System.out.println("new Member = " + memberA.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
