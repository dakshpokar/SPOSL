import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class macroPass1 {
	Scanner scan;
	public void getInput() throws IOException {
		scan = new Scanner(System.in);
		//String filename = scan.next();
		String filename = "macro_input.asm";
		fileRead(filename);
	}
	public void fileRead(String x) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(x));
		String s;
		boolean macro_def = false;
		while((s = br.readLine()) != null) {
			String[] spl = s.split("\\s");
			if(s.contains("MACRO")) {
				if(spl.length == 1) {
					macro_def = true;
					continue;
				}
				else {
					macro_def = false;
				}
			}
			if(macro_def == true) {

			}
		}
	}
	public static void main(String[] args) {
		macroPass1 m = new macroPass1();
		try {
			m.getInput();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
