/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIdisplay;

import java.awt.Color;

/**
 *
 * @author daltonchen
 */
public enum ConsoleEnum {
    ERROR_MESSAGE(Color.RED, "ERROR: "), NORMAL_MESSAGE(Color.BLACK, ""),
    SUCCESS_MESSAGE(new Color(27, 129, 62), "SUCCESS: "), INPROGRESS(new Color(201, 152, 51), "TRYING... "),
    WARNING(new Color(181, 68, 52), "WARNING: "), USER_ACTION(new Color(0, 92, 175),"ACTION:  "),
    NOTICE(new Color(78, 79, 151), "NOTICE: ");
    
    private Color color;
    private String describe;
    private ConsoleEnum(Color color, String describe){
        this.color = color;
        this.describe = describe;
    }

    public Color getColor() {
        return color;
    }

    public String getDescribe() {
        return describe;
    }
}
