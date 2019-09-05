
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author daltonchen
 */

public class FunctionBlockCoordinates {

    
    // new port! fix them~
    private FunctionBlock fb;
    private Coordinate size;
    private Coordinate functionBlockNameCoordinates;
    private Coordinate[] inputEventCoordinates;
    private Coordinate[] outputEventCoordinates;
    private Coordinate[] inputDataCoordinates;
    private Coordinate[] outputDataCoordinates;
    private Coordinate[] fbcoordinates;

    public FunctionBlockCoordinates(FunctionBlock fb) {
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
        
        //Event Input
        int length = fb.getEinputNodes().length;
        this.inputEventCoordinates = new Coordinate[length];
        
        for(int i = 0; i < length; i++){
            
            Coordinate coor;
            
            if (i == 0){
                coor = new Coordinate(3, 5);
            } else {
                coor = new Coordinate(3, this.inputEventCoordinates[i - 1].coordinateY + 15);
            }
            
            inputEventCoordinates[i] = coor;
        }
        
    }

    public void xyCalculator(int i) {

        int number = i;
        int fbNameLength = (int) (this.fb.getFBName().length() * 7f);

        switch (number) {
            case 1:
                // case 1 means location 2, this number should be same as location 8
                fbcoordinates[number].coordinateX = fbcoordinates[number - 1].coordinateX + 60 + fbNameLength;
                break;
            case 2:
                // case 2 means location 3
                fbcoordinates[number].coordinateY = fbcoordinates[number - 1].coordinateY + 80;
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
                fbcoordinates[number].coordinateY = fbcoordinates[number - 1].coordinateY + 30;
                break;
            case 7:
                //case 7 means location 8, this number should be same as location 2
                fbcoordinates[number].coordinateX = fbcoordinates[number - 1].coordinateX - 60 - fbNameLength;
                break;
            case 8:
                // case 8 means location 9
                fbcoordinates[number].coordinateY = fbcoordinates[number - 1].coordinateY - 30;
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

    public Coordinate[] getInputEventCoordinates() {
        return inputEventCoordinates;
    }

    public Coordinate[] getOutputEventCoordinates() {
        return outputEventCoordinates;
    }

    public Coordinate[] getInputDataCoordinates() {
        return inputDataCoordinates;
    }

    public Coordinate[] getOutputDataCoordinates() {
        return outputDataCoordinates;
    }
    
}
