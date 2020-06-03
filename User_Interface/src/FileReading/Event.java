/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileReading;

import UIdisplay.Coordinate;
import java.util.ArrayList;

public class Event {
    
    private String Comment;
    private String Name;
    private String Type;
    private String initialValue;
    
    private Coordinate coordinate;
    
    private ArrayList<NodeWith> withInfo;

    public Event(String Comment, String Name, String Type, ArrayList<NodeWith> withInfo) {
        this.Comment = Comment;
        this.Name = Name;
        this.Type = Type;
        this.withInfo = withInfo;
    }

    public String getComment() {
        return Comment;
    }


    public String getName() {
        return Name;
    }


    public String getType() {
        return Type;
    }


    public ArrayList<NodeWith> getWithInfo() {
        return withInfo;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public String getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(String initialValue) {
        this.initialValue = initialValue;
    }
    
    

}
