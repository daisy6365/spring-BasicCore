package com.example.core.order;

import com.example.core.discount.DiscountPolicy;
import com.example.core.discount.FixDiscountPolicy;
import com.example.core.discount.RateDiscountPolicy;
import com.example.core.member.Member;
import com.example.core.member.MemberRepository;
import com.example.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    // 인터페이스만 의존하기 때문에 DIP 원칙 준수
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // 생성자 주입
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
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
}
