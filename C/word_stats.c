#include <stdlib.h>
#include <stdio.h>
#include <ctype.h>
#include <string.h>

#define MAX_WORD_LENGTH 80
#define MAX_LINE_LENGTH 80

int main(int argc, char * argv[]) {
    if (argc == 1) {
        printf("Usage: word_stats file\n");
        return 1;
    }
    FILE * file = fopen(argv[1], "r");
    if (!file) {
        printf("Could not open %s\n", argv[1]);
        return 1;
    }

    int maxLength = 0, words = 0, chars = 0;
    char * maxStr = malloc(MAX_WORD_LENGTH * sizeof(char));
    char * line = malloc(MAX_LINE_LENGTH * sizeof(char));
    if (!line || !maxStr) {
        printf("Out of memory\n");
        return 1;
    }

    while (fgets(line, MAX_LINE_LENGTH, file)) {
        int i = 0;
        char * str = malloc (MAX_WORD_LENGTH * sizeof(char));
        char * p = line;
        if (isalpha(*p)) {
            str[i] = *p;
            i++;
            p++;
        }
        else {
            if (strlen(str) > maxLength) {
                maxLength = strlen(str);
                strcpy(maxStr, str);
            }
            p++;
            i = 0;
            words++;
            chars += strlen(str);
        }
        free(str);
    }
    
    printf("Average word length: %d\n", (chars / words));
    printf("Longest word: %s\n", maxStr);
    return 0;

}
