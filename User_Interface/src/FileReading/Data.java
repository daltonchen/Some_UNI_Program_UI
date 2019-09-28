/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileReading;

public class Data {
    private String comment;
    private String initialValue;
    private String name;
    private String type;

    public Data(String comment, String initialValue, String name, String type) {
        this.comment = comment;
        this.initialValue = initialValue;
        this.name = name;
        this.type = type;
    }
    

    public String getComment() {
        return comment;
    }

    public String getInitialValue() {
        return initialValue;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
    
    
    
}
