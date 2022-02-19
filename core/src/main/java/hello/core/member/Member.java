package hello.core.member;

public class Member {

    private Long id;
    private String name;
    private Grade grade;

    public Member(Long id) {
        this.id = id;
    }

    public Member(String name) {
        this.name = name;
    }

    public Member(Grade grade) {
        this.grade = grade;
    }
}
