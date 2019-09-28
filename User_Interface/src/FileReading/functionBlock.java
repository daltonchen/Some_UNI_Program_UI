/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileReading;

import java.util.ArrayList;

/**
 *
 * @author daltonchen
 */
public class functionBlock {
    
    private String comment;
    private String name;
    private String type;
    private double locationX;
    private double locationY;
    
    private ArrayList<FBPara> fbParameters;

    public functionBlock(String comment, String name, String type, double locationX, double locationY, ArrayList<FBPara> fbParameters) {
        this.comment = comment;
        this.name = name;
        this.type = type;
        this.locationX = locationX;
        this.locationY = locationY;
        this.fbParameters = fbParameters;
    }
    
    public String getComment() {
        return comment;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getLocationX() {
        return locationX;
    }

    public double getLocationY() {
        return locationY;
    }

    public ArrayList<FBPara> getFbParameters() {
        return fbParameters;
    }
    
    
}
