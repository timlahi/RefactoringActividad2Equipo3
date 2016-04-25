package act2;
/**
 * @author frasco2001
 *
 */
public class Punto {

	double x;
	double y;
	static final double EPSILON = 0.00001;

	/**
	 * 
	 * @param x
	 * @param y
	 */
	
private static boolean igualdouble(double uno, double dos){
		
		return Math.abs(uno - dos) < EPSILON;
		
		
		
	}
	public Punto(double x, double y) {
		if (igualdouble(x,-0.0)){
			this.x = 0.0;
		}
		if (igualdouble(y,-0.0)){
			this.y = 0.0;
		}
		
		
	}

	/**
	 * 
	 * @return
	 */
	public double getX() {
		return x;
	}

	/**
	 * 
	 * @param x
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * 
	 * @return
	 */
	public double getY() {
		return y;
	}

	/**
	 * 
	 * @param y
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "Punto [x=" + x + ", y=" + y + "]";
	}
	
	@Override
	public boolean equals(Object o){
		Punto p = (Punto)o;
		if(igualdouble(p.getX(),this.x) && igualdouble(p.getY(),this.y)) 
			return true;
		return false;
	}
}
