package UIdisplay;


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
        drawNodes();
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
    
    public void drawNodes(){
        
        if(this.fb.getInputEvent() != null){

            //event input
            for(int i = 0; i < this.fb.getInputEvent().size(); i++){

                JButton button = new JButton();
                button.setSize(10, 10);
                button.setOpaque(true);
                button.setBorderPainted(false);
                button.setBackground(Color.RED);
                button.setLocation(this.fb.getInputEvent().get(i).getCoordinate().coordinateX, this.fb.getInputEvent().get(i).getCoordinate().coordinateY);
                this.add(button);
                
                int labelLength = (int)(this.fb.getInputEvent().get(i).getName().length() * 7f);
                
                JLabel label = new JLabel(this.fb.getInputEvent().get(i).getName());
                label.setFont(new Font("Arial", Font.PLAIN, 9));
                label.setSize(labelLength < 30? 30 : labelLength, 10);
                label.setLocation(this.fb.getInputEvent().get(i).getCoordinate().coordinateX + 15, this.fb.getInputEvent().get(i).getCoordinate().coordinateY);
                this.add(label);
            }
            
            //event output
            for(int i = 0; i < this.fb.getOutputEvent().size(); i++){

                JButton button = new JButton();
                button.setSize(10, 10);
                button.setOpaque(true);
                button.setBorderPainted(false);
                button.setBackground(Color.blue);
                button.setLocation(this.fb.getOutputEvent().get(i).getCoordinate().coordinateX, this.fb.getOutputEvent().get(i).getCoordinate().coordinateY);
                this.add(button);
                
                int labelLength = (int)(this.fb.getOutputEvent().get(i).getName().length() * 7f);
    
                JLabel label = new JLabel(this.fb.getOutputEvent().get(i).getName(), SwingConstants.RIGHT);
                label.setFont(new Font("Arial", Font.PLAIN, 9));
                label.setSize(labelLength < 30? 30 : labelLength, 10);
                label.setLocation(this.fb.getOutputEvent().get(i).getCoordinate().coordinateX - (labelLength < 30? 30 : labelLength) - 5 , this.fb.getOutputEvent().get(i).getCoordinate().coordinateY);
                this.add(label);
            }
            
            
            // data input
            
            for(int i = 0; i < this.fb.getInputData().size(); i++){

                JButton button = new JButton();
                button.setSize(10, 10);
                button.setOpaque(true);
                button.setBorderPainted(false);
                button.setBackground(Color.ORANGE);
                button.setLocation(this.fb.getInputData().get(i).getCoordinate().coordinateX, this.fb.getInputData().get(i).getCoordinate().coordinateY);
                this.add(button);
                
                int labelLength = (int)(this.fb.getInputData().get(i).getName().length() * 7f);
                
                JLabel label = new JLabel(this.fb.getInputData().get(i).getName());
                label.setFont(new Font("Arial", Font.PLAIN, 9));
                label.setSize(labelLength < 30? 30 : labelLength, 10);
                label.setLocation(this.fb.getInputData().get(i).getCoordinate().coordinateX + 15, this.fb.getInputData().get(i).getCoordinate().coordinateY);
                this.add(label);

            }
            
            // data output
            for(int i = 0; i < this.fb.getOutputData().size(); i++){

                JButton button = new JButton();
                button.setSize(10, 10);
                button.setOpaque(true);
                button.setBorderPainted(false);
                button.setBackground(Color.green);
                button.setLocation(this.fb.getOutputData().get(i).getCoordinate().coordinateX, this.fb.getOutputData().get(i).getCoordinate().coordinateY);
                this.add(button);
                
                int labelLength = (int)(this.fb.getOutputData().get(i).getName().length() * 7f);
    
                JLabel label = new JLabel(this.fb.getOutputData().get(i).getName(), SwingConstants.RIGHT);
                label.setFont(new Font("Arial", Font.PLAIN, 9));
                label.setSize(labelLength < 30? 30 : labelLength, 10);
                label.setLocation(this.fb.getOutputData().get(i).getCoordinate().coordinateX - (labelLength < 30? 30 : labelLength) - 5 , this.fb.getOutputData().get(i).getCoordinate().coordinateY);
                this.add(label);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        g.drawPolygon(fbCoordinates.getXCoorArray(), fbCoordinates.getYCoorArray(), fbCoordinates.getXCoorArray().length);
    }
}
