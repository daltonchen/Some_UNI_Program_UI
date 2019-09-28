package UIdisplay;


import FileReading.SystemInfo;
import FileReading.functionBlock;
import FileReading.readIndividualFB;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DisplayCanvas extends JPanel {
    
    
    DisplayCanvas(SystemInfo info){
        setBackground(Color.white);
        setLayout(null);
        setAutoscrolls(true);
        
        this.drawFB(info);
    }
    
    private void drawFB(SystemInfo info){
        
        ArrayList<functionBlock> fbs = info.getFunctionBlocks();
        
        for(functionBlock fb : fbs){
            //readIndividualFB information, the xml should later being change to appropriate one
            readIndividualFB readFB = new readIndividualFB(fb.getName().toUpperCase() + ".fbt", fb);
                    
            DrawFunctionBlock drawFB = new DrawFunctionBlock(fb);
            
            int intX = (int)fb.getLocationX() / 2;
            int intY = (int)fb.getLocationY() / 2;
            
            drawFB.setLocation(intX,intY);
            this.add(drawFB);
        }
        
        
    }
}
