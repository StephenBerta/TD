package MapGenerator;

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
		
		int i = 0;
		double distance;
		for(i=0; i < 3; i++){
			int[] coordinate = (Direction.values()[i]).direction();
			coordinate[0] += currentCoord[0];
			coordinate[1] += currentCoord[1];
			distance = 
			if()
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
