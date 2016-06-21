import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class TransactionReader {

	final static String FILENAME = "Plik z danymi.txt";

	/**
	 * @param name file name
	 */
	public int readTransactions(String name) {
		double sum = 0;
		try{
			Scanner scan = new Scanner(new File(name)); 
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				String[] pairs = line.split("@");
				for(int i = 0; i < pairs.length; ++ i) {
					String [] keyVals = pairs[i].split(":");
					if(keyVals.length != 2)
						continue;
					else if(keyVals[0].equals("amount"))
						sum += sumFromString(keyVals[1]);
				}
			}
			scan.close();
		}  catch(FileNotFoundException ex) {
			System.err.println("File not found exception");
		}
		return sum;
	}

	public double sumFromString(String str) {
		int PLNidx = str.indexOf("PLN");
		String num = str.substring(0, PLNidx); //remove PLN if present
		String strWithDot = num.replace(",", ".");
		try {
			return Double.parseDouble(strWithDot);	
		} catch(NumberFormatException ex) {
			return 0;
		}
	}

	public static void main(String[] args) {
		String name = (args.length == 0) ? FILENAME : args[0]; 
		System.out.println("SUMA = " + new TransactionReader().readTransactions(name) + " PLN");
	}
}
