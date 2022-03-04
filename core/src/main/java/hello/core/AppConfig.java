package hello.core;

import hello.core.discount.FixDiscountPilicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

// 애플리케이션의 전체 동작 방식을 구성(confing)하기 위해 구현 객체를 생성하고 연결하는 책임을 가지는 설정 클래스
public class AppConfig {

    /*
    1. AppConfig는 애플리케이션의 실제 동작에 필요한 구현 객체를 생성함
        - MemberServiceImpl, MemoryMemberRepository, OrderServiceImpl, FixDiscountPilicy
    2. AppConfig는 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결)해줌
        - MemberServiceImpl -> MemoryMemberRepository
        - OrderServiceImpl  -> MemoryMembeRepository, FixDiscountPolicy
    */

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository()); // 생성자 주입
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPilicy());
    }
}
