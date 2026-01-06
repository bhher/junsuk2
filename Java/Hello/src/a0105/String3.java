package a0105;

public class String3 {
    public static void main(String[] args) {
        String str =  "Hello World";
        //chatAt(index) :특정 위치의 문자
        char ch = str.charAt(0); //H
        System.out.println("인덱스 0번 글자 " + ch);
         char ch2 = str.charAt(6); //H
        System.out.println("인덱스 6번 글자 " + ch2);

        //indexOf() : 문자열이나 문자의 첫 번째 위치
        int index1 = str.indexOf('o');   //4
        int index2 = str.indexOf("World");  // 6
        int index3 = str.indexOf('x');     // -1 (없으면 -1 반환)

        // lastIndexOf() : 마지막 위치
        int lastIndex = str.lastIndexOf('o');
        System.out.println("o 마지막 위치 : " + lastIndex); //7

        

    }
}
