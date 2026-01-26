package a0121.ex04;

public class StringUtil {

    //모음의 문자세기
    public static int countVowels(String text) {
        
        int count = 0;
        // Hello
        // hello
        String lowerText = text.toLowerCase(); //매개변수 text 를 소문자변환
        for(int i=0; i < lowerText.length(); i++){
          char ch =  lowerText.charAt(i); //charAt(0) 첫글자 charAt(1)두번째글
            if(ch == 'a' || ch == 'e' || ch== 'i' || ch == 'o' || ch == 'u'){
                count++;
            }
        }
        return count;

    }

    public static String toUpperCase(String text) {
        if(text == null){
            return null;
        }
      //Hello World
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < text.length();i++){
            char ch =  text.charAt(i);
            if(ch >= 'a' && ch <='z'){ //소문자이면
                sb.append((char)(ch-32)); //대문자로 변환 (ASC 코드 차이 : 32)
            }else{
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static boolean containsWord(String text, String word) {
         if(text == null || word == null){
            return false;
        }
        return text.contains(word); //contains 단어포함 여부

    }

    public static String replaceChar(String text, char oldChar, char newChar) {
        if(text == null ){
            return null;
        }
        //Hello
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < text.length();i++){
            char ch =  text.charAt(i);
            if(ch == oldChar){ //소문자이면
               sb.append(newChar);
            }else{
                sb.append(ch);
            }
        }
        return sb.toString();

    }
    
}
