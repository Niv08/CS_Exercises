/*********************************************************
*  stringList.c                                          *
*  ~~~~~~~~~~~~                                          *
*  file implementing data structure and functions for    *
*  string list                                           *
*********************************************************/

/*** use only these C libraries ***/
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>        // for isspace()
#include "stringList.h"   // header file contains all function declaration for StringList


/*********************************************************************************/
/**********    YOUR  SOLUTUON TO PROBLEM 1.1 - 1.7 UNDER HERE         ************/
/*********************************************************************************/

static char * current = NULL;
static char * currentList = NULL;

/*** Problem 1.1 ***/
int numStrsInList(const char* strList) {

    if (strList == NULL) return -1;
    else {
        int counter = 0, i = 0;
        while (i >= 0) {
            if (strList[i] == '\0') {
                if (strList[i + 1] == '\0') { 
                    if (i == 0) break;  //in case its an empty stringList
                    else {
                        counter++;
                        break;
                    }
                }
                else counter++;
            }
            i++;
        }
        return counter;
    }
}

/*** Problem 1.2 ***/
int strListLen(const char* strList) {

    if (strList == NULL) return -1;
    else {
        int counter = 0; int i = 0;
        while (i >= 0) {
            if (strList[i] == '\0' && strList[i + 1] == '\0') break;
            counter++;
            i++;
        }
        return counter;
    } 

    return -2;   
}

/*** Problem 1.3 ***/
int emptyStrList(char* target) {

    if (target == NULL) return -1;
    else {
        target[0] = '\0';
        target[1] = '\0';
        return 0;
    }

    return -2;   
}

/*** Problem 1.4 ***/
int strListFromWords(const char* source, char* target, int buffSize) {

    if (buffSize < 2) return -1;
    int length = 0, sourceIndex = 0, targetIndex = 0, i = 0, j = 0;

    //calculating required length for the stringList, ignoring leading,
    // tailing and consecutive whitespaces
    while (source[j] != '\0') j++;
    j--;
    while (isspace(source[j])) {
        j--;
    }
    //finding the first non-white space place in the string
    while (isspace(source[i])) {
        i++;
    }

    // counting the required number of chars, ignoring consecutive whitespaces
    while (i <= j) {
        if (isspace(source[i])) {
            length++;
            i++;
            while (isspace(source[i])) {
                i++;
            }
        }
        else {
            length++;
            i++;
        }
    }

    // checking if the available space is enough
    if (length > buffSize - 2) {
        emptyStrList(target);
        return 0;
    }

    // copying the words to the target array, replacing first whitespace in \0
    // and ignoring other consecutive whitespaces
    while (isspace(source[sourceIndex])) {
        sourceIndex++;
    }
    while (source[sourceIndex] != '\0') {
        if (isspace(source[sourceIndex])) {
            while (isspace(source[sourceIndex])) {
                sourceIndex++;
            }
            if (source[sourceIndex] == '\0') break;
            else {
                target[targetIndex] = '\0';
                targetIndex++;
            }
        }
        else {
            target[targetIndex] = source[sourceIndex];
            sourceIndex++;
            targetIndex++;
        }
    }

    // adding two '\0' to terminate the string list
    target[targetIndex] = '\0';
    target[++targetIndex] = '\0';

    return numStrsInList(target);

}

/*** Problem 1.5 ***/
char* createStrList(const char* str) {

    
    if (!str) return NULL;  //in case str is NULL
    int length = 0, sourceIndex = 0, targetIndex = 0, i = 0, j = 0;
    
    //calculating required length for the stringList, ignoring leading,
    // tailing and consecutive whitespaces
    while (str[j] != '\0') j++;
    j--;
    while (isspace(str[j])) {
        j--;
    }
    //finding the first non-white space place in the string
    while (isspace(str[i])) {
        i++;
    }

    // counting the required number of chars, ignoring consecutive whitespaces
    while (i <= j) {
        if (isspace(str[i])) {
            length++;
            i++;
            while (isspace(str[i])) {
                i++;
            }
        }
        else {
            length++;
            i++;
        }
    }
    char * target = malloc(length * sizeof(char) + 2);
    if (!target) return NULL;   //in case allocation failed

    while (isspace(str[sourceIndex])) {
    sourceIndex++;
    }
    while (str[sourceIndex] != '\0') {
        if (isspace(str[sourceIndex])) {
            while (isspace(str[sourceIndex])) {
                sourceIndex++;
            }
            if (str[sourceIndex] == '\0') break;
            else {
                target[targetIndex] = '\0';
                targetIndex++;
            }
        }
        else {
            target[targetIndex] = str[sourceIndex];
            sourceIndex++;
            targetIndex++;
        }
    }

    // adding two '\0' to terminate the string list
    target[targetIndex] = '\0';
    target[++targetIndex] = '\0';
    
    return target;
}

/*** Problem 1.6 ***/
char* nextStrInList(char* stringList) {
    char * str;
    // checks if the argument is NULL or this is an empty list
    if (stringList == NULL || !*stringList) {
        current = NULL;
        return NULL;
    }

    // checks if this is the first call to the function or if we
    // start to work on a new String List
    if (current == NULL || stringList != currentList) {
        current = stringList;
        currentList = stringList;
        int length = 0;
        // count the length of the next word
        while (*current != '\0') {
            current++;
            length++;
        }
        // allocate appropriate memory
        str = malloc(length * sizeof(char) + 1);

        // create the new string and return it
        int j = 0;
        while (length >= 0) {
            str[j] = *(current - length);
            length--;
            j++;
        }
        return str;
    }
    // if we continue to work on the same String List
    else {
        current++;
        if (*current == '\0') {
            return NULL;
        }
        int length = 0;
        while (*current != '\0') {
            current++;
            length++;
        }
        str = malloc(length * sizeof(char) + 1);
        int j = 0;
        while (length >= 0) {
            str[j] = *(current - length);
            length--;
            j++;
        }
        return str;
    }
}

/*** Problem 1.7 ***/
void printStrList(char* strList, char delim) {

    char * str = nextStrInList(strList);
    if (!str || !*str) {    //check for an empty string
            printf("\n");
            return;
        }
    while (str != NULL) {
        printf("%s", str);
        str = nextStrInList(strList);
        if (str == NULL) {
            printf("\n");
            return;
        }
        else {
            printf("%c", delim);
        }
    }

}

/*** end of stringList.c ***/
