/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileReading;

/**
 *
 * @author daltonchen
 */
public class FBPara {
    private String Name;
    private String Value;

    public FBPara(String Name, String Value) {
        this.Name = Name;
        this.Value = Value;
    }
    
    public String getName() {
        return Name;
    }

    public String getValue() {
        return Value;
    }
    
    
}
