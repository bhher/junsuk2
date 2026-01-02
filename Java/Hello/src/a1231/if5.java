package a1231;

public class if5 {
    public static void main(String[] args) {
        int num =7;
        if(num % 2 ==0){
            System.out.println(num + "은 짝수입니다.");
        } else {
        System.out.println("홀수");
        }

        //삼항연산자
        //조건식 ? 값1 : 값2;
        //조건식이 true면 값1, false면 값2
        String result = (num % 2 ==0) ? "짝수" : "홀수";
        System.out.println(num + "은 " + result + "입니다.");

    }
}