package hangman;

import java.util.Arrays;
import java.util.Scanner;

public class Game {
	private int k = 0;
	public String word = "banana";
	public char[] wordArr = word.toCharArray();
	public char[] blanks = new char[word.length()];
	public char[] man = { ' ', ' ', ' ', ' ', ' ', ' ' };
	private int guesses = man.length;
	private Scanner input = new Scanner(System.in);

	/**Jonathan Langford
	 * Clears the array blanks every time a new game is started.
	 */
	public void newGame() {
		for (int i = 0; i < blanks.length; i++) {
			blanks[i] = '_';
		}
	}

	/**Jonathan Langford
	 * When called this uses the field k to decide what part of
	 * the man should be drawn. As more wrong choices are made k 
	 * continues to increment and the man is hung.
	 */
	public void wrongLetter() {
		switch (k) {
		case 0:
			man[k] = '0';
			break;
		case 1:
			man[k] = '|';
			break;
		case 2:
			man[k] = '/';
			break;
		case 3:
			man[k] = '\\';
			break;
		case 4:
			man[k] = '/';
			break;
		case 5:
			man[k] = '\\';
			break;
		}
		k++;
	}

	/**Jonathan Langford
	 * @returns the printed noose and stage
	 */
	public String Noose() {
		return " ;--,\n" + " |  " + man[0] + "\n" + " | " + man[2] + man[1] + man[3] + "\n" + " | " + man[4] + " "
				+ man[3] + "\n" + "_| " + Arrays.toString(blanks);
	}

	/**Jonathan Langford
	 * Calls Noose to print the stage the requests user input.
	 * Checks user input with wordArr, which is the word of choice 
	 * converted to a char Array. If a match is found then it updates 
	 * the blanks array which are the final result. Otherwise it updates
	 * the man Arrayd and the man begin to be hung. 
	 */
	public void Start() {
		newGame();
		do {
			System.out.print(Noose());
			System.out.print("\nGuess a Letter: ");
			String letterGuessed = input.next();
			char letterGuess = letterGuessed.charAt(0);
			if (letterGuessed.length() > 1) {
				System.out.println("Please try one guess at a time.");
			}
			for (int j = 0; j < wordArr.length; j++) {
				if (wordArr[j] == letterGuess) {
					blanks[j] = letterGuess;
				}
			}
			boolean contains = false;
			for (char c : wordArr) {
				if (c == letterGuess) {
					contains = true;
				}
			}
			if (!contains) {
				wrongLetter();
			}
			guesses--;
			System.out.println(guesses + " guesses remaining\n");
		} while (man[5] != '\\' && guesses > 0 && (new String(blanks).contains("_")));

		if (man[5] != '\\' && guesses >= 0 && !(new String(blanks).contains("_"))) {
			System.out.println("You Guessed it!!");
			System.out.println(Noose());
		} else {
			System.out.println("Your out of Guesses, Think Harder Next Time");
		}
	}

	/**Jonathan Langford
	 * @param args
	 * TEST CLIENT
	 */
	public static void main(String[] args) {
		Game game = new Game();
		game.Start();
	}
}