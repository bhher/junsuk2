package a0121.ex03;

import java.util.ArrayList;

public class ScoreManager {

    public static void addScore(ArrayList<Integer> list, int score) {
       list.add(score);
    }

    public static double getAverage(ArrayList<Integer> list) {
        if(list.isEmpty()){
            return 0.0;
        }

        int sum = 0; //합계 구할 변수 초기화
        for(int score : list){
            sum = sum + score; //sum+=score;
        }
        return (double) sum / list.size();
    }
    //합격자수 계산
    public static int countPass(ArrayList<Integer> list, int passScore) {
       int count = 0;
        for(int score : list){
            if(score >= passScore){ //60점이상이면
                count++; //count = count + 1;
            }
        }
        return count;
    }

    public static int getMaxScore(ArrayList<Integer> list) {
        //85 92 78 65 88 
        if(list.isEmpty()){
            return -1;
        }

        int max = list.get(0);
        for(int i = 1; i < list.size();i++){
            if(list.get(i) > max){
                max = list.get(i);
            }
        }
        return max;

    }
    
}
