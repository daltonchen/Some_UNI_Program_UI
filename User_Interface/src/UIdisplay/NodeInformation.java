package UIdisplay;


import java.awt.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author daltonchen
 */

enum NodeType{
    EVENT_INPUT(Color.BLACK), EVENT_OUTPUT(Color.CYAN),
    DATA_INPUT(Color.ORANGE), DATA_OUTPUT(Color.RED);
    
    private Color color;
    
    private NodeType(Color color){
        this.color = color;
    }

    public Color getColor(){
        return this.color;
    }
}
public class NodeInformation {
    private NodeType type;
    private String NodeName;

    
    public NodeInformation(String NodeName, NodeType type){
        this.type = type;
        this.NodeName = NodeName;
    }
    
    public NodeInformation(){
        this.type = NodeType.DATA_INPUT;
        this.NodeName = "Test Node";
    }
    
    
    public NodeType getType() {
        return type;
    }

    public void setType(NodeType type) {
        this.type = type;
    }

    public String getNodeName() {
        return NodeName;
    }

    public void setNodeName(String NodeName) {
        this.NodeName = NodeName;
    }
}
