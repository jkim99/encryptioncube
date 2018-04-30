import java.util.Scanner;
public class HiddenWord {
	private String hidden_word;
	public HiddenWord(String hword) {
		hidden_word = hword;
	}
	public String getHint(String guess) {
		int length = guess.length();
		String returnVal = "", currentChar;
		for(int i = 0; i < length; i++) {
			currentChar = guess.substring(i, i + 1);
			if(currentChar.equals(hidden_word.substring(i, i + 1)))
				returnVal += currentChar;
			else if(hidden_word.contains(currentChar))
				returnVal += "+";
			else
				returnVal += "*";
		}
		return returnVal;
	}
	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		String guess = "";
		HiddenWord puzzle = new HiddenWord("HARPS");
		for(; !guess.equals("HARPS");) {
			System.out.println("Input Guess: ");
			guess = input.nextLine();
			System.out.println(puzzle.getHint(guess));
		}
	}
	public static void swap(int[] arr) {
		arr[0] = arr[arr.length - 1];
		arr[arr.length - 1] = arr[0];
	}
}