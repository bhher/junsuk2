#include <stdio.h>
#include <string.h>
//문자열 복사
int main(){
    char src[] = "Hello";
    
    char dest[20];
    
    strcpy(dest, src);
    printf("복사된 문자열: %s\n", dest);
    
}