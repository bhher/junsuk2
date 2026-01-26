package a0120.ex02;

public class Student {
     String name ;
    int age;
    int score;
    public Student(){    
    }
    public Student(String name, int age, int score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    @Override
    public String toString() {
        return "name : " + name + ", age : " + age + ", score : " + score ;
    }
   
   
    


    
}
