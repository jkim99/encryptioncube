import java.util.*;
import java.io.*;
public class rubiktest {
	public static void main(String args[]) throws Exception {
		File f1 = new File("/mnt/c/Users/jonat/Desktop/test.java");
		File f2 = new File("/mnt/c/Users/jonat/Desktop/test_java.en");
		File f3 = new File("/mnt/c/Users/jonat/Desktop/test_REBUILD.java");
		Encryption_formula.encrypt(f1, f2);
		Encryption_formula.decrypt(f2, f3);
	}
}