import java.util.Arrays;
import java.io.*;
public class Encryption_rubik_cube {
	private char[][][] cube;
	private int cube_length;
	public Encryption_rubik_cube(char[] input_array) { //Creates Cube (method that is called from formula)
		cube_length = (int)Math.ceil(Math.cbrt(input_array.length)); //Gets the length based on the inputed array
		cube = new char[cube_length][cube_length][cube_length]; 
		int a = 0; 
		for(int i = 0; i < cube_length; i++) //Fills in data
			for(int j = 0; j < cube_length; j++)
				for(int k = 0; k < cube_length; k++) {
					if(a >= input_array.length)
						cube[i][j][k] = (char)(int)(Math.random() * 20 + 71); //random char between G-Z
					else
						cube[i][j][k] = input_array[a];
					a++; 
				}
	}
	public char[] cubeBreak() {
		char[] return_array = new char[(int)Math.pow(cube_length, 3)];
		int a = 0;
		for(int f = 0; f < cube.length; f++) //Converts cube array back into one-dimensional array
			for(int g = 0; g < cube.length; g++)
				for(int h = 0; h < cube.length; h++){
					return_array[a] = cube[f][g][h];
					a++;
				}
		return return_array;
	}
	private String keyGenerator(char[][][] cube) {
		int random_number, random_number_2, module;
		String instructions = "";
		for(int q = 0; q < 500; q++) { //MAKES KEY
			random_number = (int)(Math.random() * cube.length + 1);
			random_number_2 = (int)(Math.random() * cube.length + 1);
			module = random_number % 3;
			if(module == 0)
				instructions += "F-" + (int)(Math.random() * 3 + 1) + "-" + random_number_2 + "-";
			else if(module == 1)
				instructions += "L-" + (int)(Math.random() * 3 + 1) + "-" + random_number_2 + "-";
			else
				instructions += "D-" + (int)(Math.random() * 3 + 1) + "-" + random_number_2 + "-";
		}
		return instructions;
	}
	public void scramble() { //Scrambles Cube
		char[][][] return_cube = new char[cube.length][cube.length][cube.length];
		for(int a = 0; a < cube.length; a++)
			for(int b = 0; b < cube.length; b++)
				for(int c = 0; c < cube.length; c++)
					return_cube[a][b][c] = cube[a][b][c];
		String instructions = keyGenerator(cube), side;
		System.out.println(instructions); //PRINTS KEY
		String[] instruct = instructions.split("-");
		int rotations, layer;
		for(int x = 0; x < instruct.length; x+=3) { //Follows Key
			side = instruct[x]; //which side is being rotated
			rotations = Integer.valueOf(instruct[x + 1]); //number of rotations
			layer = Integer.valueOf(instruct[x + 2]); //which layer is being rotated
			switch(side) {
				case "F":
					char[][][] copy_cube = new char[cube.length][cube.length][cube.length]; //because array's aren't primitive :(
					while(rotations > 0) {
						for(int a = 0; a < cube.length; a++)
							for(int b = 0; b < cube.length; b++)
								for(int c = 0; c < cube.length; c++)
									copy_cube[a][b][c] = return_cube[a][b][c];
						for(int i = 0; i < return_cube.length; i++)
							for(int j = 0; j < return_cube.length; j++)
								return_cube[layer - 1][j][cube.length - 1 - i] = copy_cube[layer - 1][i][j];
						rotations--;
					}
					break;
				case "D":
					char[][][] v_rotated_cube = flipVertical(return_cube);
					return_cube = flipVertical(return_cube);
					while(rotations > 0) {
						for(int i = 0; i < return_cube.length; i++)
							for(int j = 0; j < return_cube.length; j++)
								return_cube[layer - 1][j][cube.length - 1 - i] = v_rotated_cube[layer - 1][i][j];
						for(int a = 0; a < cube.length; a++)
							for(int b = 0; b < cube.length; b++)
								for(int c = 0; c < cube.length; c++)
									v_rotated_cube[a][b][c] = return_cube[a][b][c];
						rotations--;
					}
					for(int a = 0; a < 3; a++)
							return_cube = flipVertical(return_cube);
					break;
				case "L":
					char[][][] h_rotated_cube = flipHorizontal(return_cube);
					return_cube = flipHorizontal(return_cube);
					while(rotations > 0) {
						for(int i = 0; i < return_cube.length; i++)
							for(int j = 0; j < return_cube.length; j++)
								return_cube[layer - 1][j][cube.length - 1 - i] = h_rotated_cube[layer - 1][i][j];
						for(int a = 0; a < cube.length; a++)
							for(int b = 0; b < cube.length; b++)
								for(int c = 0; c < cube.length; c++)
									h_rotated_cube[a][b][c] = return_cube[a][b][c];
						rotations--;	
					}
					for(int a = 0; a < 3; a++)
						return_cube = flipHorizontal(return_cube);
					break;
			}
		}
		cube = return_cube;
	}
	public void solve(String instructions) {
		String side;
		char[][][] return_cube = new char[cube.length][cube.length][cube.length];
			for(int a = 0; a < cube.length; a++)
				for(int b = 0; b < cube.length; b++)
					for(int c = 0; c < cube.length; c++)
						return_cube[a][b][c] = cube[a][b][c];
			String[] instruct = instructions.split("-");
			int rotations, layer;
			for(int x = instruct.length - 1; x >= 0; x-=3) { //Follows Key
				side = instruct[x - 2]; //which side is being rotated
				rotations = 4 - Integer.valueOf(instruct[x - 1]); //number of rotations
				layer = Integer.valueOf(instruct[x]); //which layer is being rotated
				switch(side) {
					case "F":
						char[][][] copy_cube = new char[cube.length][cube.length][cube.length]; //because array's aren't primitive :(
						while(rotations > 0) {
							for(int a = 0; a < cube.length; a++)
								for(int b = 0; b < cube.length; b++)
									for(int c = 0; c < cube.length; c++)
										copy_cube[a][b][c] = return_cube[a][b][c];
							for(int i = 0; i < return_cube.length; i++)
								for(int j = 0; j < return_cube.length; j++)
									return_cube[layer - 1][j][cube.length - 1 - i] = copy_cube[layer - 1][i][j];
							rotations--;
						}
						break;
					case "D":
						char[][][] v_rotated_cube = flipVertical(return_cube);
						return_cube = flipVertical(return_cube);
						while(rotations > 0) {
							for(int i = 0; i < return_cube.length; i++)
								for(int j = 0; j < return_cube.length; j++)
									return_cube[layer - 1][j][cube.length - 1 - i] = v_rotated_cube[layer - 1][i][j];
							for(int a = 0; a < cube.length; a++)
								for(int b = 0; b < cube.length; b++)
									for(int c = 0; c < cube.length; c++)
										v_rotated_cube[a][b][c] = return_cube[a][b][c];
							rotations--;
						}
						for(int a = 0; a < 3; a++)
								return_cube = flipVertical(return_cube);
						break;
					case "L":
						char[][][] h_rotated_cube = flipHorizontal(return_cube);
						return_cube = flipHorizontal(return_cube);
						while(rotations > 0) {
							for(int i = 0; i < return_cube.length; i++)
								for(int j = 0; j < return_cube.length; j++)
									return_cube[layer - 1][j][cube.length - 1 - i] = h_rotated_cube[layer - 1][i][j];
							for(int a = 0; a < cube.length; a++)
								for(int b = 0; b < cube.length; b++)
									for(int c = 0; c < cube.length; c++)
										h_rotated_cube[a][b][c] = return_cube[a][b][c];
							rotations--;	
						}
						for(int a = 0; a < 3; a++)
							return_cube = flipHorizontal(return_cube);
						break;
				}
			}
		cube = return_cube;
	}
	private char[][][] flipHorizontal(char[][][] cube) { //Rotates Cube's orrientation horizontally on the Y-axis
		char[][][] return_cube = new char[cube.length][cube.length][cube.length];
		for(int i = 0; i < cube.length; i++)
			for(int j = 0; j < cube.length; j++)
				for(int k = 0; k < cube.length; k++)
					return_cube[k][j][cube.length - 1 - i] = cube[i][j][k];
		return return_cube;
	}
	private char[][][] flipVertical(char[][][] cube) { //Rotates Cube's orrientation vertically on the X-axis
		char[][][] return_cube = new char[cube.length][cube.length][cube.length];
		for(int i = 0; i < cube.length; i++) {
			for(int j = 0; j < cube.length; j++)
				for(int k = 0; k < cube.length; k++)
					return_cube[cube.length - 1 - j][i][k] = cube[i][j][k];
		}
		return return_cube;
	}
}