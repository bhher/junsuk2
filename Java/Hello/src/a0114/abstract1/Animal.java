package a0114.abstract1;

abstract class Animal { //public -> abstract
    String name;

    public Animal(String name) {
        this.name = name;
    }
    abstract void sound(); //반드시구현 -> 추상메서드
    public void eat(){ //일반메서드
        System.out.println(name + "이(가) 먹습니다.");
    }
    
}
