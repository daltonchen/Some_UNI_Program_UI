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
    
//    public static void main(String[] args){
//        // this class is design to read .sys file, it will later intergrate with the function block read.
//        
//        try{
//            ReadSys rd = new ReadSys();
//            rd.SysRead();
//            
//        }catch(DocumentException e){
//            System.err.println(e);
//        }
//        
//    }
    
    public SystemInfo SysRead() throws DocumentException{
        
        SAXReader reader = new SAXReader();
        Document doc = reader.read("./src/FileReading/testSYS.xml");
        
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
        
        //get eventConnections
        Element elementConnection = subNetwork.element("EventConnections");
        ArrayList<ConnectionInfo> eventConns = geatherConnectionInformation(elementConnection);
        
        
        //get DataConnections
        Element dataConnection = subNetwork.element("DataConnections");
        ArrayList<ConnectionInfo> dataConns = geatherConnectionInformation(dataConnection);
        
        SystemInfo info = new SystemInfo(documentName, documentComment, versionAuthor, organization, versionVersion,
                applicationName, applicationComment, functionBlockInformation, eventConns, dataConns);
        
        return info;
    }
    
    private ArrayList<ConnectionInfo> geatherConnectionInformation(Element connectionEle){
        Iterator<Element> connectionElement = connectionEle.elements("Connection").iterator();
        ArrayList<ConnectionInfo> conn = new ArrayList<ConnectionInfo>();
        
        while(connectionElement.hasNext()){
            Element connEle = connectionElement.next();
            
            String comment = connEle.attributeValue("Comment");
            String destination =connEle.attributeValue("Destination");
            String source = connEle.attributeValue("Source");
            
            ConnectionInfo info = new ConnectionInfo(comment, destination, source);
            
            try{
                double dx1 = Double.parseDouble(connEle.attributeValue("dx1"));
                info.setDx1(dx1);
                
            }catch (NullPointerException e){
                // this is means there is no such data called dx1, therefore it will ignore it.
            }
            
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
