package com.example.core.order;

import com.example.core.annotation.MainDiscountPolicy;
import com.example.core.discount.DiscountPolicy;
import com.example.core.discount.FixDiscountPolicy;
import com.example.core.discount.RateDiscountPolicy;
import com.example.core.member.Member;
import com.example.core.member.MemberRepository;
import com.example.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
// annotation processor기능을 사용하여 컴파일 시점에 생성자를 자동으로 생성
// @RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    // 인터페이스만 의존하기 때문에 DIP 원칙 준수
    // final 값이 무조건 있어야 함
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // 생성자 주입
    @Autowired // MemberRepository DiscountPolicy 의존관계 자동 주입
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) { //쓰는곳에서도 생성자 자동주입
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);

        // 주문기능은 할인기능에 대해 관여 X, discountPolicy가 처리하고 결과만 받음
        // "단일책임에 대한 원칙" 설계
        int discountPrice = discountPolicy.discount(member, itemPrice);


        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // Singleton 테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
