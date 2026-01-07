package a0107;

public class PrintfEx1 {
    public static void main(String[] args) {
        int age = 20;
        String male = "여성" ; 
        System.out.println("당신의 나이는 " +age+" 이고 성별은 " +male+" 입니다.");
        System.out.printf("당신의 나이드 %d 이고 성별은 %s 입니다.\n",age,male);
        // %d 정수형(age)
        // %s 문자열(male)
        // \n 줄바꿈


        
    }
}
