package a0116;

public class Sample2_1 {
    public static void main(String[] args) {
        Sample2_1  sample = new Sample2_1();
        int c;
        try {
            c = 4 / 0 ;
          // sample.shouldRun(); 이코드는 실행안됨
        } catch (ArithmeticException e) {
            c = - 1; //예외가 발생하여 이 문자 수행
        }finally{
            //예외와 상관없이 무조건 실행
            sample.shouldRun();
        }

        System.out.println(c);
    }
    private void shouldRun() {
       System.out.println(" ok thank");
    }
}
