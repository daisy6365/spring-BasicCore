package com.example.core.member;

import java.util.HashMap;
import java.util.Map;

// 데이터베이스 확정이 안된 상태
// 메모리를 사용하기로 함
public class MemoryMemberRepository implements MemberRepository{

    // 동시성 이슈가 있기 때문에 원래는 ConcurrentHashMap 사용
    // https://applepick.tistory.com/124
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
