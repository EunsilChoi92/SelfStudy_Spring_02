package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        /*
        - ApplicationContext를 스프링 컨테이너라고 함(ApplicationContext는 인터페이스)
        - 이제부터는 스프링 컨테이너를 통해서 객체를 생성하고 DI를 함
        - 스프링 컨테이너는 @Configuration이 붙은 AppConfig를 설정 정보로 사용함
            - 여기서 @Beean이라 적힌 메서드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록함
            - 이렇게 스프링 컨테이너에 등록된 객체를 스프링 빈이라고 함
        - 스프링 빈은 @Bean이 붙은 메서드의 명을 스프링 빈의 이름으로 사용함
        - 스프링 빈은 applicationContext.getBean() 메서드를 통해 찾을 수 있음
        - 스프링 컨테이너의 생성 과정
            1. 스프링 컨테이너 생성
            2. 스프링 빈 등록
            3. 스프링 빈 의존관계 설정 - 준비
            4. 스프링 빈 의존관계 설정 - 완료
         */

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());


        /*
        <스프링 전환 전>

        <수정 전 - DI가 이루어지기 전>
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());  // soutv + enter - 출력할 변수 선택 가능
        */

        /* <수정 후>
        AppConfig appConfig = new AppConfig();

        // MemoryMemberRepository를 사용하는 MemberServiceImpl return
        MemberService memberService = appConfig.memberService();

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
        */

    }
}
