package UIdisplay;


import FileReading.Data;
import FileReading.Event;
import FileReading.ReadSys;
import FileReading.SystemInfo;
import FileReading.functionBlock;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import org.dom4j.DocumentException;

public class UI_Frame {

    JFrame uiFrame;
    JPanel uiPanel;
    ControlPanel controlPanel;
    DisplayCanvas cvs;
    Dimension defaultSize = new Dimension(3000, 3000);


    public void ShowFrame() {

        uiFrame = new JFrame();
        uiPanel = new JPanel();


        //set default exit process
        uiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        uiFrame.setTitle("IEC61499 Debug");
        uiFrame.setMinimumSize(new Dimension(1000, 600));
        uiFrame.setLayout(new BorderLayout());
        
        
        controlPanel = new ControlPanel(this);
        uiFrame.add(controlPanel,BorderLayout.WEST);
        
        cvs = new DisplayCanvas(defaultSize, this);
        cvs.setPreferredSize(defaultSize);

        JScrollPane scroll = new JScrollPane(cvs, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(BorderFactory.createTitledBorder("Canvas"));
        
        
        uiFrame.add(scroll, BorderLayout.CENTER);
        addMessageToConsole("UI ready", ConsoleEnum.SUCCESS_MESSAGE);
        addMessageToConsole("Canvas is empty", ConsoleEnum.NOTICE);

        // set the frame always show at the center point of the screen.
        uiFrame.setLocationRelativeTo(null);
        uiFrame.setVisible(true);
                
        addMessageToConsole("Please Load .sys File.", ConsoleEnum.NOTICE);

    }

    public Dimension calculateCanvasSize(ArrayList<functionBlock> fbs){
        
        int maxWidth = 0;
        int maxHeight = 0;
        
        if(fbs != null){
            for(functionBlock fb : fbs){
                if((int)fb.getLocationX() > maxWidth){
                    maxWidth = (int)fb.getLocationX()/3 + 1000;
                }
                if((int)fb.getLocationY() > maxHeight){
                    maxHeight = (int)fb.getLocationY()/3 + 1000;
                }
            }
        } else {
            maxHeight = 3000;
            maxWidth = 3000;
        }
        
        Dimension size = new Dimension(maxWidth, maxHeight);
        
        return size;
    }
    
    public void addMessageToConsole(String input, ConsoleEnum msgType){
        controlPanel.addWordToConsole(input, msgType);
    }
    public void changeTableToFunctionBlockMode(functionBlock fb){
        controlPanel.tableFunctionBlockGenerator(fb);
    }
    public void changeTableToSystemMode(){
        controlPanel.tableTypeSysUpdate();
    }
    public void changeTableToButton_Event(Event event){
        controlPanel.tableButtonEventGenerator(event);
    }
    public void changeTableToButton_Data(Data data){
        controlPanel.tableButtonDataGenerator(data);
    }
    public void loadSysFile(String path, String sysName){
        
        SystemInfo sysInfo = null;
        
        try{
            ReadSys rd = new ReadSys();
            sysInfo = rd.SysRead(path + sysName);
            addMessageToConsole("Read .sys File...", ConsoleEnum.INPROGRESS);
        } catch (DocumentException e){
            addMessageToConsole("Unable to Load .sys File...", ConsoleEnum.ERROR_MESSAGE);
            addMessageToConsole(e.toString(), ConsoleEnum.ERROR_MESSAGE);

        }
        
        if(sysInfo != null){
            addMessageToConsole("Loaded .sys File", ConsoleEnum.SUCCESS_MESSAGE);
            controlPanel.setSysInfo(sysInfo);
            
            Dimension preferredSize = this.calculateCanvasSize(sysInfo.getFunctionBlocks());
            this.cvs.setPreferredSize(preferredSize);
            addMessageToConsole("New Canvas Size: " + preferredSize.width + " x " + preferredSize.height + " is being set.", ConsoleEnum.SUCCESS_MESSAGE);
            
            this.cvs.removeAll();
            this.cvs.updateSysfile(sysInfo, path);
            this.cvs.revalidate();
            this.cvs.repaint();
            addMessageToConsole("Canvas is ready", ConsoleEnum.SUCCESS_MESSAGE);
        }
        

    }

}
