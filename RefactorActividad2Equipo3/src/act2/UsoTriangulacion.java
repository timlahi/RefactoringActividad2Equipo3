package act2;
import java.util.LinkedList;
import java.util.logging.Logger;

import javax.swing.JFrame;

import utils.Lector;

/**
 * @author frasco2001
 *
 */
public class UsoTriangulacion {

	
	private UsoTriangulacion() {

	}
	/**
	 * @param args
	 */
	@SuppressWarnings({})
	public static void main(String[] args) {

		// Variable con los vertices del poligono
		Punto[] vertices;
		Triangulo[] salida;

		// Peticion por pantalla del numero de vertices que tendra el poligono
		Logger.getLogger("**** Este programa realiza la triangulación de un poligono regular,"
				+ " concavo o convexo, sin aristas que intersecten.\n");
		Logger.getLogger("**** Introduzca el numero de vertices del poligono: \n");
		
		vertices = new Punto[Lector.leerEntero()];

		// Peticion por pantalla de los vertices del poligono
		Logger.getLogger("**** Introduce los vertices del poligono de forma correlativa,"
				+ " en el sentido contrario a las agujas del reloj\n");
		for (int i = 0; i < vertices.length; i++) {
			Logger.getLogger("* Introduce abcisa punto " + (i + 1) + ":");
			double abcisa = Lector.leerDouble();
			Logger.getLogger("* Introduce ordenada punto " + (i + 1) + ":");
			double ordenada = Lector.leerDouble();
			Punto nuevo = new Punto(abcisa, ordenada);
			vertices[i] = nuevo;
		}
		Poligono01 nuevo = new Poligono01(vertices);
		Logger.getLogger(nuevo.toString()+"\n");

		
		// Inicio del proceso de triangulacion
		if (Poligono01.esPoligono(vertices)) {
			
			// Ejercicio nº1
			//---------------
			
			String constante= "***********************************************\n";
			if (Poligono01.esConcavo(vertices)){
				Logger.getLogger(constante+
								   "***		POLIGONO CONCAVO  	    ***\n"+
								   constante);
			} else {
				Logger.getLogger(constante+
						   "***		POLIGONO CONVEXO  	    ***\n"+
						   constante);
			}
			
			LinkedList<Punto>cortes = Poligono01.seIntersectanAristas(vertices);
			Logger.getLogger("\nAristas Secantes: " + cortes.size()+"\n");
			if(cortes.isEmpty()){
				
				// Ejercicio nº3
				//---------------
				Logger.getLogger("***********************************************************\n"+
						   "***	CENTROIDE DEL POLIGONO	"+Poligono01.centroidePoligono(vertices)+"	***\n"+
						   "***********************************************************\n");				
				// Salida por pantalla de la solucion de la triangulacion del poligono
				Logger.getLogger(Poligono01.noSeIntersectan());
				// Salida por pantalla del area del poligono
				Logger.getLogger(Poligono01.areaPoligonoToString(vertices));
				salida = Triangulo.triangulacionPoligono(vertices);
				Logger.getLogger(Triangulo.arrayTriangulosToString(salida));
				
				// crea marco para objeto PoligonosJPanel
				JFrame marco = new JFrame("Dibujo de poligonos");
				marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				TriangulosJPanel triangulosJPanel = new TriangulosJPanel(salida);
				marco.add(triangulosJPanel); // agrega poligonosJPanel al marco
				marco.setSize(400, 400); // establece el tamaño del marco
				marco.setVisible(true); // muestra el marco
			} else {
				Logger.getLogger("**** No se pueden cortar las aristas del poligono para calcular los triangulos ****");
			}
		} else {
			Logger.getLogger("**** No es un poligono ****");
			
		}
	}
}