
import FileReading.functionBlock;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author daltonchen
 */

public class DrawFunctionBlock extends JPanel {

    private JLabel NameLabel;
    private functionBlock fb;
    private FunctionBlockCoordinates fbCoordinates;

    public DrawFunctionBlock(functionBlock fb) {
        this.fb = fb;
        this.fbCoordinates = new FunctionBlockCoordinates(this.fb);

        setupPanel();
        drawFBnameLabel();
//        drawNodes();
    }

    public void setupPanel() {
        this.setLayout(null);
        this.setSize(fbCoordinates.getSize().coordinateX, fbCoordinates.getSize().coordinateY);
    }

    public void drawFBnameLabel() {
        NameLabel = new JLabel(fb.getName(),SwingConstants.CENTER);
        NameLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        NameLabel.setSize(fbCoordinates.getSize().coordinateX, 20);
        NameLabel.setLocation(fbCoordinates.getFBNameCoordinate().coordinateX, fbCoordinates.getFBNameCoordinate().coordinateY);
        this.add(NameLabel);
    }
    
//    public void drawNodes(){
//        
//        //event input
//        for(int i = 0; i < this.fb.getEinputNodes().length; i++){
//            
//            Coordinate coor = fbCoordinates.getInputEventCoordinates()[i];
//            
//            JButton button = new JButton();
//            button.setSize(10, 10);
//            button.setOpaque(true);
//            button.setBorderPainted(false);
//            button.setBackground(Color.PINK);
//            button.setLocation(coor.coordinateX, coor.coordinateY);
//            this.add(button);
//        }
//        
//    }

    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        g.drawPolygon(fbCoordinates.getXCoorArray(), fbCoordinates.getYCoorArray(), fbCoordinates.getXCoorArray().length);
    }
}
