package UIdisplay;

public class Coordinate {
	public int coordinateX;
	public int coordinateY;
	
	public Coordinate(int valueX, int valueY) {
		this.coordinateX = valueX;
		this.coordinateY = valueY;
	}
	
        @Override
	public String toString() {
		return "The coorinate for X will be: " + this.coordinateX + " ,Coordinate for Y will be: " + this.coordinateY; 
	}
	
}
