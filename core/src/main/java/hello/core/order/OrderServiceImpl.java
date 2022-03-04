package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPilicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    /*
    <수정 전>
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPilicy();
    */

    /*
    고정 할인 정책에서 정률 할인 정책으로 변경하려면
    private final DiscountPolicy discountPolicy = new FixDiscountPilicy(); 코드를
    private final DiscountPolicy discountPolicy = new RateDiscountPilicy();로 변경해야 함

    <문제점>
    역할과 구현을 충실하게 분리했고, 다형성을 활용하고 인터페이스와 구현 객체를 분리했지만
    OCP, DIP와 같은 객체지향 설계 원칙을 충실히 준수하지 않았음
    1.  DIP 위반
        - OrderServiceImpl은 DiscoutPolicy 인터페이스에 의존하면서 DIP를 지킨 것 같지만
        추상(인터페이스)뿐만 아니라 구현 클래스에도 의존하고 있음
            - 추상(인터페이스) 의존  : DiscountPolicy
            - 구현 클래스 : FixDiscountPolicy, RateDiscutPolicy
    2. OCP 위반
        - 지금 코드는 기능을 확장에서 변경하면 클라이언트 코드(OrderServiceImpl)에 영향을 줌
            - OrderServiceImpl에서 private final DiscountPolicy discountPolicy = new FixDiscountPilicy();를
            private final DiscountPolicy discountPolicy = new RateDiscountPilicy(); 로 변경하는 행위
    
    <해결방안>
    누군가가 클라이언트인 OrderServiceImpl에 DiscountPolicy의 구현 객체를 대신 생성하고 주입해주어야 함
    */

    // <수정후>
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    
    /*
    - OrderServiceImpl은 더 이상 MemoryMemberRepository와 FixDiscountPilicy를 의존하지 않음
        단지 MemberRepository, DiscountPolicy 인터페이스만을 의존함
    - OrderServiceImpl의 입장에서 생성자를 통해 어떤 구현객체가 들어올지(주입될지)는 알 수 없음
    - OrderServiceImpl의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부(AppConfig)에서 결정됨
    - OrderServiceImpl은 이제부터 의존관계에 대한 고민은 외부에 맡기고 실행에만 집중하면 됨
    */

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);

        // 할인에 관한 건 discountPolicy가 수행하기 때문에 단일 책임 원칙을 잘 지키고 있음
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
