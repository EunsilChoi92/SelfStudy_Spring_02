package hello.core.member;

import java.util.Map;
import java.util.HashMap;

public class MemoryMemberRepository implements MemberRepository {

    // HashMap은 동시성 이슈가 발생할 수 있어서 ConcurrentHashMap 사용을 권장함
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
