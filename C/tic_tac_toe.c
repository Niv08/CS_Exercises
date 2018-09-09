#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <math.h>
#include <time.h>

void drawBoard(char board[3][3]) {
    printf("\n");
    printf("Player 1 (X) - Player 2 (O)\n");
    printf("\n");

    printf("     |     |\n");
    printf("  %c  |  %c  |  %c\n", board[0][0], board[0][1], board[0][2]);
    printf("_____|_____|_____\n");
    printf("     |     |\n");
    printf("  %c  |  %c  |  %c  \n", board[1][0], board[1][1], board[1][2]);
    printf("_____|_____|_____\n");
    printf("     |     |\n");
    printf("  %c  |  %c  |  %c  \n", board[2][0], board[2][1], board[2][2]);
    printf("     |     |\n");

    printf("\n");
    return;
}

int checkForWin(char board[3][3]) {
    
    int i = 0; int j = 0;
    if (board[i][j] == board[i+1][j+1] && board[i+1][j+1] == board[i+2][j+2]) {
        if (board[i][j] == 'X') return 1;
        if (board[i][j] == 'O') return 2;
    }
    i = 0; j = 2;
    if (board[i][j] == board[i+1][j-1] && board[i+1][j-1] == board[i+2][j-2]) {
        if (board[i][j] == 'X') return 1;
        if (board[i][j] == 'O') return 2;
    }
    
    for (int i = 0; i < 3; i++) {
        int j = 0;
        if (board[i][j] == board[i][j+1] && board[i][j+1] == board[i][j+2]) {
            if (board[i][j] == 'X') return 1;
            if (board[i][j] == 'O') return 2;
        }
    }

    for (int j = 0; j < 3; j++) {
        int i = 0;
        if (board[i][j] == board[i+1][j] && board[i+1][j] == board[i+2][j]) {
            if (board[i][j] == 'X') return 1;
            if (board[i][j] == 'O') return 2;
        }
    }

    return 0;   
}

void markBoard(int player, char board[3][3], int num) {

    int choose = num;
    int counter = 1;
    int x, y;
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            if (counter == choose) {
                x = i;
                y = j;
            }
            counter++;
        }
    }
    while (board[x][y] == 'X' || board[x][y] == 'O') {
        int x, y;
        counter = 1;
        printf("Invalid selection, try another spot\n");
        scanf("%d", &choose);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (counter == choose) {
                    x = i;
                    y = j;
                }
                counter++;
            }
        }
        if (board[x][y] != 'X' && board[x][y] != 'O') {
             if (player == 1) {
                board[x][y] = 'X';
            } else {
                board[x][y] = 'O';
            }
            return;
        }
    }
    
    if (player == 1) {
        board[x][y] = 'X';
    } else {
        board[x][y] = 'O';
    }
    
    return;
}



 
int main(int argc, char *argv[]) {
    
    char board[3][3] = {{'1','2','3'},{'4','5','6'},{'7','8','9'}};
    drawBoard(board);

    int i = 1;
    while (checkForWin(board) == 0) {
        int pick;
        if (i == 1) {
            printf("Player 1, choose a number\n");
            scanf("%d", &pick);
            markBoard(1, board, pick);
            drawBoard(board);
            int win = checkForWin(board);
            if (win == 1) {
                printf("Player 1 won!\n");
                return 0;
            }
            else {
                i = 2;
            }
        }
        else {
            printf("Player 2, choose a number\n");
            scanf("%d", &pick);
            markBoard(2, board, pick);
            drawBoard(board);
            int win = checkForWin(board);
            if (win == 2) {
                printf("Player 2 won!\n");
                return 0;
            }
            else {
                i = 1;
            } 
        }

    }
    
 
    return 0;
}

