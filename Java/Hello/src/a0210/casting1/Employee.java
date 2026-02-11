package a0210.casting1;

//직원 관리시스템(부모, 추상클래스)
abstract class Employee {
    protected String name;
    protected int id;
    protected double baseSalary;

    public Employee(String name, int id, double baseSalary) {
        this.name = name;
        this.id = id;
        this.baseSalary = baseSalary;
    }

    //추상메서드 각직원 타입마다 다른 계산 방식
    abstract double calculateSalary();

    public String getName() {
        return name;
    }

        
    // 공통 메서드
    void printInfo() {
        System.out.println("ID: " + id + ", 이름: " + name + 
                          ", 기본급: " + baseSalary + 
                          ", 실급여: " + calculateSalary());
    }
     
   

}



//abstract ?
//직원마다 급여 계산방식이 다르기때문
//부모는 '규칙'만 정함
// 추상메서드는 자식에서 반드시 구현
