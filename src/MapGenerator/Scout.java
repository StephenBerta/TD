package MapGenerator;

import java.util.Arrays;

public class Scout {
	private int[] mapSize = null;
	private int[] startCoord = null;
	private int[] endCoord = null;
	
	private int[] currentCoord = null;
	private int walkPace = 0;
	
	public Scout(int[] mapSize, int[] startCoord, int[] endCoord){
		this.mapSize = mapSize;
		this.startCoord = startCoord;
		this.endCoord = endCoord;
		this.currentCoord = startCoord;
		
		this.walkPace = RandomGenerator.RandomInteger(1, 4);
	}
	
	private int getDirection(){
		int value = (Integer) null;
//		List<direction> = null;
		double[] distance = (double[]) null;
		int i = 0;

		//Calculate the distances of all directions
		for(i=0; i < 3; i++){
			int[] coordinate = (Direction.values()[i]).direction();
			coordinate[0] += currentCoord[0];
			coordinate[1] += currentCoord[1];
			try{
				distance[i] = Math.sqrt(Math.exp((currentCoord[0] - endCoord[0])) + Math.exp((currentCoord[1] - endCoord[1])));
			}
			finally{
				distance[i] = (Double) null;
			}
		}
		
		//Determine shortest route
		int minValue = (int) distance[0];
		for(i = 0; i < distance.length; i++){
			if(distance[i] < minValue){
				minValue = (int) distance[i];
			}
		}
		
		
		
			
			
				
		Direction direction = Direction.values()[RandomGenerator.RandomInteger(0, 3)];
		switch(direction){
			case North:
				
				break;
			case East:
				break;
			case South:
				break;
			case West:
				break;
			default:
				break;
		}
		
		return value;
	}
	
	private void moveDirection(){
		
	}
}
