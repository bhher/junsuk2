package a0114.interface1;

public class InterfaceTest {
    public static void main(String[] args) {
        Flayable f1 = new Bird();
        Flayable f2 = new AirPlane();

        f1.fly();
        f2.fly();

    }
}
//  추상과 인터페이스 차이
// 키워드 -   추상- abstract        인터 - interface
// 상속/구현   extends              implements
// 다중 상속   1개만               여러개 가능
// 필드       일반변수              public stact final  상수
//메서      일반 + 추상            추상 메서드
//생성자      있음                 없음
// 목적      기능 + 규칙            규칙(표준)


