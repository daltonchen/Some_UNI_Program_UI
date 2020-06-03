/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileReading;

import java.util.ArrayList;


public class ConnectionInfo {
    private String comment;
    private String destination;
    private String source;
    private ArrayList<Double> dxArray;
    private ArrayList<Double> dyArray;
    private ConnType type;

    public ConnectionInfo(String comment, String Destination, String Source, ConnType type) {
        this.comment = comment;
        this.destination = Destination;
        this.source = Source;
        this.type = type;
    }

    public ArrayList<Double> getDxArray() {
        return dxArray;
    }

    public void setDxArray(ArrayList<Double> dxArray) {
        this.dxArray = dxArray;
    }

    public ArrayList<Double> getDyArray() {
        return dyArray;
    }

    public void setDyArray(ArrayList<Double> dyArray) {
        this.dyArray = dyArray;
    }

    
   
    public String getComment() {
        return comment;
    }

    public String getDestination() {
        return destination;
    }

    public String getSource() {
        return source;
    }

    public ConnType getType() {
        return type;
    }

    public void setType(ConnType type) {
        this.type = type;
    }
    
    
}
