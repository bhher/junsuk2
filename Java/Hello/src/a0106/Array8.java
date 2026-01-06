package a0106;

public class Array8 {
    public static void main(String[] args) {
        //모든 요소의 합
         int[][] arr = {
            {10, 20},
            {30, 40},
            {50, 60}
        };

         //모든 요소의 합
        //  int[][] arr = {
        //     arr[0][0]= 10 arr[0][1]=20
        //    arr[1][0]= 30 arr[1][1]=40
        //     arr[2][0]= 50 arr[2][1]=60
        // };
        int sum = 0;
        // for(int i=0; i < 3; i++){ //행
        //     for(int j = 0 ; j < 2;j++){ //열
        //        sum = sum + arr[i][j];
        //     }           
        // }
  for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                sum += arr[i][j];
            }
        }


        System.out.println("합 : " + sum);
    }
}
