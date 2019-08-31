import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;



public class DisplayCanvas extends JPanel{
	
	//int[] x = {30,150,150,135,135,150,150,30,30,45,45,30,30};
	//int[] y = {60,60,120,120,135,135,195,195,135,135,120,120,60};

	FunctionBlock fb = new FunctionBlock();
	FunctionBlockCoordinates coor = new FunctionBlockCoordinates();
	FunctionBlock fb2 = new FunctionBlock(600, 900);
	FunctionBlockCoordinates coor2 = new FunctionBlockCoordinates();
	
	@Override 
	public void paintComponent(Graphics g) {
		//g.drawRoundRect(60, 60, 60, 80, 20, 20);
		//g.drawPolygon(x, y, x.length);
		
		coor.CoordinateCalculator(fb);
		
		g.drawPolygon(coor.getXCoorArray(), coor.getYCoorArray(), coor.getXCoorArray().length);
		
		coor2.CoordinateCalculator(fb2);
		
		g.drawPolygon(coor2.getXCoorArray(), coor2.getYCoorArray(), coor2.getXCoorArray().length);
		
	}
	
}
