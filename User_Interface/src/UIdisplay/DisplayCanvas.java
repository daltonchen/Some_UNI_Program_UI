package UIdisplay;


import FileReading.ConnectionInfo;
import FileReading.Data;
import FileReading.DrawConnection;
import FileReading.Event;
import FileReading.SystemInfo;
import FileReading.functionBlock;
import FileReading.readIndividualFB;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import javax.swing.JPanel;

public class DisplayCanvas extends JPanel {
    
    Dimension appropriateSize;
    // caling method in uiFrame.
    UI_Frame uiFrame;
    ArrayList<functionBlock> fbs;
    
    
    DisplayCanvas(Dimension size, UI_Frame uiFrame){
        setBackground(Color.white);
        setLayout(null);
        setAutoscrolls(true);
        addMouseListener(new whiteAreaClickMouseListerner());
        
        this.uiFrame = uiFrame;
        
        this.appropriateSize = size;

    }
    
    public void updateSysfile(SystemInfo info, String path){
        if(info != null){
            this.drawFB(info, path);
        } else{
            uiFrame.addMessageToConsole("Unable To Draw Function Block - Critical Data Missing", ConsoleEnum.ERROR_MESSAGE);
        }
    }
    
    private void drawFB(SystemInfo info, String path){

        ArrayList<functionBlock> fbs = info.getFunctionBlocks();
        
        uiFrame.addMessageToConsole("Draw Function Block", ConsoleEnum.INPROGRESS);

        for(functionBlock fb : fbs){
            
            //readIndividualFB information, the xml should later being change to appropriate one
            //any other errors such like the file not found exceptions will be print out in the console
            readIndividualFB readFB = new readIndividualFB(path + fb.getType().toUpperCase() + ".fbt", fb);
                    
            DrawFunctionBlock drawFB = new DrawFunctionBlock(fb, this);
            
            int intX = (int)fb.getLocationX() / 3;
            int intY = (int)fb.getLocationY() / 3;
            
            drawFB.setLocation(intX,intY);
            
            this.add(drawFB);
            uiFrame.addMessageToConsole("Draw Function Block - " +fb.getName(), ConsoleEnum.SUCCESS_MESSAGE);

        }
        uiFrame.addMessageToConsole("Function Block - Finish Drawing", ConsoleEnum.SUCCESS_MESSAGE);
        
        this.fbs = fbs;
        drawConnections(info, fbs);
        drawInitialValue(fbs);
    }
    
    private void drawConnections(SystemInfo sysInfo, ArrayList<functionBlock> fbs){
        
        // determine the completeness of the file
        Map<String, functionBlock> fbMap = new HashMap<String, functionBlock>();
        
        
        for(functionBlock fb: fbs){
            fbMap.put(fb.getName(), fb);
        }
                
        uiFrame.addMessageToConsole("Draw Connections", ConsoleEnum.INPROGRESS);

        // get all event connections
        ArrayList<ConnectionInfo> allConn = new ArrayList<ConnectionInfo>();
        ArrayList<ConnectionInfo> eventConnections = sysInfo.getEventConn();
        ArrayList<ConnectionInfo> dataConnections = sysInfo.getDataConn();
        allConn.addAll(eventConnections);
        allConn.addAll(dataConnections);
        
        for(ConnectionInfo connInfo: allConn){
            
           
            //Convert the name into coordinates.
            // it will first get information from connection information, and then it will extract 
            // the source and the target name, by using these names, it will able to found the coordinates
            // of it.


            String[] destinationInfo = connInfo.getDestination().split("\\.");
            String[] sourceInfo = connInfo.getSource().split("\\.");

            // check the existence and completeness of function block
            // in the case of not exist, it will give a return value of null
            // in the case if the function block is not complete, it will return the value of false.
            // either of those two cases will not able to continue running.

            // check the existence of function block
            if(fbMap.get(destinationInfo[0]) != null && fbMap.get(sourceInfo[0]) != null){

                // check the completeness of the function block, it should return true when the function block is complete
                // this boolean is being set to true when read the individual function block
                if(fbMap.get(destinationInfo[0]).isComplete() && fbMap.get(sourceInfo[0]).isComplete()){

                    ConnectionCoorCalculation coonCoorCal = new ConnectionCoorCalculation(connInfo ,sourceInfo, destinationInfo, fbMap.get(sourceInfo[0]), fbMap.get(destinationInfo[0]));

                    DrawConnection dc = new DrawConnection(coonCoorCal.getCoorX(), coonCoorCal.getCoorY(), appropriateSize.width, appropriateSize.height, connInfo.getType());
                    dc.setLocation(0, 0);
                    this.add(dc);

                    uiFrame.addMessageToConsole("Draw Connections - " + connInfo.getSource() + " To " + connInfo.getDestination(), ConsoleEnum.SUCCESS_MESSAGE);
                }

            } else {
                uiFrame.addMessageToConsole("Unable To Draw Connections (.fbt file missing) - " + connInfo.getSource() + " To " + connInfo.getDestination(), ConsoleEnum.WARNING);

            }
          
        }
        uiFrame.addMessageToConsole("Connections - Finish Drawing", ConsoleEnum.SUCCESS_MESSAGE);

    }
    
    public void drawInitialValue(ArrayList<functionBlock> fbs){
        
        uiFrame.addMessageToConsole("Draw Values", ConsoleEnum.INPROGRESS);
        for(functionBlock fb : fbs){
            CreateLabelPanel dvl = new CreateLabelPanel(appropriateSize.width, appropriateSize.height, fb);
            dvl.setLocation(0, 0);
            // add to function block to have a better control, when value is update, it can choose a partial update.
            fb.setLabelPanel(dvl);
            this.add(dvl);
        }
        uiFrame.addMessageToConsole("Values - Finish Drawing", ConsoleEnum.SUCCESS_MESSAGE);

    }
    
    public class whiteAreaClickMouseListerner implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            int CoorX = e.getX();
            int CoorY = e.getY();
            
            functionBlock clickedFunctionBlock = null;
            
            if(fbs != null){
                for(functionBlock fb : fbs){
                    int xLower = (int)fb.getLocationX() / 3;
                    int xUpper = (int)fb.getLocationX() / 3 + fb.getWidth();
                    int yLower = (int)fb.getLocationY() / 3;
                    int yUpper = (int)fb.getLocationY() / 3 + fb.getHeight();
                    
                    if((CoorX > xLower && CoorX < xUpper)&&(CoorY > yLower && CoorY < yUpper)){
                        clickedFunctionBlock = fb;
                    }
                }
            }
            
            if(clickedFunctionBlock != null){
                uiFrame.addMessageToConsole("Mouse Click At Function Block: " + clickedFunctionBlock.getName() , ConsoleEnum.USER_ACTION);
                uiFrame.changeTableToFunctionBlockMode(clickedFunctionBlock);

            } else {
                uiFrame.addMessageToConsole("Mouse Click At X: " +CoorX + " Y: " + CoorY , ConsoleEnum.USER_ACTION);
                uiFrame.changeTableToSystemMode();
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
