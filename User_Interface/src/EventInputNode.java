/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author daltonchen
 */
public class EventInputNode extends NodeInformation{
    
    public EventInputNode(String NodeName){
        this.setNodeName(NodeName);
        this.setType(NodeType.EVENT_INPUT);
    }
}