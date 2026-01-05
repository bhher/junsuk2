package a0105;

import java.util.Random;

public class Math1 {
    public static void main(String[] args) {
        System.out.println(Math.max(10,20));
        System.out.println(Math.min(10,20));
        System.out.println(Math.round(3.6)); //반올림
        System.out.println(Math.ceil(3.1)); //욜림
        System.out.println(Math.floor(3.9));//내림
        double r = Math.random();
        int k = (int)(r * 45)+1;
        System.out.println("랜덤한 숫자 *45 : " + k );
        Random random = new Random();
        int n = random.nextInt(45);
        // 0~44 사이의 랜덤 숫자 선택
        System.out.println("랜덤한 숫자1 *45 : " + n );

    }
}
