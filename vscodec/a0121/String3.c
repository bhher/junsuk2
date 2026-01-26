#include <stdio.h>
#include <string.h>

int main(){
    char name[20];
    printf("이름을 입력하세요: ");
    fgets(name, 20, stdin); //scanf 처럼 문자입력
    //printf("입력한 이름: %s\n", name);
    //name[strlen(name) - 1] = '\0';
    //입력 : abc + 엔터
    // index : 0    1   2   3   4   5
    //value  : 'a' 'b' 'c' '\n''\0'    
    //실무용
    if (name[strlen(name) - 1] == '\n') {
      name[strlen(name) - 1] = '\0';
    }
    //문자열끝에 \n  있으면 \0 대체

     printf("입력한 이름: %s\n", name);
    return 0;
}