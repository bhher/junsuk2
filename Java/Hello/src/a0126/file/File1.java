package a0126.file;

import java.io.FileOutputStream;
import java.io.IOException;

public class File1 {
    public static void main(String[] args) throws IOException {
        FileOutputStream output = new FileOutputStream("c:/Users/TJ/out.txt");
        output.close();
        //c:/ 드라이브 루트는 엑세스거브
        //c:/Users/TJ/
        //c:/Users/본인계정/
    }
}
