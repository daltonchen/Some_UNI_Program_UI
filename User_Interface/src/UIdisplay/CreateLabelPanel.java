/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIdisplay;

import FileReading.ConnType;
import FileReading.Data;
import FileReading.Event;
import FileReading.functionBlock;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;


class labelCoor{
    int coorX;
    int coorY;
    String text;
    labelType type;
    
    public labelCoor(int coorX, int coorY, String text, labelType type){
        this.coorX = coorX;
        this.coorY = coorY;
        this.text = text;
        this.type = type;
    }
}
enum labelType{
    LEFT, RIGHT;
}

/**
 *
 * @author daltonchen
 */
public class CreateLabelPanel extends JPanel{
    
    ArrayList<labelCoor> labels;
    
    public CreateLabelPanel(int appropriateWidth, int appropriateHeight,functionBlock fb){
        initialPanel(appropriateWidth, appropriateHeight);
        calculateIndividualCoordinate(fb);
    }
    
    public void initialPanel(int width, int height){
        this.setLayout(null);
        this.setSize(width, height);
    }
    
    public void calculateIndividualCoordinate(functionBlock fb){
        
        labels = new ArrayList<labelCoor>();
        if(fb.getInputEvent() != null){
            for(Event e : fb.getInputEvent()){
                if(e.getInitialValue() != null){
                    int coorX = e.getCoordinate().coordinateX - 15 + (int)fb.getLocationX()/3;
                    int coorY = e.getCoordinate().coordinateY + (int)fb.getLocationY()/3 + 9;
                    labels.add(new labelCoor(coorX, coorY, e.getInitialValue().trim(), labelType.LEFT));
                }
            }
        }
        if(fb.getOutputEvent() != null){
            for(Event e : fb.getOutputEvent()){
                if(e.getInitialValue() != null){
                    int coorX = e.getCoordinate().coordinateX + 5 + (int)fb.getLocationX()/3;
                    int coorY = e.getCoordinate().coordinateY + (int)fb.getLocationY()/3 + 9;
                    labels.add(new labelCoor(coorX, coorY, e.getInitialValue().trim(), labelType.RIGHT));
                }
            }
        }
        if(fb.getInputData() != null){
            for(Data d : fb.getInputData()){
                if(d.getInitialValue() != null){
                    int coorX = d.getCoordinate().coordinateX - 15 + (int)fb.getLocationX()/3;
                    int coorY = d.getCoordinate().coordinateY + (int)fb.getLocationY()/3 + 9;
                    labels.add(new labelCoor(coorX, coorY, d.getInitialValue().trim(), labelType.LEFT));
                }
            }
        }
        if(fb.getOutputData() != null){
            for(Data d : fb.getOutputData()){
                if(d.getInitialValue() != null){
                    int coorX = d.getCoordinate().coordinateX + 5 + (int)fb.getLocationX()/3;
                    int coorY = d.getCoordinate().coordinateY + (int)fb.getLocationY()/3 + 9;
                    labels.add(new labelCoor(coorX, coorY, d.getInitialValue().trim(), labelType.RIGHT));
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        
        for(labelCoor label : labels){
            g2.setFont(new Font("Arial", Font.PLAIN, 11));
            FontMetrics fm = g2.getFontMetrics();
            
            if(label.type == labelType.LEFT){
                g2.drawString(label.text, label.coorX - fm.stringWidth(label.text), label.coorY);
            } else {
                g2.drawString(label.text, label.coorX + fm.stringWidth(label.text), label.coorY);
            }
        }

    }

}

