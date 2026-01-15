package a0115;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class ArrayList1 {
    public static void main(String[] args) {
        //컬렉션
        //<데이터형식> -> 제네릭
        ArrayList<Integer> arrList = new ArrayList<Integer>();
        //add() 메소드를 이용한 요소저장
        arrList.add(40); 
        arrList.add(20); 
        arrList.add(30); 
        arrList.add(10); 

        //for 문과 get() 메소드 이용해서 요소의  출력
        //배열 arrList.length -> 리스트에서는   arrList.size() 갯수
        for(int i = 0;  i < arrList.size(); i++){
            System.out.print(arrList.get(i) + " ");
        }
        System.out.println();
        //remove() 메소드를 이용한 요소제거
        arrList.remove(1);  //0,1,2,3 실제로 2번째 데이터가 삭제

        //Enhanced for문 
        for(int e : arrList){
            System.out.print(e + " ");
        }
         System.out.println();
        //컬레션 정렬제공
        Collections.sort(arrList);

  // iterator() 메소드와 get() 메소드를 이용한 요소의 출력
		Iterator<Integer> iter = arrList.iterator();
		while (iter.hasNext()) {
			System.out.print(iter.next() + " ");
		}
		System.out.println();
        //arrayList 수정가능
        //set() 메소드를 이용한 요소의 변경(수정)
        arrList.set(0,20);

      //Enhanced for문 
        for(int e : arrList){
            System.out.print(e + " ");
        }
         System.out.println();

        //size() 메소드 이용한 요소의 총 갯수
        System.out.println("리스트의 크기 : " + arrList.size());
        
    }
}
