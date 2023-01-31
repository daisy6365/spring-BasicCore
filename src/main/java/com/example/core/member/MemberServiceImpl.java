package com.example.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    // 구현 객체를 설정 안하면 NullPointException 발생
    // Cmd + Shift + Enter
    // 추상화에 의존 DIP원칙 준수
    private final MemberRepository memberRepository;

    // MemberRepository타입에 맞는 걸(MemoryMemberRepository) 찾아와서 의존관계 자동 주입
    @Autowired // 자동으로 의존관계 주입 ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // Singleton 테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
