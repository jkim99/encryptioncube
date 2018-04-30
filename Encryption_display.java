import javax.swing.*;
import java.io.*;
import java.util.*;
public class Encryption_display extends JPanel {
	private static JPanel p;
	private static File file1, file2;
	private static JFileChooser jfc;
	private static JTextField input;
	
	public Encryption_display() throws IOException {
		p = new JPanel();
		file1 = null;
		jfc = new JFileChooser();
		input = new JTextField();
	}
	public static void encryptDisplay() throws IOException {
		jfc = new JFileChooser();
		input = new JTextField();
		JTextField input2 = new JTextField();
		int returnVal = jfc.showOpenDialog(jfc);
		if(returnVal == JFileChooser.APPROVE_OPTION){file1 = jfc.getSelectedFile();}
		if(file1 == null){return;}
		p.add(new JLabel("Name of encrypted file: "));
		p.add(input);
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		JOptionPane.showMessageDialog(null, p);
		File encryptedFile = new File(input.getText() + ".txt");
		Encryption_formula.encrypt(file1, encryptedFile);
	}
	public static void decryptDisplay() throws IOException {
		JProgressBar progressbar = new JProgressBar();
		p = new JPanel();
		jfc = new JFileChooser();
		input = new JTextField();
		File encryptedFile = null, file3;
		int returnVal = jfc.showOpenDialog(jfc);
		if(returnVal == JFileChooser.APPROVE_OPTION) {encryptedFile = jfc.getSelectedFile();}
		if(encryptedFile == null) {return;}
		p.add(new JLabel("Name of decrypted file (include file tag): "));
		p.add(input);
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		JOptionPane.showMessageDialog(null, p);
		file3 = new File(input.getText());
		Encryption_formula.decrypt(encryptedFile, file3);
		
	}
}