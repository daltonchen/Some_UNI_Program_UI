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
    
    private String InnerComment;
    private String standard;
    private String author;
    private String date;
    private String version;
    
    private ArrayList<FBPara> fbParameters;
    private ArrayList<Event> inputEvent;
    private ArrayList<Event> outputEvent;
    private ArrayList<Data> inputData;
    private ArrayList<Data> outputData;
 

    public functionBlock(String comment, String name, String type, double locationX, double locationY, ArrayList<FBPara> fbParameters) {
        this.comment = comment;
        this.name = name;
        this.type = type;
        this.locationX = locationX;
        this.locationY = locationY;
        this.fbParameters = fbParameters;
    }

    public void setInnerComment(String InnerComment) {
        this.InnerComment = InnerComment;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setInputEvent(ArrayList<Event> inputEvent) {
        this.inputEvent = inputEvent;
    }

    public void setOutputEvent(ArrayList<Event> outputEvent) {
        this.outputEvent = outputEvent;
    }

    public void setInputData(ArrayList<Data> inputData) {
        this.inputData = inputData;
    }

    public void setOutputData(ArrayList<Data> outputData) {
        this.outputData = outputData;
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

    public String getInnerComment() {
        return InnerComment;
    }

    public String getStandard() {
        return standard;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public String getVersion() {
        return version;
    }

    public ArrayList<Event> getInputEvent() {
        return inputEvent;
    }

    public ArrayList<Event> getOutputEvent() {
        return outputEvent;
    }

    public ArrayList<Data> getInputData() {
        return inputData;
    }

    public ArrayList<Data> getOutputData() {
        return outputData;
    }
    
    
}
