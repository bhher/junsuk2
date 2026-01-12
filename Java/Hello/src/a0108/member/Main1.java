package a0108.member;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("==== 회원가입 ====");

        System.out.print("아이디입력 : ");
        String id = sc.nextLine(); //문자한줄 입력

        System.out.print("패스워드 입력 : ");
        String pw = sc.nextLine(); //문자한줄 입력

        System.out.print("이름 입력 : ");
        String name = sc.nextLine(); //문자한줄 입력

        //객체 생성(회원가입 완료)
        Member member = new Member(id, pw, name);
        System.out.println("\n===가입 완료 === ");
        member.showInfo();
        sc.close(); //scanner 종료

    }
}
