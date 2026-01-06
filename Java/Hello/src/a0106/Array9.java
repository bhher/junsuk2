package a0106;
//행별합계 구하기
public class Array9 {
    public static void main(String[] args) {
        int[][] scores ={
              {90, 80, 70},
              {85, 95, 88}
        };
        
        for(int i = 0; i < scores.length; i++){
            int sum = 0; //행마다 새로  0으로 초기화
            for(int j = 0; j < scores[i].length;j++){
                sum += scores[i][j];
            }
            System.out.println("행 합 : " + sum);
        }

    }
}
