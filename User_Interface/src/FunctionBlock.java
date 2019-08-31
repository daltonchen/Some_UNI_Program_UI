
public class FunctionBlock {
	private int LocationX;
	private	int LocationY;
	
	public FunctionBlock(int locX, int locY) {
		this.setLocationX(locX);
		this.setLocationY(locY);
	}
	
	public FunctionBlock() {
		this.setLocationX(300);
		this.setLocationY(300);
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
	
	
}
