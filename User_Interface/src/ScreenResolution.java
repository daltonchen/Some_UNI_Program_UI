/**
 * This class is designed to hold the screen resolution information,this class will be used when generates
 * the frame, it will set a default resoulution of width of 1000 and height of 1000, 
 * this code will be called and used in the UI_Frame.java
 * 
 * @author dalton chen
 *
 */

public class ScreenResolution {
	private int ScreenHeight;
	private int ScreenWidth;
	
	public ScreenResolution() {
		this.ScreenHeight = 1000;
		this.ScreenWidth = 1000;
	}
	
	public int getWidth() {
		return ScreenWidth;
	}
	
	public void setWidth(int width) {
		this.ScreenWidth = width - 500;
	}
	
	public int getHeight() {
		return ScreenHeight;
	}
	
	public void setHeight(int height) {
		this.ScreenHeight = height - 300;
	}
}
