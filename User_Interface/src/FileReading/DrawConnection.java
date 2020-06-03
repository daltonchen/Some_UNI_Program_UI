/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileReading;

import UIdisplay.Coordinate;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

/**
 *
 * @author daltonchen
 */
public class DrawConnection extends JPanel{
    
    private int[] coorX;
    private int[] coorY;
    private int[] arrowX;
    private int[] arrowY;
    private ConnType type;
        
    public DrawConnection(int[] coorX, int[] coorY, int sizeX, int sizeY, ConnType type){
        
        this.setLayout(null);
        this.setSize(sizeX, sizeY);
        
        this.coorX = coorX;
        this.coorY = coorY;
        this.type = type;
        
        this.calculateArrowCoordiantes();
    }
    private void calculateArrowCoordiantes(){
        arrowX = new int[5];
        arrowY = new int[5];
        arrowX[0] = coorX[coorX.length - 1] - 10;   
        arrowY[0] = coorY[coorY.length - 1];
        
        arrowX[1] = arrowX[0];
        arrowY[1] = arrowY[0] - 5;
        
        arrowX[2] = arrowX[0] + 10;
        arrowY[2] = arrowY[0];
        
        arrowX[3] = arrowX[0];
        arrowY[3] = arrowY[0] + 5;
        
        arrowX[4] = arrowX[0];
        arrowY[4] = arrowY[0];
    }
    
    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        if(type == ConnType.DATA){
            g2.setColor(Color.BLUE);
        } else {
            g2.setColor(Color.RED);
        }
        g2.drawPolyline(this.coorX, this.coorY, this.coorX.length);
        
        g2.fillPolygon(arrowX, arrowY, arrowX.length);
    }

    
    
}
