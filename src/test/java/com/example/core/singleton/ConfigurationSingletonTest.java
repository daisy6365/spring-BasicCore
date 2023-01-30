package com.example.core.singleton;

import com.example.core.AppConfig;
import com.example.core.member.MemberRepository;
import com.example.core.member.MemberServiceImpl;
import com.example.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {
    @Test
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository1 = " + memberRepository1); // MemoryMemberRepository@553f1d75
        System.out.println("orderService -> memberRepository2 = " + memberRepository2); // MemoryMemberRepository@553f1d75
        System.out.println("memberRepository = " + memberRepository); // MemoryMemberRepository@553f1d75

        // 같음!!!!!
        assertThat(memberRepository1).isEqualTo(memberRepository2);
        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);

    }

    @Test
    void configurationDeep(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean.getClass() = " + bean.getClass()); //AppConfig$$EnhancerBySpringCGLIB$$9024176
    }
}
