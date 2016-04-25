package act2;
import java.util.Scanner;
import java.util.logging.Logger;

import javax.swing.JFrame;

/**
 * @author frasco2001
 *
 */
public class UsoPoligono01 {
	
	
	private UsoPoligono01(){
		
	}

	private static Scanner lector = new Scanner(System.in);

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Peticiones por pantalla
		Logger.getLogger("Introduce el numero de vertices del poligono (minimo 4 puntos):");
		int puntos = leerEntero();
		Punto[] vertices = new Punto[puntos];
		
		Logger.getLogger("Introduce los puntos de forma correlativa en el sentido contrario a las agujas del reloj");
		for (int i = 0; i < vertices.length; i++) {
			Logger.getLogger("Introduce abcisa punto " + (i + 1) + ":");
			double abcisa = leerDouble();
			Logger.getLogger("Introduce ordenada punto " + (i + 1) + ":");
			double ordenada = leerDouble();
			Punto nuevo = new Punto(abcisa, ordenada);
			vertices[i] = nuevo;
		}
		Poligono01 poligono = new Poligono01(vertices);

		// Soluciones
		
		

		// (ES POlIGONO)
		if (Poligono01.esPoligono(vertices)) {
			// (AREA)
			Logger.getLogger("El area del poligono es: ");
			Logger.getLogger(Double.toString(Poligono01.areaPoligono(vertices)));
			// (CENTROIDE)
			Logger.getLogger("El centroide del poligono es el punto: ");
			Logger.getLogger(""+Poligono01.centroidePoligono(vertices));
			// (CONCAVIDAD)
			Logger.getLogger("¿Es Concavo el poligono?");
			Logger.getLogger(Boolean.toString(Poligono01.esConcavo(vertices)));
			// (PUNTOS DEL POLIGONO)
			Logger.getLogger(""+poligono);

			// crea marco para objeto PoligonosJPanel
			JFrame marco = new JFrame("Dibujo de poligonos");
			marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			PoligonosJPanel poligonosJPanel = new PoligonosJPanel(vertices);
			marco.add(poligonosJPanel); // agrega poligonosJPanel al marco
			marco.setSize(400, 400); // establece el tamaño del marco
			marco.setVisible(true); // muestra el marco
		} else {
			Logger.getLogger(Poligono01.seraPoligono(vertices));
		}
	}

	public static Scanner getLector() {
		return lector;
	}

	public static void setLector(Scanner lector) {
		UsoPoligono01.lector = lector;
	}

	/**
	 * 
	 * @return
	 */
	public static double leerDouble() {
		return lector.nextDouble();
	}

	/**
	 * 
	 * @return
	 */
	public static int leerEntero() {
		return lector.nextInt();
	}
}
