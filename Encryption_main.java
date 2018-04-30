import javax.swing.*;
import java.io.*;
import java.util.*;
public class Encryption_main {
	public static void main(String args[]) throws IOException {
		Encryption_display e = new Encryption_display();
		while(true) {
			Object[] options = {"Encrypt", "Decrypt", "Exit"};
			int choice = JOptionPane.showOptionDialog(null, "", "Encryption Beta 3.0", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
			if(choice == 0) {e.encryptDisplay();}
			else if(choice == 1) {e.decryptDisplay();}
			else if(choice == 2) {System.exit(0);}
		}
	}
}