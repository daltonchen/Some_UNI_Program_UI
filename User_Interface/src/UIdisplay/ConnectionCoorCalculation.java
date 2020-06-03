/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIdisplay;

import FileReading.ConnectionInfo;
import FileReading.DrawConnection;
import FileReading.SystemInfo;
import FileReading.functionBlock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class will handle the calculation of the coordinates between two function blocks
 * it will generates a series of coordinates and later can being using to draw a series of lines
 * which between two function blocks
 * @author daltonchen
 */
public class ConnectionCoorCalculation {
    
    private ArrayList<Coordinate> connectionCoordinates;
    private int[] coorX;
    private int[] coorY;
    
    /**
     * this class will setup the initial environment for the program to use.
     * @param sourceInfo the source information, it will represent in the form of array
     *                   the [0] position will include the function block's name 
     *                   the [1] position will include the function block's port name
     * @param destinationInfo the destination information, [0][1] will keep the same as above
     * @param fbMap the function block which in a map format, <String, functionBlock>
     *              String will include the name of the function block
     *              functionBlock will contain functionBlock itself.
     * @param conn  
     */
    public ConnectionCoorCalculation(ConnectionInfo connInfo, String[] sourceInfo, String[] destinationInfo, functionBlock sourceFB, functionBlock destFB){
        
        
        
        System.out.println("------------");
        System.out.println("SOURCE: " + sourceInfo[0]);
        System.out.println("DEST: " + destinationInfo[0]);

//        // in this part, it will obtain the initial position of the function block (which is the location of function block)
//        Coordinate sourceCoordinate_INITIAL = new Coordinate((int) fbMap.get(sourceInfo[0]).getLocationX() / 2, (int) fbMap.get(sourceInfo[0]).getLocationY() / 2);
//        Coordinate destinationCoordinate_INITIAL = new Coordinate((int) fbMap.get(destinationInfo[0]).getLocationX() / 2, (int) fbMap.get(destinationInfo[0]).getLocationY() / 2);
        
        this.connectionCoordinates = new ArrayList<Coordinate>();
        calculateCorrectCoordinates(sourceFB, destFB, sourceInfo[1], destinationInfo[1], connInfo);
        getherCoorInfo();
        
    }
    
    /**
     * it will gather the initial coordinate information and add some value to fix the gap
     * in here, the source coordinate should add the width of the function block (as it was start at the right half of function block)
     * @param sourceFB the initial source function block
     * @param destFB  the destination function block
     */
    private void calculateCorrectCoordinates(functionBlock sourceFB, functionBlock destFB, String sourcePort, String destPort, ConnectionInfo connInfo){
        
        int SourceCoorX = (int)sourceFB.getLocationX() / 3;
        int SourceCoorY = (int)sourceFB.getLocationY() / 3;
        int DestCoorX = (int)destFB.getLocationX() / 3;
        int DestCoorY = (int)destFB.getLocationY() / 3;
        
        // add the gap of function block's width 
        SourceCoorX += sourceFB.getFunctionBlockCoordinates().getXCoorArray()[1];
        
        // source 
        // target the line to the port, it can be either outputdata or outputevent.
        for(int i = 0; i < sourceFB.getOutputData().size(); i++){
            if(sourcePort.equals(sourceFB.getOutputData().get(i).getName())){
                
                SourceCoorY += sourceFB.getOutputData().get(i).getCoordinate().coordinateY + 5;
            }
            
        }
        for(int i = 0; i < sourceFB.getOutputEvent().size(); i++){
            if(sourcePort.equals(sourceFB.getOutputEvent().get(i).getName())){
                
                SourceCoorY += sourceFB.getOutputEvent().get(i).getCoordinate().coordinateY + 5;
            } 
        }
        
        this.connectionCoordinates.add(new Coordinate(SourceCoorX, SourceCoorY));

        
        // handle with dx Value, dx value represents the point of turn's location.
        ArrayList<Double> dxArray = connInfo.getDxArray();
        ArrayList<Double> dyArray = connInfo.getDyArray();
        
        int loopSize = dxArray.size() > dyArray.size()? dxArray.size():dyArray.size();
        
        for(int i = 0; i < loopSize; i++){
            
            // if the dx value exist
            if(dxArray.get(i) != null){
                // convert double to int, and devided by zoom value
                int dxIntVal = (int) (dxArray.get(i) / 1.8);
                
                Coordinate lastCoor = this.connectionCoordinates.get(this.connectionCoordinates.size() - 1);
                Coordinate dxCoor = new Coordinate(lastCoor.coordinateX + dxIntVal, lastCoor.coordinateY);
                
                this.connectionCoordinates.add(dxCoor);
            }
            
            
            // space for dy.
        }
        
        // destination 
        // target the line to the port, it can be either inputdata or inputevent.
        for(int i = 0; i < destFB.getInputData().size(); i++){
            
            if(destPort.equals(destFB.getInputData().get(i).getName())){
                DestCoorY += destFB.getInputData().get(i).getCoordinate().coordinateY + 5;
            }
        }
        for(int i = 0; i < destFB.getInputEvent().size(); i++){
            
            if(destPort.equals(destFB.getInputEvent().get(i).getName())) {
                DestCoorY += destFB.getInputEvent().get(i).getCoordinate().coordinateY + 5;
            }
        }
        this.connectionCoordinates.add(new Coordinate(DestCoorX, DestCoorY));

        
        // fill the space between destination and last position.
        // get last one (destination information
        
        Coordinate destinationCoor = this.connectionCoordinates.get(this.connectionCoordinates.size() - 1);
        Coordinate beforeDestCoor = this.connectionCoordinates.get(this.connectionCoordinates.size() - 2);
        
        if(destinationCoor.coordinateY - beforeDestCoor.coordinateY != 0){
            
            Coordinate fillSpaceCoor = new Coordinate(beforeDestCoor.coordinateX,destinationCoor.coordinateY);
            
            this.connectionCoordinates.add(this.connectionCoordinates.size() - 1, fillSpaceCoor);
            System.out.println(destinationCoor.coordinateY - beforeDestCoor.coordinateY);
        }
        
    }
    
    
    public void getherCoorInfo(){
        
        coorX = new int[this.connectionCoordinates.size()];
        coorY = new int[this.connectionCoordinates.size()];
        
        for(int i = 0; i < connectionCoordinates.size(); i++){
            coorX[i] = connectionCoordinates.get(i).coordinateX;
            coorY[i] = connectionCoordinates.get(i).coordinateY;

        }
        
    }

    public int[] getCoorX() {
        return coorX;
    }

    public int[] getCoorY() {
        return coorY;
    }
    
    
    
}
