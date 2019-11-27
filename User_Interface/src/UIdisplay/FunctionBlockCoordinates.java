package UIdisplay;


import FileReading.Event;
import FileReading.functionBlock;
import java.util.ArrayList;
import java.util.Collections;


/*
 * This class will automatically calculate the coordinate information for the function block
 * it will later being used for drawing.
 */

/**
 *
 * @author daltonchen
 */


public class FunctionBlockCoordinates {

    // new port! fix them~
    private functionBlock fb;
    private Coordinate size;
    private Coordinate[] fbcoordinates;
//    private int fbNameLength;

    public FunctionBlockCoordinates(functionBlock fb) {
        this.fb = fb;
        fbcoordinates = new Coordinate[13];

        for (int i = 0; i < fbcoordinates.length; i++) {
            fbcoordinates[i] = new Coordinate(0, 0);
        }
        DrawingCoordinateCalculator();
        NodeCoordinateCalculator();
    }

    public void DrawingCoordinateCalculator() {

        fbcoordinates[0].coordinateX = 0;
        fbcoordinates[0].coordinateY = 0;

        fbcoordinates[fbcoordinates.length - 1].coordinateX = 0;
        fbcoordinates[fbcoordinates.length - 1].coordinateY = 0;

        for (int i = 1; i < fbcoordinates.length - 1; i++) {

            xyCalculator(i);

            if (i % 2 == 1) {
                // if the number is the odd number, means that the y coordinate of this one will be same as previous one
                fbcoordinates[i].coordinateY = fbcoordinates[i - 1].coordinateY;

            } else {
                // if the number is even number, means that the x coordinate is the same as the previous one.
                fbcoordinates[i].coordinateX = fbcoordinates[i - 1].coordinateX;
            }
        }
        
        // get the size for the function block it will be the coordinate 0 with the largest point of this function block
        this.size = fbcoordinates[6];

    }
    
    
    public void NodeCoordinateCalculator(){
        
        if(this.fb.getInputEvent() != null){
            //Event Input
            for(int i = 0; i < this.fb.getInputEvent().size(); i++){

                Coordinate coor = new Coordinate(3, 10 + 15 * i);

                this.fb.getInputEvent().get(i).setCoordinate(coor);

            }
        }
        
        if(this.fb.getOutputEvent() != null){
            //Event output
            for(int i = 0; i < this.fb.getInputEvent().size(); i++){

                Coordinate coor = new Coordinate(this.getXCoorArray()[1] - 13, 10 + 15 * i);

                this.fb.getOutputEvent().get(i).setCoordinate(coor);

            }
        }
        
        if(this.fb.getInputData() != null){
            
            //for data input
            for(int i = 0; i < this.fb.getInputData().size(); i++){
                
                Coordinate coor = new Coordinate(3, this.getYCoorArray()[9] + 10 + 15 * i);
                
                this.fb.getInputData().get(i).setCoordinate(coor);
                
                System.out.println(coor.coordinateY);
            }
        }
        
        if(this.fb.getOutputData() != null){
            
            // for data output
            for(int i = 0; i < this.fb.getOutputData().size(); i++){
                
                Coordinate coor = new Coordinate(this.getXCoorArray()[1] - 13, this.getYCoorArray()[9] + 10 + 15 * i);
                
                this.fb.getOutputData().get(i).setCoordinate(coor);
            }
        }
        
    }
    
    public int calculateDrawingLowerHeight(){
        
        int lower = 20;
        
        if(this.fb.getInputData() != null){
            
            if((this.fb.getInputData().size() * 15 + 30) > lower){
                lower = this.fb.getInputData().size() * 15 + 30;
            }
        }
        if(this.fb.getOutputData() != null){
            
            if((this.fb.getOutputData().size() * 15 + 30) > lower){
                lower = this.fb.getOutputData().size() * 15 + 30;
            }
        }
        
        return lower;
    }
    
    public int calculateDrawingUpperHeight(){
        
        // the default value would be 30, 15 for upper gap and 15 for lower gap
        int upper = 30;
        
        if(this.fb.getInputEvent() != null){
            
            int countHeight = this.fb.getInputEvent().size() * 15 + 30;
            
            if(countHeight > upper){
                upper = countHeight;
            }
        }
        
        if(this.fb.getOutputEvent() != null){
            
            int countHeight = this.fb.getOutputEvent().size() * 15 + 30;
            
            if(countHeight > upper){
                upper = countHeight;
            }
        }
        
        return upper;
    }
    
    public int calculateDrawingWidth(){

        int maximum = (int)(this.fb.getName().length()* 7f);
        
        // this method will count the size of the name for the event input as well as the event output, it will able to
        // provide funtionality that it will automatically adjust based on the length of the text as well as the name.
        
        if(this.fb.getInputEvent() != null){
            
            for (int i = 0; i < this.fb.getInputEvent().size(); i++){
                
                int length = (int)(this.fb.getInputEvent().get(i).getName().length() * 7f);
                
                if(this.fb.getOutputEvent().get(i) != null){
                    length += (int)(this.fb.getOutputEvent().get(i).getName().length() * 7f);
                }
                
                if (length > maximum){
                    maximum = length;
                }
            }
        }
        
        if(this.fb.getOutputEvent() != null){
            for(int i = 0; i < this.fb.getOutputEvent().size(); i++){
                
                int length = (int)(this.fb.getOutputEvent().get(i).getName().length() * 7f);
                
                if (length > maximum){
                    maximum = length;
                }
            }
        }
        
        
        if(this.fb.getInputData() != null){

            for(int i = 0; i < this.fb.getInputData().size(); i++){
                
                int length = (int)(this.fb.getInputData().get(i).getName().length() * 7f);
                
                if(this.fb.getOutputData() != null){
                    length += (int)(this.fb.getOutputData().get(i).getName().length() * 7f);
                }
                
                if(length > maximum){
                    maximum = length;
                }
            }

        }
        
        if(this.fb.getOutputData()!= null){
            for(int i = 0; i < this.fb.getOutputData().size(); i++){
                
                int length = (int)(this.fb.getOutputData().get(i).getName().length() * 7f);
                
                if (length > maximum){
                    maximum = length;
                }
            }
        }



        return maximum;
    }

    public void xyCalculator(int i) {

        int number = i;
        int drawingWidth = calculateDrawingWidth();
        int drawingUpperHeight = calculateDrawingUpperHeight();
        int drawingLowerHeight = calculateDrawingLowerHeight();
        
        switch (number) {
            case 1:
                // case 1 means location 2, this number should be same as location 8
                fbcoordinates[number].coordinateX = fbcoordinates[number - 1].coordinateX + 60 + drawingWidth;
                break;
            case 2:
                // case 2 means location 3
                fbcoordinates[number].coordinateY = fbcoordinates[number - 1].coordinateY + drawingUpperHeight;
                break;
            case 3:
            case 11:
                // case 3 means location 4, case 11 means location 11, those number should be fixed at 15
                // this is part of rabbet of the function.
                fbcoordinates[number].coordinateX = fbcoordinates[number - 1].coordinateX - 15;
                break;
            case 4:
                // case 4 means location 5, this number should be fixed at 20.
                fbcoordinates[number].coordinateY = fbcoordinates[number - 1].coordinateY + 20;
                break;
            case 5:
            case 9:
                // case 5 means location 6, case 9 means location 10 ,those number should be fixed at 15
                // this is part of the rabbet of the function block.
                fbcoordinates[number].coordinateX = fbcoordinates[number - 1].coordinateX + 15;
                break;
            case 6:
                // case 6 means location 7
                fbcoordinates[number].coordinateY = fbcoordinates[number - 1].coordinateY + drawingLowerHeight;
                break;
            case 7:
                //case 7 means location 8, this number should be same as location 2
                fbcoordinates[number].coordinateX = fbcoordinates[number - 1].coordinateX - 60 - drawingWidth;
                break;
            case 8:
                // case 8 means location 9
                fbcoordinates[number].coordinateY = fbcoordinates[number - 1].coordinateY - drawingLowerHeight;
                break;
            case 10:
                // case 10 means location 11, this number should be fixed at 20.
                fbcoordinates[number].coordinateY = fbcoordinates[number - 1].coordinateY - 20;
                break;
        }
    }
    

    public Coordinate getFBNameCoordinate() {

        Coordinate coor = new Coordinate(0, this.fbcoordinates[10].coordinateY);

        return coor;
    }
    
    public Coordinate getSize(){
        return this.size;
    }

    public int[] getXCoorArray() {

        int[] xArray = new int[13];

        for (int i = 0; i < fbcoordinates.length; i++) {
            xArray[i] = fbcoordinates[i].coordinateX;
        }

        return xArray;
    }

    public int[] getYCoorArray() {

        int[] yArray = new int[13];

        for (int i = 0; i < fbcoordinates.length; i++) {
            yArray[i] = fbcoordinates[i].coordinateY;
        }

        return yArray;
    }

    
}

