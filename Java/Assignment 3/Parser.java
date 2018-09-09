/** A parser that gets character and int values from a given string.
 */
public class Parser {
	static String str;   // the string to parse
	static int N;        // size of the string
	static int cursor;   // current position in the string
	
	/** Initializes the given string for parsing. */
	public static void init(String s) {
		str = s;
		N = str.length();
		cursor = 0;
	}
	
	/** Returns true if there are more characters to process, false otherwise. */
	public static boolean hasMoreChars() {
		return (cursor < N);
	}

	/** Returns the next character in the string, as a char value.
	/*  Should be called only if hasMoreChars() is true. */
	// Side effect: advances the cursor just beyond the character.
	public static char nextChar() {
		char c = str.charAt(cursor);
		cursor++;
		return c;
	}
	/** Returns the next integer in the text.
	 *  Should be called only if hasMoreChars() is true, and if the next character is a digit. */
	// Side effect: advances the cursor just beyond the integer.
	public static int nextInt() {
		// When this function is called, the next character must be a digit.
		int val = nextChar()- 48;
		while (hasMoreChars()) {
			char c = nextChar();
			// If the character is a digit, update the integer. 
			if (isDigit(c)) {
			    val = 10 * val + (c - 48);
			}
			// If the character is not a digit, regresses the cursor and exits the loop.
			else {
				cursor--;
				break;
			}
		}
		return val;
	}
	
	// Returns true if the given character is a digit, false otherwise.
	private static boolean isDigit(char c) {
		return ((c >= '0') && (c <= '9'));
	}
	
	// The main function of this class is designed to test the parser.
	// It gets a string from the command line, and assumes that the string has the format "cncncn...",
	// where each c is a single char value and each n is one or more digit characters.
	public static void main(String[] args)  {
	      // Initializes the parser to the command line argument
	      Parser.init(args[0]);
	      // Goes through the input string and prints each character and integer value in a separate line.
	      // To demonstrate that the parser parses the integer value correctly, prints this value + 1.
	      while (Parser.hasMoreChars()) {
	         System.out.println(Parser.nextChar());
	         System.out.println(Parser.nextInt() + 1);
	      }		
	 }
}