
import java.util.ArrayList;


public class FunctionBlock {

    private String FBName;
    private int LocationX;
    private int LocationY;
    private EventInputNode[] EinputNodes;
    private EventOutputNode[] EoutputNodes;
    private DataInputNode[] DInputNode;
    private DataOutputNode[] DOutputNode;

    public FunctionBlock(int locX, int locY, String fbName) {
        this.setLocationX(locX);
        this.setLocationY(locY);
        this.setFBName(fbName);
    }

    public FunctionBlock() {
        this.setLocationX(300);
        this.setLocationY(300);
        this.setFBName("Default");
        
        //test component
        EventInputNode eiTestNode = new EventInputNode("test Input");
        
        EinputNodes = new EventInputNode[3];
        EinputNodes[0] = eiTestNode;
    }

    public int getLocationY() {
        return LocationY;
    }

    public void setLocationY(int locationY) {
        LocationY = locationY;
    }

    public int getLocationX() {
        return LocationX;
    }

    public void setLocationX(int locationX) {
        LocationX = locationX;
    }

    public String getFBName() {
        return FBName;
    }

    public void setFBName(String fBName) {
        FBName = fBName;
    }

    public EventInputNode[] getEinputNodes() {
        return EinputNodes;
    }

    public void setEinputNodes(EventInputNode[] EinputNodes) {
        this.EinputNodes = EinputNodes;
    }

    public EventOutputNode[] getEoutputNodes() {
        return EoutputNodes;
    }

    public void setEoutputNodes(EventOutputNode[] EoutputNodes) {
        this.EoutputNodes = EoutputNodes;
    }

    public DataInputNode[] getDInputNode() {
        return DInputNode;
    }

    public void setDInputNode(DataInputNode[] DInputNode) {
        this.DInputNode = DInputNode;
    }

    public DataOutputNode[] getDOutputNode() {
        return DOutputNode;
    }

    public void setDOutputNode(DataOutputNode[] DOutputNode) {
        this.DOutputNode = DOutputNode;
    }

    
}
