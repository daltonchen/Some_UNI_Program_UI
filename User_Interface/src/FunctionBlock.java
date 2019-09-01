
public class FunctionBlock {
	private String FBName;
	private int LocationX;
	private	int LocationY;
	
	public FunctionBlock(int locX, int locY, String fbName) {
		this.setLocationX(locX);
		this.setLocationY(locY);
		this.setFBName(fbName);
	}
	
	public FunctionBlock() {
		this.setLocationX(300);
		this.setLocationY(300);
		this.setFBName("Default");
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
	
	
}
