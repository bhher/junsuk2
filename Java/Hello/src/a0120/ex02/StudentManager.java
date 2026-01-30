package a0120.ex02;

import java.util.ArrayList;

public class StudentManager {

    //학생추가 메서드
    public static void addStudent(ArrayList<Student> list, String name, int age, int score) {
        list.add(new Student(name, age, score));
    }

    public static void printAll(ArrayList<Student> list) {
        for(Student s : list){
            System.out.println(s);
        }
    }
    //학생검색
    public static Student findStudent(ArrayList<Student> list, String sname) {
         for(Student s : list){
            if(s.name.equals(sname)){
                return s;
            }
        }
        return null;
    }

    public static double getAverageScore(ArrayList<Student> list) {
       if(list.isEmpty()){
        return 0.0;
       }
       int sum = 0;
       for(Student s : list){
          sum += s.score;
       }
       return (double) sum / list.size(); //초점을 인원수로 나눈다. 
    }

    public void addStudent(String name, int score) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addStudent'");
    }
    
}
