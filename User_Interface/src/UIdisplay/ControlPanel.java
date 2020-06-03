/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIdisplay;

import FileReading.Data;
import FileReading.Event;
import FileReading.SystemInfo;
import FileReading.functionBlock;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FileChooserUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Highlighter;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter.HighlightPainter;

/**
 *
 * @author daltonchen
 */
public class ControlPanel extends JPanel{
    
    private SystemInfo sysInfo;
    private JTextArea infoField;
    private JTable table;
    // uiframe used to set output to console, in here it will be used to update the sys file.
    private UI_Frame frame;

    
    public ControlPanel(UI_Frame frame){
        this.frame = frame;
        this.setPreferredSize(new Dimension(300, 300));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        this.setBorder(BorderFactory.createTitledBorder("Control Panel"));
        addButtons();
        DrawTable();
        addConsole();
        tableTypeSysUpdate();

    }
    
    private void addConsole(){
        
        infoField = new JTextArea("");
        infoField.setLineWrap(true);
        infoField.setWrapStyleWord(true);
        infoField.setEditable(false);
        infoField.setBackground(Color.BLACK);
        infoField.setForeground(Color.WHITE);
        
        JScrollPane consoleScroll = new JScrollPane(infoField);
        consoleScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        consoleScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        consoleScroll.setBorder(BorderFactory.createTitledBorder("Console"));
        consoleScroll.setPreferredSize(new Dimension(300, 400));

        this.add(consoleScroll);
    }
    public void addWordToConsole(String input, ConsoleEnum msgType){
        
        int currentTextLength = this.infoField.getText().length();
        String newMessage = msgType.getDescribe() + input + "\n";
                
        if(msgType != ConsoleEnum.NORMAL_MESSAGE){
            int p0 = currentTextLength;
            int p1 = msgType.getDescribe().length() + currentTextLength;

            this.infoField.append(newMessage);
            
            Highlighter highligter = infoField.getHighlighter();
            HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(msgType.getColor());
            
            try{
                highligter.addHighlight(p0, p1, painter);
            } catch(BadLocationException e){
                // do nothing
            }
        } else {
            this.infoField.append(newMessage);
        }
        
        this.infoField.setCaretPosition(this.infoField.getDocument().getLength());
    }
    
    private void addButtons(){
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Control"));
        
        JButton loadButton = new JButton("Load Files");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int result = JOptionPane.YES_OPTION;
                
                if(sysInfo != null){
                    result = JOptionPane.showOptionDialog(ControlPanel.this, "Do you wish to replace current system files", "Read File", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
                }

                if(result == JOptionPane.YES_OPTION){
                    FileNameExtensionFilter filter = new FileNameExtensionFilter(".sys - System files", "sys");
                    JFileChooser fc = new JFileChooser();
                    fc.setFileFilter(filter);
                    fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
                    int returnVal = fc.showOpenDialog(ControlPanel.this);

                    if(returnVal == JFileChooser.APPROVE_OPTION){
                        File sysFile = fc.getSelectedFile();

                        String path = sysFile.getParent() + "/";
                        String sysName = sysFile.getName();
                        frame.addMessageToConsole("File: " + sysName + " is being selected", ConsoleEnum.SUCCESS_MESSAGE);
                        frame.loadSysFile(path, sysName);
                    }
                }
                
            }
        });
        buttonPanel.add(loadButton);
        
        JButton cleanButton = new JButton("Clean Console");
        cleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                infoField.setText("");
            }
        });
        buttonPanel.add(cleanButton);
        
        this.add(buttonPanel);
    }
    
    private void DrawTable(){
        
        table = new JTable();
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tableScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tableScroll.setBorder(BorderFactory.createTitledBorder("Parameters"));

        this.add(tableScroll);
        
//        this.add(table.getTableHeader(), BorderLayout.NORTH);
//        this.add(table, BorderLayout.CENTER);
    }
    
    public void tableTypeSysUpdate(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Parameters");
        model.addColumn("Value");
        
        if(this.sysInfo != null){
            model.insertRow(0, new Object[]{"Document Name", this.sysInfo.getDocumentName()});
            model.insertRow(1, new Object[]{"Document Comment", this.sysInfo.getDocumentComment()});
            model.insertRow(2, new Object[]{"Application Name", this.sysInfo.getApplicationName()});
            model.insertRow(3, new Object[]{"Application Comment", this.sysInfo.getApplicationComment()});
            model.insertRow(4, new Object[]{"Author", this.sysInfo.getVersionAuthor()});
            model.insertRow(5, new Object[]{"Organization", this.sysInfo.getVersionOrganization()});
            model.insertRow(6, new Object[]{"Version", this.sysInfo.getVersionVersion()});
            model.insertRow(7, new Object[]{"Number of Function Blocks", this.sysInfo.getFunctionBlocks().size()});
            model.insertRow(8, new Object[]{"Number of Event Connections", this.sysInfo.getEventConn().size()});
            model.insertRow(9, new Object[]{"Number of Data Connections", this.sysInfo.getDataConn().size()});
            this.addWordToConsole("Load .sys info to table", ConsoleEnum.SUCCESS_MESSAGE);

        }
        
        this.table.setModel(model);
    }
    public void tableButtonEventGenerator(Event event){
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Parameters");
        model.addColumn("Value");
        
        if(event != null){
            model.insertRow(0, new Object[]{"Node Name", event.getName()});
            model.insertRow(1, new Object[]{"Node Comment", event.getComment()});
            model.insertRow(2, new Object[]{"Node Type", event.getType()});
            model.insertRow(3, new Object[]{"Initial Value", event.getInitialValue() == null? "N/A":event.getInitialValue()});
            model.insertRow(4, new Object[]{"Node Location X", event.getCoordinate().coordinateX});
            model.insertRow(5, new Object[]{"Node Location Y", event.getCoordinate().coordinateY});
            
            this.addWordToConsole("Load node info(Event) to table", ConsoleEnum.SUCCESS_MESSAGE);

        }
        
        this.table.setModel(model);
    }
    public void tableButtonDataGenerator(Data data){
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Parameters");
        model.addColumn("Value");
        
        if(data != null){
            model.insertRow(0, new Object[]{"Node Name", data.getName()});
            model.insertRow(1, new Object[]{"Node Comment", data.getComment()});
            model.insertRow(2, new Object[]{"Node Type", data.getType()});
            model.insertRow(3, new Object[]{"Initial Value", data.getInitialValue() == null ? "N/A" : data.getInitialValue()});
            model.insertRow(4, new Object[]{"Node Location X", data.getCoordinate().coordinateX});
            model.insertRow(5, new Object[]{"Node Location Y", data.getCoordinate().coordinateY});
            
            this.addWordToConsole("Load node info(Data) to table", ConsoleEnum.SUCCESS_MESSAGE);

        }
        
        this.table.setModel(model);
    }
    
    public void tableFunctionBlockGenerator(functionBlock fb){
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Parameters");
        model.addColumn("Value");
        
        if(fb.isComplete() == true){
            model.insertRow(0, new Object[]{"Function Block Name", fb.getName()});
            model.insertRow(1, new Object[]{"Function Block Comment", fb.getComment()});
            model.insertRow(2, new Object[]{"Function Block Type", fb.getType()});
            model.insertRow(3, new Object[]{"Function Block Location X", (int)fb.getLocationX()});
            model.insertRow(4, new Object[]{"Function Block Location Y", (int)fb.getLocationY()});
            model.insertRow(5, new Object[]{"Application Comment", fb.getInnerComment()});
            model.insertRow(6, new Object[]{"Number of Input Event", fb.getInputEvent().size()});
            model.insertRow(7, new Object[]{"Number of Output Event", fb.getOutputEvent().size()});
            model.insertRow(8, new Object[]{"Number of Input Data", fb.getInputData().size()});
            model.insertRow(9, new Object[]{"Number of Output Data", fb.getOutputData().size()});
            
            ArrayList<Event> event = new ArrayList<Event>();
            event.addAll(fb.getInputEvent());
            event.addAll(fb.getOutputEvent());
            
            ArrayList<Data> data = new ArrayList<Data>();
            data.addAll(fb.getInputData());
            data.addAll(fb.getOutputData());
            
            int number = 10;
            for(Event eve : event){
                model.insertRow(number, new Object[]{eve.getName(), eve.getInitialValue() == null? "N/A" : eve.getInitialValue()});
                number ++;
            }
            for(Data dat : data){
                model.insertRow(number, new Object[]{dat.getName(), dat.getInitialValue() == null? "N/A" : dat.getInitialValue()});
                number ++;
            }
            
            this.addWordToConsole("Load Function Block " + fb.getName() + " info to table", ConsoleEnum.SUCCESS_MESSAGE);
            this.table.setModel(model);

        } else {
            
            model.insertRow(0, new Object[]{"Function Block Name", fb.getName()});
            model.insertRow(1, new Object[]{"Function Block Comment", fb.getComment()});
            model.insertRow(2, new Object[]{"Function Block Type", fb.getType()});
            model.insertRow(3, new Object[]{"Function Block Location X", (int)fb.getLocationX()});
            model.insertRow(4, new Object[]{"Function Block Location Y", (int)fb.getLocationY()});
            this.table.setModel(model);
            this.addWordToConsole("Partial Function Block information is Being Load: " + fb.getName() + " (.fbt File is Missing or Incomplete)", ConsoleEnum.WARNING);

        }
    }
    
    
    public void setSysInfo(SystemInfo sysInfo) {
        this.sysInfo = sysInfo;
        this.tableTypeSysUpdate();
    }
    
    
}
