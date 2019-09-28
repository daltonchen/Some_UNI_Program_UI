/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileReading;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class readIndividualFB {
    
    
    public readIndividualFB(String fileName, functionBlock fb){
        try{
            
            //test
            functionBlock fbWithNodes = readXML(fileName, fb);
            
            System.out.println(fbWithNodes.getInnerComment());
            
        } catch (DocumentException e){
            System.err.println(e);
        }
    }
    
    /*
    This class is reading the xml from the local file and return the finished product
    */
    public functionBlock readXML(String fileName, functionBlock fb) throws DocumentException{
        SAXReader reader = new SAXReader();
        Document doc = reader.read("./src/FileReading/" + fileName);
        
        
        Element rootElement = doc.getRootElement();
        
        // get name and comment from XML file.
        String FBName = rootElement.attributeValue("Name");
        String FBComment = rootElement.attributeValue("Comment");
        fb.setInnerComment(FBComment);
        
        // get identification element and information
        Element identificationElement = rootElement.element("Identification");
        String FBStandard = identificationElement.attributeValue("Standard");
        fb.setStandard(FBStandard);
        
        // get version element and information
        Element versionElement = rootElement.element("VersionInfo");
        String FBAuthor = versionElement.attributeValue("Author");
        String FBDate = versionElement.attributeValue("Date");
        String FBVersion = versionElement.attributeValue("Version");
        fb.setAuthor(FBAuthor);
        fb.setDate(FBDate);
        fb.setVersion(FBVersion);
        
        // get interface list
        Element interfaceElement = rootElement.element("InterfaceList");
        
        //get event input inforamtion
        Element eventInputElement = interfaceElement.element("EventInputs");
        ArrayList<Event> eventInput = getEvent(eventInputElement);
        fb.setInputEvent(eventInput);
        
        //get event output inforamtion
        Element eventOutputElement = interfaceElement.element("EventOutputs");
        ArrayList<Event> eventOutput = getEvent(eventOutputElement);
        fb.setOutputEvent(eventOutput);
        
        //get var input information
        Element varInputElement = interfaceElement.element("InputVars");
        ArrayList<Data> dataInput = getData(varInputElement);
        fb.setInputData(dataInput);
        
        //get var output information
        Element varOutputElement = interfaceElement.element("OutputVars");
        ArrayList<Data> dataOutput = getData(varOutputElement);
        fb.setOutputData(dataOutput);
        
        return fb;
        // simpleFB is not being read, it will need further discussion on it.
        
    }
    
    public ArrayList<Data> getData(Element dataInputElement){
        
        Iterator<Element> dataIterator = dataInputElement.elements("VarDeclaration").iterator();
        ArrayList<Data> Datas = new ArrayList<Data>();
        
        while(dataIterator.hasNext()){
            Element dataElement = dataIterator.next();
            
            String comment = dataElement.attributeValue("Comment");
            String initialValue = dataElement.attributeValue("InitialValue");
            String name = dataElement.attributeValue("Name");
            String type = dataElement.attributeValue("Type");
            
            Datas.add(new Data(comment, initialValue, name, type));
            
        }
        
        return Datas;
    }
    
    public ArrayList<Event> getEvent(Element eventInputElement){
        
        Iterator<Element> eventIterator = eventInputElement.elements("Event").iterator();
        ArrayList<Event> Events = new ArrayList<Event>();
        while(eventIterator.hasNext()){
            Element eventElement = eventIterator.next();
            
            String EventComment = eventElement.attributeValue("Comment");
            String EventName = eventElement.attributeValue("Name");
            String EventType = eventElement.attributeValue("Type");
            
            
            // get node with information
            ArrayList<NodeWith> withList = new ArrayList<NodeWith>();
            Iterator<Element> withIterator = eventElement.elements("With").iterator();
            
            while(withIterator.hasNext()){
                Element withElement = withIterator.next();
                String withInfo = withElement.attributeValue("Var");
                withList.add(new NodeWith(withInfo));
            }
            
            Events.add(new Event(EventComment, EventName, EventType, withList));
        }
        
        return Events;
    }
    
}
