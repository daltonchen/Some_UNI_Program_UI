/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileReading;

public class ConnectionInfo {
    private String comment;
    private String Destination;
    private String Source;
    private double dx1;

    public ConnectionInfo(String comment, String Destination, String Source) {
        this.comment = comment;
        this.Destination = Destination;
        this.Source = Source;
    }
    
    

    public void setDx1(double dx1) {
        this.dx1 = dx1;
    }
    
    public double getDx1(){
        return this.dx1;
    }
    

    public String getComment() {
        return comment;
    }

    public String getDestination() {
        return Destination;
    }

    public String getSource() {
        return Source;
    }

    
    
    
}
