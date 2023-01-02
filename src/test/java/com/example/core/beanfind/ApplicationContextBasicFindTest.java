package com.example.core.beanfind;

import com.example.core.AppConfig;
import com.example.core.member.MemberService;
import com.example.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService = "+ memberService); // memberService = com.example.core.member.MemberServiceImpl@3e34ace1
        System.out.println("memberService = "+ memberService.getClass()); // memberService = class com.example.core.member.MemberServiceImpl

        // 인터페이스의 구현체가 대상
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름없이 타입으로만 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        System.out.println("memberService = "+ memberService); // memberService = com.example.core.member.MemberServiceImpl@3e34ace1
        System.out.println("memberService = "+ memberService.getClass()); // memberService = class com.example.core.member.MemberServiceImpl

        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2(){
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        System.out.println("memberService = "+ memberService); // memberService = com.example.core.member.MemberServiceImpl@3e34ace1
        System.out.println("memberService = "+ memberService.getClass()); // memberService = class com.example.core.member.MemberServiceImpl

        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회X")
    void disableFindBeanByName(){
        // 해당이름의 bean이 없으므로 예외가 터짐
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("XXXXX", MemberService.class));
    }
}
