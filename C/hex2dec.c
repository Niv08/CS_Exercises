/**********************************************************
* hex2dec.c
* ~~~~~~~~~
* C source file for program in Ex4, Problem 2.
* Program converts positive integers from hexadecimal
* representation (base 16) to decimal representasion
* (base 10).
* 
**********************************************************/

#include <stdio.h>

int char2hex(char);
int test_char2hex(void);
int hexPush(int n, int d);
int test_hexPush(void);

/********************
* char2hex
* --------
* param c - character representing hexadecimal digit
* return value: integer number representing value of hexadecimal digit (0-15)
* or -1, if c is not a hexadecimal digit
* ==> implement for Problem 2.2
*******************/
int char2hex(char c) {
  if (c >= '0' && c <= '9') {
  	return (c - 48);
  } 
  else if (c >= 'a' && c <= 'f') {
  	return (c - 87);
  }
  else if (c >= 'A' && c <= 'F') {
	  return (c - 55);
  }
 
  return -1;
}

/********************
* test_char2hex
* -------------
* testing function char2hex for Problem 2.2
* return value: 1 if tests pass, or 0 otherwise.
* ==> feel free to add more tests
*******************/
int test_char2hex(void) {
  int res = 1;
  
  res = res && (char2hex('3')== 3);
  res = res && (char2hex('b')==11);
  res = res && (char2hex('D')==13);
  res = res && (char2hex('*')==-1);
  res = res && (char2hex('g')==-1);

  return res;
}

/********************
* hexPush
* -------
* param n - non-negative integer number
* param d - integer number in range [0,15] representing hexadecimal digit
* return value: integer number obtained by pushing d to the right of n
*   if n<0 or d is out of range [0,15], function returns -1
*   if operation results in negative number or bit overflow, the function returns -1
* ==> implement for Problem 2.3
*******************/
int hexPush(int n, int d) {
  if (n >= 0 && (d >= 0 && d <= 15)) {
    if ((n<<4) + d >= 0) {
      if ((n<<4) + d > n) {
        return ((n<<4) + d);  
      }
      else if (n == 0 && (n<<4) + d == 0) {
        return ((n<<4) + d);
      }
    }
  } 
  return -1;
}

/********************
* test_hexPush
* ------------
* testing function hexPush for Problem 2.3
* return value: 1 if tests pass, or 0 otherwise.
* ==> feel free to add more tests
*******************/
int test_hexPush(void) {
  int res = 1;

  res = res && (hexPush(0xA1E,15)     == 0xA1EF);
  res = res && (hexPush(10,11)        == 171   );
  res = res && (hexPush(10,16)        == -1);
  res = res && (hexPush(-10,11)       == -1);
  res = res && (hexPush(0xA000000,11) == -1);
  res = res && (hexPush(0x1A000000,0) == -1);

  return res;
}

/********************
* main function for hex2dec 
* -------------------------
* Reads a hexadecimal number from stdin char by char and
* prints out the corresponding integer in decimal form.
* Valid input should consist of hex digits 0-9,a-f,A-F
* and should corrrespond to a positive int, followed by
* newline.
* If invalid input is given, returns exit status of 1.
* ==> implement for Problem 2.4
*******************/
int main() {

  int result = 0;
  char c = ' ';

  while (c != '\n') {
    scanf("%c", &c);
    if (c == '\n') break;
    else if (char2hex(c) == -1) {
      printf("Invalid input. '%c' is not a hexadecimal digit.\n", c);
      return 1;
    }
    else if (hexPush(result, char2hex(c)) == -1) {
        printf("Invalid input. Exceeds allowed space for positive int.\n");
        return 1;
      }
    else {
        result = hexPush(result, char2hex(c));
      }
  }

  printf("Decimal form = %d\n", result);

  return 0;
}


