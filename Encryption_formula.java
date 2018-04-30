import java.util.*;
import java.io.*;
import java.nio.file.*;
public class Encryption_formula {
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray(); //Hex Array
	public static void encrypt(File file1, File file2) throws IOException {
		PrintWriter writer = new PrintWriter(file2);
		byte[] data = Files.readAllBytes(Paths.get(file1.getPath())); //Reading Bytes Frrom File
		char[] hex_char_array = new char[data.length * 2];
		for(int i = data.length - 1; i >= 0; i--) { //Converting Bytes to Hex into char array (backwards)
			int v = data[i] & 0xFF;
			hex_char_array[i * 2] = hexArray[v >>> 4];
			hex_char_array[i * 2 + 1] = hexArray[v & 0x0F];
		}
		Encryption_rubik_cube cube = new Encryption_rubik_cube(hex_char_array); //creates cube
		cube.scramble(); //Initializes Cube Scrambler
		hex_char_array = cube.cubeBreak(); //Breaks down into one-dimensional array
		writer.write(new String(hex_char_array)); //Writing to encrypted file
		writer.close();
	}
	
	public static void decrypt(File file1, File file2) throws IOException {
		System.out.println("Please enter key here: ");
		Scanner input = new Scanner(System.in);
			String key = input.nextLine();
		String s = new Scanner(file1).useDelimiter("\\A").next(); //Scanning in file
		String sub, holdCheck;
		FileOutputStream stream = new FileOutputStream(file2.getPath()); 
		byte[] unencrypted_byte_array = new byte[s.length() / 2]; //Byte array is half of the length of the encrypted string because each char is half of the hex value
		Encryption_rubik_cube cube = new Encryption_rubik_cube(s.toCharArray()); //creates cube
		cube.solve(key);
		s = new String(cube.cubeBreak()); //Initializing unsquare method
		for(int a = 0; a < s.length(); a++) { //Checking for extra characters G-Z
			holdCheck = s.substring(a, a+1); 
			if(holdCheck.charAt(0) < 91 && holdCheck.charAt(0) > 70)
				s = s.substring(0, a); //Cutting off extra characters
		}
		for(int i = 0; i < s.length(); i+=2) {
			sub = s.substring(i, i+2);
			unencrypted_byte_array[i / 2] = (byte)((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i+1), 16)); //Creating unencrypted string
		}
		try {stream.write(unencrypted_byte_array);} //Outputing into rebuilt file
		finally {stream.close();}
	}
}