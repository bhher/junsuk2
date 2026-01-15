package a0114.abstract1;

class Dog extends Animal {
//자식 클래스
    public Dog(String name) {
        super(name);
    }

    @Override
    void sound() {
        System.out.println(name + "이(가) 멍멍 짖습니다.");
    }
    
    
}
