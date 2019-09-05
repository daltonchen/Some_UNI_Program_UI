
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DisplayCanvas extends JPanel {

    FunctionBlock fb = new FunctionBlock();
    DrawFunctionBlock draw = new DrawFunctionBlock(fb);
    
    public DisplayCanvas(){
        draw.setLocation(fb.getLocationX(), fb.getLocationY());
        this.add(draw);
        
    }
}
