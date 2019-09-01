import java.awt.event.ItemEvent;

public class FunctionBlockCoordinates {
	private Coordinate[] fbcoordinates;
	
	public FunctionBlockCoordinates(){
		fbcoordinates = new Coordinate[13];
		
		for (int i = 0; i < fbcoordinates.length; i++) {
			fbcoordinates[i] = new Coordinate(0, 0);
		}
	}
	
	public void CoordinateCalculator(FunctionBlock fb) {


		fbcoordinates[0].coordinateX = fb.getLocationX();
		fbcoordinates[0].coordinateY = fb.getLocationY();
		
		fbcoordinates[fbcoordinates.length - 1].coordinateX = fb.getLocationX();
		fbcoordinates[fbcoordinates.length - 1].coordinateY = fb.getLocationY();
		
		

		for (int i = 1; i < fbcoordinates.length - 1; i++) {
			
			xyCalculator(i, fb);
			
			if (i % 2 == 1) {
				// if the number is the odd number, means that the y coordinate of this one will be same as previous one
				fbcoordinates[i].coordinateY = fbcoordinates[i - 1].coordinateY;

				
			} else {
				// if the number is even number, means that the x coordinate is the same as the previous one.
				fbcoordinates[i].coordinateX = fbcoordinates[i - 1].coordinateX;
			}
		}
		

		

	}
	public int[] getXCoorArray() {
		
		int[] xArray = new int[13];
		
		for(int i = 0; i < fbcoordinates.length; i++) {
			xArray[i] = fbcoordinates[i].coordinateX;
		}
		
		return xArray;
	}
	
	public int[] getYCoorArray() {
		
		int[] yArray = new int[13];
		
		for(int i = 0; i < fbcoordinates.length; i++) {
			yArray[i] = fbcoordinates[i].coordinateY;
		}
		
		return yArray;
	}
	
	public void xyCalculator(int i, FunctionBlock fb) {
		
		int number = i;
		int fbNameLength = (int)(fb.getFBName().length() * 5.5f);
		
//		if (fbNameLength >= 90) {
//			fbNameLength += 5;
//		}

		System.out.println(fbNameLength);
		
		switch(number) {
			case 1:
				// case 1 means location 2, this number should be same as location 8
				fbcoordinates[number].coordinateX = fbcoordinates[number - 1].coordinateX + 60 + fbNameLength;
				break;
			case 2:
				// case 2 means location 3
				fbcoordinates[number].coordinateY = fbcoordinates[number - 1].coordinateY + 80;
				break;
			case 3:
			case 11:
				// case 3 means location 4, case 11 means location 11, those number should be fixed at 15
				// this is part of rabbet of the function.
				fbcoordinates[number].coordinateX =  fbcoordinates[number - 1].coordinateX - 15;
				break;
			case 4:
				// case 4 means location 5, this number should be fixed at 20.
				fbcoordinates[number].coordinateY = fbcoordinates[number - 1].coordinateY + 20;
				break;
			case 5:
			case 9:
				// case 5 means location 6, case 9 means location 10 ,those number should be fixed at 15
				// this is part of the rabbet of the function block.
				fbcoordinates[number].coordinateX = fbcoordinates[number - 1].coordinateX + 15;
				break;
			case 6:
				// case 6 means location 7
				fbcoordinates[number].coordinateY = fbcoordinates[number - 1].coordinateY + 30;
				break;
			case 7:
				//case 7 means location 8, this number should be same as location 2
				fbcoordinates[number].coordinateX = fbcoordinates[number - 1].coordinateX - 60 - fbNameLength;
				break;
			case 8:
				// case 8 means location 9
				fbcoordinates[number].coordinateY = fbcoordinates[number - 1].coordinateY - 30;
				break;
			case 10:
				// case 10 means location 11, this number should be fixed at 20.
				fbcoordinates[number].coordinateY = fbcoordinates[number - 1].coordinateY - 20;
				break;
		}
	}
	
	public Coordinate getFBNameCoordinate() {
		
		Coordinate coor = new Coordinate(this.fbcoordinates[10].coordinateX + 12, this.fbcoordinates[10].coordinateY + 15);
		
		return coor;
	}
	
	// test
//	public static void main (String[] args) {
//		
//		//default location of 300, 300
//		FunctionBlock fb = new FunctionBlock();
//		FunctionBlockCoordinates coor = new FunctionBlockCoordinates();
//		
//		coor.CoordinateCalculator(fb);
//		
//		int count = 1;
//		
//		for (Coordinate item : coor.fbcoordinates) {
//			System.out.println("Current Location " + count);
//			count ++; 
//			System.out.println(item.toString());
//		}
//	}
	
}
