package a0126.file;

import java.io.FileOutputStream;
import java.io.IOException;

public class File1_1 {
    public static void main(String[] args)  {
      try{
         FileOutputStream output = new FileOutputStream("c:/Users/TJ/out.txt");
        output.close();
      }catch( IOException e){
        System.out.println("파일 처리 중 오류 발생");
      }
      
    }
}
//IOException - checked Execption
// 파일경로 잘못되거나
//권한이 없음
//디스크 용량부족
//파일이 이미 열려있음