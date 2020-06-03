package UIdisplay;


import FileReading.Data;
import FileReading.Event;
import FileReading.functionBlock;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
    // display canvas is used to add information to console.
    public DisplayCanvas canvas;

    public DrawFunctionBlock(functionBlock fb, DisplayCanvas canvas) {
        this.fb = fb;
        this.canvas = canvas;
        
        // at this step, every coordinate about the function block it self will be 
        // calculated and generated under there
        // it is possible that a function block will have a null coordinates
        // since the file did not exist, please do check each one before using of it.
        this.fb.setFunctionBlockCoordiantes(new FunctionBlockCoordinates(this.fb));
        this.fbCoordinates = this.fb.getFunctionBlockCoordinates();

        setupPanel();
        drawFBnameLabel();
        drawNodes();
        
//        testComponent();
    }
    

    public void setupPanel() {
        this.setLayout(null);
        this.setSize(this.fb.getWidth(), this.fb.getHeight());
    }

    public void drawFBnameLabel() {
        NameLabel = new JLabel(fb.getName(),SwingConstants.CENTER);
        NameLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        NameLabel.setSize(this.fb.getWidth(), 20);
        NameLabel.setLocation(fbCoordinates.getFBNameCoordinate().coordinateX, fbCoordinates.getFBNameCoordinate().coordinateY);
        this.add(NameLabel);
    }
    
    public void drawNodes(){
        
        if(this.fb.getInputEvent() != null){

            //event input
            for(int i = 0; i < this.fb.getInputEvent().size(); i++){

                FunctionBlockButton button = new FunctionBlockButton();
                button.setSize(10, 10);
                button.setOpaque(true);
                button.setBorderPainted(false);
                button.setBackground(Color.RED);
                button.setLocation(this.fb.getInputEvent().get(i).getCoordinate().coordinateX, this.fb.getInputEvent().get(i).getCoordinate().coordinateY);
                button.fb = this.fb;
                button.nodeType = "Event Input";
                button.nodeID = i;
                button.addMouseListener(new functionBlockButtonClicked());
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

                FunctionBlockButton button = new FunctionBlockButton();
                button.setSize(10, 10);
                button.setOpaque(true);
                button.setBorderPainted(false);
                button.setBackground(Color.blue);
                button.setLocation(this.fb.getOutputEvent().get(i).getCoordinate().coordinateX, this.fb.getOutputEvent().get(i).getCoordinate().coordinateY);
                button.fb = this.fb;
                button.nodeType = "Event Output";
                button.nodeID = i;
                button.addMouseListener(new functionBlockButtonClicked());
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

                FunctionBlockButton button = new FunctionBlockButton();
                button.setSize(10, 10);
                button.setOpaque(true);
                button.setBorderPainted(false);
                button.setBackground(Color.ORANGE);
                button.setLocation(this.fb.getInputData().get(i).getCoordinate().coordinateX, this.fb.getInputData().get(i).getCoordinate().coordinateY);
                button.fb = this.fb;
                button.nodeType = "Data Input";
                button.nodeID = i;
                button.addMouseListener(new functionBlockButtonClicked());
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

                FunctionBlockButton button = new FunctionBlockButton();
                button.setSize(10, 10);
                button.setOpaque(true);
                button.setBorderPainted(false);
                button.setBackground(Color.green);
                button.setLocation(this.fb.getOutputData().get(i).getCoordinate().coordinateX, this.fb.getOutputData().get(i).getCoordinate().coordinateY);
                button.fb = this.fb;
                button.nodeType = "Data Output";
                button.nodeID = i;
                button.addMouseListener(new functionBlockButtonClicked());
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
    
    public class FunctionBlockButton extends JButton{
        public functionBlock fb;
        public int nodeID;
        public String nodeType;
    }
    
    
    public class functionBlockButtonClicked implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {

            Object source = e.getSource();
            if(source instanceof FunctionBlockButton){
                FunctionBlockButton button = (FunctionBlockButton)source;
                
                canvas.uiFrame.addMessageToConsole("Mouse Click At: " + button.fb.getName() +" Position: " + (button.nodeID+1) + " Type: " + button.nodeType, ConsoleEnum.USER_ACTION);
                
                
                if(button.nodeType == "Event Input"){
                    
                    Event event = button.fb.getInputEvent().get(button.nodeID);
                    canvas.uiFrame.changeTableToButton_Event(event);
                    
                } else if (button.nodeType == "Event Output"){
                    
                    Event event = button.fb.getOutputEvent().get(button.nodeID);
                    canvas.uiFrame.changeTableToButton_Event(event);
                    
                } else if (button.nodeType == "Data Input"){
                    
                    Data data = button.fb.getInputData().get(button.nodeID);
                    canvas.uiFrame.changeTableToButton_Data(data);
                    
                } else if (button.nodeType == "Data Output"){
                    
                    Data data = button.fb.getOutputData().get(button.nodeID);
                    canvas.uiFrame.changeTableToButton_Data(data);
                    
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent arg0) {}

        @Override
        public void mouseReleased(MouseEvent arg0) {}

        @Override
        public void mouseEntered(MouseEvent arg0) {}

        @Override
        public void mouseExited(MouseEvent arg0) {}
        
    }
}
