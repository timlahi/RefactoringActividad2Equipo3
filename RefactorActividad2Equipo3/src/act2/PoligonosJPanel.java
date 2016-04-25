package act2;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.logging.Logger;

import javax.swing.JPanel;

/**
 * @author frasco2001
 *
 */
public class PoligonosJPanel extends JPanel {
	/**
	 * 
	 */
	Polygon poligono1;
	Graphics g;
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param vertices
	 */
	public PoligonosJPanel(Punto[] vertices) {
		this.poligono1 = new Polygon();	
		double minX=Double.MAX_VALUE ;
		double minY=Double.MAX_VALUE;
		Logger.getLogger(minX+" "+minY);
		for (int i = 0; i < vertices.length; i++) {
			Punto a = vertices[i];
			if(a.getX()<minX){
				minX=a.getX();
			}
			if(a.getY()<minY){
				minY=a.getY();
			}
		}
			if (minX<0){
				minX=-minX;
			}
			if (minY<0){
				minY=-minY;
			}
		for (int i = 0; i < vertices.length; i++) {
			Punto a = vertices[i];
			poligono1.addPoint((int) (a.getX()+minX+1) * 50, (int) (a.getY()+minY+1) * 50);
		}
	}

	/**
	 * 
	 */
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); 
	
		g.setColor(Color.RED);
		g.drawPolygon(poligono1);
	}
}
