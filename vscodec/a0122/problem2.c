#include <stdio.h>

//평균이상인 값의 개수 정답
int main(){
    int arr[8];
    int i;
    int sum = 0;
    float average;
    int count = 0; //평균이상이 개수 저장
    printf("점수를 8개 입력해주세요 > ");
    for(i = 0 ; i< 8;i++){
        scanf("%d", &arr[i]);
    }

    //합계 계산
    for(i = 0 ; i< 8;i++){
       sum += arr[i];
    }

    //평균
    average = (float) sum / 8;

    //평균 이상인 값의 개수 세기
    for(i = 0 ; i< 8;i++){
        if(arr[i] >= average){
            count++; //count = count + 1;
        }
    }
  //72 88 65 90 78 85 70 95
   // 결과 출력
    printf("평균: %.2f\n", average);
    printf("평균 이상인 값의 개수: %d\n", count);
    
    return 0;

}