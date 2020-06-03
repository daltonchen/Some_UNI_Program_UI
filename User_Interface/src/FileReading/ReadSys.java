/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileReading;

import java.util.ArrayList;
import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ReadSys {
    
    
    public SystemInfo SysRead(String filePath) throws DocumentException{
        
        SAXReader reader = new SAXReader();
        Document doc = reader.read(filePath);
        
        Element rootElement = doc.getRootElement();
        
        //get system level information 
        String documentName = rootElement.attributeValue("Name");
        String documentComment = rootElement.attributeValue("Comment");
        
        //get version informations
        Element versionElement = rootElement.element("VersionInfo");
        String versionAuthor = versionElement.attributeValue("Author");
        String organization = versionElement.attributeValue("Organization");
        String versionVersion = versionElement.attributeValue("Version");
        
        //get application informations
        Element applicationElements = rootElement.element("Application");
        String applicationName = applicationElements.attributeValue("Name");    
        String applicationComment = applicationElements.attributeValue("Comment");
        
        //get SubAppNetwork(function block information)
        Element subNetwork = applicationElements.element("SubAppNetwork");
        ArrayList<functionBlock> functionBlockInformation = subNetworkProcess(subNetwork);
        
        
        //read devices information
        Element devicesElement = rootElement.element("Device");
        
        Element resourcesElement = devicesElement.element("Resource");
        
        Element FBnetworkElement = resourcesElement.element("FBNetwork");
                
        
        //get eventConnections
        Element elementConnection = FBnetworkElement.element("EventConnections");
        ArrayList<ConnectionInfo> eventConns = gatherConnectionInformation(elementConnection, ConnType.EVENT);
        
        
        //get DataConnections
        Element dataConnection = FBnetworkElement.element("DataConnections");
        ArrayList<ConnectionInfo> dataConns = gatherConnectionInformation(dataConnection, ConnType.DATA);
        
        SystemInfo info = new SystemInfo(documentName, documentComment, versionAuthor, organization, versionVersion,
                applicationName, applicationComment, functionBlockInformation, eventConns, dataConns);
        
        return info;
    }
    
    private ArrayList<ConnectionInfo> gatherConnectionInformation(Element connectionEle, ConnType type){
        Iterator<Element> connectionElement = connectionEle.elements("Connection").iterator();
        ArrayList<ConnectionInfo> conn = new ArrayList<ConnectionInfo>();
        
        System.out.println(connectionElement);
        
        while(connectionElement.hasNext()){
            Element connEle = connectionElement.next();
            
            String comment = connEle.attributeValue("Comment");
            String destination =connEle.attributeValue("Destination");
            String source = connEle.attributeValue("Source");
            
            ConnectionInfo info = new ConnectionInfo(comment, destination, source, type);
            
            // get information for all dx.
            int dxCounter = 1;
            ArrayList<Double> dxArray = new ArrayList<Double>();

            // if dxcounter is greater than 1, it will continue processing, otherwise it will stop(means there's the end of the file)
            while(dxCounter != 0){
                String dxValue = connEle.attributeValue("dx" + dxCounter);
                
                if(dxValue != null){
                    dxArray.add(Double.parseDouble(dxValue));
                                        
                    dxCounter ++;
                } else {
                    // if there was no more data, then suspend the while loop.
                    dxCounter = 0;
                }
            }
            
            // space for get dy
            ArrayList<Double> dyArray = new ArrayList<Double>();

            
            info.setDxArray(dxArray);
            info.setDyArray(dyArray);
  
            conn.add(info);
        }
        
        return conn;
    }
    
    private ArrayList<functionBlock> subNetworkProcess(Element subnetElement){
        
        Iterator<Element> fbElement = subnetElement.elements("FB").iterator();
        ArrayList<functionBlock> fbs = new ArrayList<functionBlock>();
        
        while(fbElement.hasNext()){
            Element fbEle = fbElement.next();
            
            //get basic function block information
            String Comment = fbEle.attributeValue("Comment");
            String Name = fbEle.attributeValue("Name");
            String type = fbEle.attributeValue("Type");
            double locationX = Double.parseDouble(fbEle.attributeValue("x"));
            double locationY = Double.parseDouble(fbEle.attributeValue("y"));
                        
            //get lower parameter information
            ArrayList<FBPara> fbpara = parameterProcessing(fbEle);
            
            fbs.add(new functionBlock(Comment, Name, type, locationX, locationY, fbpara));
        }
        
        return fbs;
    }
    
    private ArrayList<FBPara> parameterProcessing(Element par){
        Iterator<Element> parElement = par.elements("Parameter").iterator();
        ArrayList<FBPara> FBparameter = new ArrayList<FBPara>();
        
        while(parElement.hasNext()){
            Element parEle = parElement.next();
            String ParName = parEle.attributeValue("Name");
            String ParValue = parEle.attributeValue("Value");
            
            FBparameter.add(new FBPara(ParName, ParValue));
        }
        
        return FBparameter;
    }
    
}
