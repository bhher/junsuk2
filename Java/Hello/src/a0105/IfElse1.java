package a0105;

import java.util.Scanner;

public class IfElse1 {
    public static void main(String[] args) {
        //int avg = 85; //int 정수 저장
        //키보드입력
        //Scanner 객체를 생성
        Scanner sc = new Scanner(System.in);
        System.out.print("점수입력하세요 : ");
        int avg = sc.nextInt(); //숫자로 점수를 입력
        //nextDouble() - 실수
        //next() - 한단어
        //nextLine() - 한줄(문자열)
        //next().charAt(0) - 문자 1개 
        
        char hak;
        //한 개의 문자 를 저장하는 자료형(char)
        //반드시 저장(대입)시 작은따옴표 
        if(avg >= 90){
            hak = 'A';
        }else if(avg >= 80){
            hak = 'B';
        }else if(avg > 70){
            hak = 'C';
        }else if(avg > 60){
            hak = 'D';
        }else{
            hak = 'F';
        }
        System.out.println("당신의 학점은 : " + hak + "입니다.");
    
    }
}
