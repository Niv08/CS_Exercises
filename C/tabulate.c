/*********************************************************
*  tabulate.c                                            *
*  ~~~~~~~~~~                                            *
*  file implementing program for taking a file named     *
*  and turning it into a comma-delimeter table in        *
*  outFile.                                              *
*********************************************************/

/*** use only these C libraries ***/
#include <stdio.h>
#include <stdlib.h>
#include "stringList.h"   // header file contains all function declaration for StringList

#define INFILE        "inFile.txt"
#define DELIM         ','
#define MAX_LINE_LEN  500


/*********************************************************************************/
/**********         YOUR  SOLUTUON TO PROBLEM 2 UNDER HERE            ************/
/*********************************************************************************/

int main() {

    FILE* file;
    file = fopen(INFILE, "r");

    // validate file memory allocation
    if (!file) {
        fprintf(stderr , "File %s cannot be opened\n", INFILE);
        return 1;
    }

    char * line = malloc((MAX_LINE_LEN + 1)* sizeof(char));
    char * list = "";

    // validate preliminary char arrays memory allocation
    if (!line || !list) {
        fprintf(stderr, "Out of memory\n");
        return 2;
    }

    // print each line as a list with the given delimiter
    while (fgets(line, MAX_LINE_LEN, file)) {
        list = createStrList(line);	//memory allocated inside the function
        if (list) {     //validate allocation
            if (!*list) printf("\n");   //check for empty line
            else printStrList(list, DELIM);    
        }
	    else {
		    fprintf(stderr, "Out of memory\n");
		    return 2;
	    }
    }

    // free all allocated memory
    free(line);
    free(list);
    fclose(file);
}

/*** end of stringList.c ***/
