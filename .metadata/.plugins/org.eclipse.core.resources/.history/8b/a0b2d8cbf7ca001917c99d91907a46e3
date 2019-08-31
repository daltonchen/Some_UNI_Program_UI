import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class UI_Frame {
	
	JFrame uiFrame;
	JPanel uiPanel;
	
	public void ShowFrame() {
		
		uiFrame = new JFrame();
		uiPanel = new JPanel();
		
		// get user's screen resolution
		ScreenResolution sr = getScreenResolution();
		
		//set default exit process
		uiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		uiFrame.setTitle("IEC61499 Debug");
		uiFrame.setSize(sr.getWidth(), sr.getHeight());
		
		// set the frame always showed at the center point of the screen.
		uiFrame.setLocationRelativeTo(null);
		uiFrame.setVisible(true);
	}
	
	public ScreenResolution getScreenResolution() {
		
		// the default screenresolution value would be 1000 * 1000;
		ScreenResolution sr = new ScreenResolution();
		
		try {
			// get multi-monitor configuration (default one)
			GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			
			sr.setWidth(gd.getDisplayMode().getWidth());
			sr.setHeight(gd.getDisplayMode().getHeight());
			
		} catch(Error e) {
			System.err.println(e.getLocalizedMessage());
			System.err.println("Could Not Found Monitor, Default Size will be used.");
		}
		
		return sr;
	}
}
