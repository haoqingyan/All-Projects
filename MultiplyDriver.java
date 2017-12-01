package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MultiplyDriver {

	public static void main(String[] args) throws InstantiationException {
		final LLInt x = new LLInt(args[0]), y = new LLInt(args[1]);
		/*BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));
		String strX;
		try {
			strX = bufReader.readLine();
			String strY = bufReader.readLine();
			LLInt x = new LLInt(strX), y = new LLInt(strY);
			System.out.println("" + x.ko_multiply(y));
			System.out.println("" + x.es_multiply(y));
			//System.out.println("" + x.subtract(y));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		System.out.println("" + x.ko_multiply(y));
		System.out.println("" + x.es_multiply(y));
		/*
		 * ADAPT THE NEXT PART AS REQUIRED, TO CALL YOUR add(), WITH WHATEVER
		 * SIGNATURE YOU CHOSE:
		 */


		/////// COMMON ALTERNATIVES: ///////////
		// System.out.println("" + LLInt.add(x, y));
		// System.out.println("" + x.add(x, y)); // ick
	}
}
