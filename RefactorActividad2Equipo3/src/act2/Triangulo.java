package act2;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.logging.Logger;

/**
 * @author frasco2001
 *
 */
public class Triangulo {
	Punto[] triangulito = new Punto[3];

	/**
	 * 
	 * @param z
	 */
	public Triangulo(Punto[] z) {
		this.triangulito = z;
	}

	/**
	 * 
	 * @param izquierdo
	 * @param vertice
	 * @param derecho
	 */
	public Triangulo(Punto izquierdo, Punto vertice, Punto derecho) {
		
		triangulito[0] = vertice;
		triangulito[1] = derecho;
		triangulito[2] = izquierdo;
	}

	/**
	 * Funcion que calcula la diferencia de angulos entre dos rectas formadas
	 * por tres puntos a->b y a->c , es decir el angulo formado por las dos
	 * rectas (angulo(a->c)-angulo(a->b)), pero siempre positivo. Por lo tanto,
	 * obtenemos el angulo en sentido antihorario.
	 * 
	 * @param vertice
	 *            : Primer punto, y sobre el que se realizara el calculo del
	 *            angulo.
	 * @param dos
	 *            : Segundo punto, punto a la derecha del vertice, inicio del
	 *            calculo del angulo en sentido antihorario.
	 * @param tres
	 *            : Tercer punto, punto a la izquierda del vertice, fin del
	 *            calculo del angulo.
	 * @return : Angulo en radianes [0-2PI] con la diferencia entre las rectas
	 *         vertice-izquierda y vertice-derecha, pero formateado en
	 *         gradianes.
	 */
	public static double angulo(Punto vertice, Punto derecha, Punto izquierda) {

		// transladamos al origen de coordenadas los tres puntos
		Punto pi = new Punto(derecha.x - vertice.x, derecha.y - vertice.y);
		Punto pj = new Punto(izquierda.x - vertice.x, izquierda.y - vertice.y);

		
		double angpi = Math.atan2((double) pi.x, (double) pi.y);
		double angpj = Math.atan2((double) pj.x, (double) pj.y);

		
		double ang = angpj - angpi;

		
		if (ang < 0.0)
			return Math.toDegrees(ang + (2.0 * Math.PI));
		else
			return Math.toDegrees(ang);
	}// fin angulo

	/**
	 * 
	 * @param triangulo
	 * @return
	 */
	public boolean esTriangulo(Punto[] triangulo) {
		if (triangulo.length != 3) {
			return false;
		}
		if (!triangulo[0].equals(triangulo[1]) || !triangulo[0].equals(triangulo[2])
				|| !triangulo[1].equals(triangulo[2])) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @return
	 */
	public Punto[] getTriangulo() {
		return triangulito;
	}

	/**
	 * 
	 * @param triangulo
	 */
	public void setTriangulo(Punto[] triangulo) {
		this.triangulito = triangulo;
	}

	/**
	 * 
	 * @return
	 */
	public Punto getVertice() {
		return triangulito[0];
	}

	/**
	 * 
	 * @return
	 */
	public Punto getDerecha() {
		return triangulito[1];
	}

	/**
	 * 
	 * @return
	 */
	public Punto getIzquierda() {
		return triangulito[2];
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return Arrays.toString(triangulito);
	}

	/**
	 * 
	 * @param poligono
	 * @return
	 */
	public static Triangulo[] triangulacionPoligono(Punto[] poligono) {
		int contadorTriangulos = 0;
		// Array con los triangulos de la solucion
		Triangulo[] trianguloSalida;
		// Lista original de vertices del poligono
		LinkedList<Punto> vertex = new LinkedList<>();
		// Lista auxiliar de vertices
		LinkedList<Punto> auxVertex = new LinkedList<>();
		// Lista de vertices para comprobar si estan contenidos en el triangulo
		LinkedList<Punto> contenido = new LinkedList<>();
		// Lista de vertices eliminados en la triangulacion
		LinkedList<Punto> eliminados = new LinkedList<>();

		for (int i = 0; i < poligono.length; i++) {
			vertex.add(poligono[i]);
		}
		// Instanciacion del array de la solucion
		trianguloSalida = new Triangulo[poligono.length - 2];
		// Creacion del iterador de la lista principal
		ListIterator<Punto> iter1 = vertex.listIterator();
		// Instanciacion de los puntos que formaran los posibles triangulos
		// del poligono
		Punto izquierdo; 
				Punto angulo;
				Punto derecho;
		// Contador de vueltas o puntos que no han podido ser eliminados en
		// principio en el proceso de triangulacion
		int vueltas = 0;

		while (trianguloSalida.length != contadorTriangulos) {
			auxVertex.clear();
			for (int i = 0; i < poligono.length; i++) {
				if (!eliminados.contains(poligono[i])) {
					auxVertex.add(poligono[i]);
				}
			}
			// Creacion del iterador de la lista auxiliar
			ListIterator<Punto> iterAux1 = auxVertex.listIterator();
			// Colocacion de los iteradores
			for (int i = 0; i < vueltas; i++) {
				if (iterAux1.hasNext()) {
					iterAux1.next();
				} else {
					iterAux1 = auxVertex.listIterator();
					i++;
				}
			}
			// Salida por pantalla de los puntos de los posibles triangulos
			Logger.getLogger(posibleTrianguloToString());
			if (iterAux1.hasPrevious()) {
				izquierdo = iterAux1.previous();
				iterAux1.next();
			} else {
				izquierdo = auxVertex.getLast();
			}
			Logger.getLogger("Punto izquierdo:" + izquierdo);

			
			if (iterAux1.hasNext()) {
				angulo = iterAux1.next();
				
				if (iter1.hasNext()) {
					iter1.next();
				} else {
					iter1 = vertex.listIterator();
				}
			} 
			else {
				iterAux1 = auxVertex.listIterator();
				angulo = iterAux1.next();
				if (!iter1.hasNext()) {
					iter1 = vertex.listIterator();
					iter1.next();
				}
			}
			Logger.getLogger("Punto central  :" + angulo);

			if (iterAux1.hasNext()) {
				derecho = iterAux1.next();
			} else {
				derecho = auxVertex.getFirst();
			}
			Logger.getLogger("Punto derecho  :" + derecho);

			// Comprobacion si el triangulo pertenece al poligono y sino
			// contiene otros puntos de este
			if (Triangulo.angulo(angulo, izquierdo, derecho) < 180) {
				contenido.clear();
				for (Iterator<Punto> iter = vertex.iterator(); iter.hasNext();) {
					Punto punto = iter.next();
					if (!(punto.equals(derecho) || punto.equals(angulo) || punto.equals(izquierdo))
							&& !eliminados.contains(punto)) {
						contenido.add(punto);
					}
				}
				if (contenido.isEmpty() && auxVertex.size() == 3) {
					trianguloSalida[contadorTriangulos++] = new Triangulo(izquierdo, angulo, derecho);
					Logger.getLogger(trianguloAniadidoOKToString());
				} else {
					int eliminar = 0;
					for (Iterator<Punto> iter = contenido.iterator(); iter.hasNext();) {
						Punto punto = iter.next();
						Logger.getLogger(angulosTrianguloRestoVerticesToString());
						Logger.getLogger(Double.toString(Triangulo.angulo(izquierdo, punto, angulo)));
						Logger.getLogger(Double.toString(Triangulo.angulo(angulo, punto, derecho)));
						Logger.getLogger(Double.toString(Triangulo.angulo(derecho, punto, izquierdo)));
						if (Triangulo.angulo(izquierdo, punto, angulo) > 180
								|| Triangulo.angulo(angulo, punto, derecho) > 180
								|| Triangulo.angulo(derecho, punto, izquierdo) > 180) {
							eliminar++;
						}
					}
					if (eliminar == contenido.size()) {
						trianguloSalida[contadorTriangulos++] = new Triangulo(izquierdo, angulo, derecho);
						eliminados.add(angulo);
						Logger.getLogger(trianguloAniadidoOKToString());
					} else {
						vueltas++;
					}
				}
			} else {
				vueltas++;
			}
		}
		
		return trianguloSalida;
	}

	/**
	 * 
	 * @param triangulos
	 * @return
	 */
	public static String arrayTriangulosToString(Triangulo[] triangulos) {
		String constante= "---------------------------------------------\n";
		String salida = constante + "La solucion es: \n"
				+ constante + "Los triangulos son:\n"
				+ constante
				+ "***********************************************************************************\n";
		for (int i = 0; i < triangulos.length; i++) {
			salida += "***	" + triangulos[i].toString() + "	***\n";
		}
		return salida + "***********************************************************************************\n";
	}

	/**
	 * 
	 * @return
	 */
	public static String trianguloAniadidoOKToString() {
		return "*********************************************\n" + "Triangulo aorrecto añadido...........\n"
				+ "*********************************************\n";

	}

	/**
	 * 
	 * @return
	 */
	public static String posibleTrianguloToString() {
		return "------------------------------------------------\n" + "Posible Triangulo........:\n"
				+ "------------------------------------------------\n";
	}

	/**
	 * 
	 * @return
	 */
	public static String angulosTrianguloRestoVerticesToString() {
		return "----------------------------------------------------------------\n"
				+ "Angulos del triangulo con los restantes vertices del poligono...\n"
				+ "----------------------------------------------------------------\n";
	}
}
