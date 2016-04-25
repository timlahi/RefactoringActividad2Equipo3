package utils;
import java.util.Scanner;

public class Lector{
	
	private Lector(){
		
	}

	private static Scanner lectorcito = new Scanner(System.in);

	/**
	 * 
	 * @return
	 */
	public static double leerDouble() {
		return lectorcito.nextDouble();
	}

	/**
	 * 
	 * @return
	 */
	public static int leerEntero() {
		return lectorcito.nextInt();
	}
}
