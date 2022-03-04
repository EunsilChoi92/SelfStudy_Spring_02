package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPilicy implements DiscountPolicy {

    private int discountFixAmount = 1000; // 1000원 할인

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {   // Eunm 타입은 문자 비교라도 ==을 사용
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
