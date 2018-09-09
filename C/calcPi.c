#include <stdio.h>
#include <stdlib.h> 

double calcPi(int n);

/********************
* calcPi function for Problem 3
* -------------------------
* Recieves as an argument a positive number n and generating
* n random points with (x,y) coordinates.
* Returns an approximation of Pi using the Monte Carlo method. 
* ==> implement for Problem 3.1
*******************/
double calcPi(int n) {

    int c = 0, i;
    double x, y;

    for(i = 0; i < n; i++) {
        x = (double)rand() / RAND_MAX; 
        y = (double)rand() / RAND_MAX;
        if ((x*x) + (y*y) <= 1) c++; 
    }
    
    return (double)4*c/n;
}

/********************
* main function for Problem 3 
* -------------------------
* Reads a positive integer n from stdin and calling calling
* calcPi function with that n.
* Prints the approximated value of PI calculated with n random
* points.
* If invalid input is given (not a positive number),
* returns exit status of 1.
* ==> implement for Problem 3.2
*******************/
int main() {
    
    int n, status;
    printf("Please enter the number of points to randomly sample: ");
    status = scanf("%d", &n);
    
    //check for the exit status of scanf to determine
    //if the input is a valid number
    if (status != 1 || n <= 0) {
        printf("Input should be a positive number.\n");
        return 1;
    } 
    else {
        printf("\nThe approximated value of PI using %d points is: %.10f\n", n, calcPi(n));
    }

    return 0;
}