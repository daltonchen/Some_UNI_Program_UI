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
public class SystemInfo {
    
    private String documentName;
    private String documentComment;
    
    private String versionAuthor;
    private String versionOrganization;
    private String versionVersion;
    
    private String applicationName;
    private String applicationComment;
    
    private ArrayList<functionBlock> functionBlocks;
    private ArrayList<ConnectionInfo> eventConn;
    private ArrayList<ConnectionInfo> dataConn;

    public SystemInfo(String documentName, String documentComment, String versionAuthor, 
            String versionOrganization, String versionVersion, String applicationName, 
            String applicationComment, ArrayList<functionBlock> functionBlocks, 
            ArrayList<ConnectionInfo> eventConn, ArrayList<ConnectionInfo> dataConn) {
        this.documentName = documentName;
        this.documentComment = documentComment;
        this.versionAuthor = versionAuthor;
        this.versionOrganization = versionOrganization;
        this.versionVersion = versionVersion;
        this.applicationName = applicationName;
        this.applicationComment = applicationComment;
        this.functionBlocks = functionBlocks;
        this.eventConn = eventConn;
        this.dataConn = dataConn;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public String getApplicationComment() {
        return applicationComment;
    }

    public ArrayList<ConnectionInfo> getEventConn() {
        return eventConn;
    }

    public ArrayList<ConnectionInfo> getDataConn() {
        return dataConn;
    }

    public String getDocumentName() {
        return documentName;
    }

    public String getDocumentComment() {
        return documentComment;
    }

    public String getVersionAuthor() {
        return versionAuthor;
    }

    public String getVersionOrganization() {
        return versionOrganization;
    }

    public String getVersionVersion() {
        return versionVersion;
    }

    public ArrayList<functionBlock> getFunctionBlocks() {
        return functionBlocks;
    }
    
    
    
    
    
}
