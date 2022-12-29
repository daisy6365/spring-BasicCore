package com.example.core;

import com.example.core.discount.DiscountPolicy;
import com.example.core.discount.FixDiscountPolicy;
import com.example.core.discount.RateDiscountPolicy;
import com.example.core.member.MemberRepository;
import com.example.core.member.MemberService;
import com.example.core.member.MemberServiceImpl;
import com.example.core.member.MemoryMemberRepository;
import com.example.core.order.OrderService;
import com.example.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // application의 설정정보임을 밝힘
public class AppConfig {

    @Bean // spring 컨테이너에 spring bean으로 등록됨
    public MemberService memberService(){
        // MemoryMemberRepository으로 생성자 주입
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        // FixDiscountPolicy로 생성자 주입
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // FixDiscountPolicy -> RateDiscountPolicy로 변경하고 싶을때
    // 이곳만 수정하면 됨
    // SRP(단일책임원칙) OCP(개방폐쇄의 원칙) DIP(의존관계 역전)원칙 모두 준수
    @Bean
    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }


}
