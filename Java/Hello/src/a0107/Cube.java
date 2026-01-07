package a0107;

public class Cube {
    public static void main(String[] args) {
        int n = 5; //한변의 길이
        int v = volume(n);

        System.out.printf("한 변의 길이가 %d인 정육면체 부피: %d ",n,v);
    }

    private static int volume(int n) { //입력변수
       return n * n * n; //반환값
    }
}
