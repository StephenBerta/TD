package MapGenerator;

import java.util.*;

public class Scout {
	private int[][] mapArray = null;
	private int[] startCoord = null;
	private int[] endCoord = null;
	private int[] currentCoord = null;
	private int[] headingCoord = null;
	private int walkPace = 0;
	
	public Scout(int[][] mapArray, int[] startCoord, int[] endCoord){
		this.mapArray = mapArray;
		this.startCoord = startCoord;
		this.endCoord = endCoord;
		this.currentCoord = startCoord;
		this.headingCoord = new int[]{0, 0};
		this.walkPace = RandomGenerator.RandomInteger(1, 4);
	}
	
	private Direction getDirection(){
		List<Direction> directionList = new ArrayList<Direction>();
		double shortestDistance = 0;

		//Calculate the distances from all possible directions
		for(int i = 0; i < Direction.Orientation.values().length; i++){
			int[] relativeCoordinate = (Direction.Orientation.values()[i]).direction();
//			this.currentCoord[0] += relativeCoordinate[0];
//			this.currentCoord[1] += relativeCoordinate[1];
			double distance = Math.sqrt(Math.pow((this.endCoord[0] - relativeCoordinate[0]), 2) + Math.pow((this.endCoord[1] - relativeCoordinate[1]), 2));
			//Fix in future, 0 could be the distance
			if(distance == 0){
				shortestDistance = distance;
			}
			try{
				if(distance < shortestDistance){
					shortestDistance = distance;
				}
				directionList.add(new Direction(
					Direction.Orientation.values()[i],
					distance,
					relativeCoordinate
				));
			}
			catch(Exception e){
				System.out.println("A direction class could not be generated.");
			}
		}
		
		//Determine shortest route(s)
		Iterator<Direction> iterator = directionList.iterator();
		while(iterator.hasNext()){
			if(iterator.next().distance != shortestDistance){
				iterator.remove();
			}
		}
		
		//Of remaining directions, choose one
		Direction value = directionList.get(RandomGenerator.RandomInteger(0, directionList.size() - 1));
//		directionList.clear();
		return value;
	}
	
	public void moveDirection(){
		int[] headingCoord = getDirection().coordinate;
		System.out.println("Heading coordinate is:  " + headingCoord[0] + ":" + headingCoord[1]);
		for(int i = 0; i < headingCoord.length; i++){
			this.headingCoord[i] = headingCoord[i];
		}
		this.currentCoord[0] += this.headingCoord[0];
		this.currentCoord[1] += this.headingCoord[1];
		this.mapArray[headingCoord[0]][headingCoord[1]] = 1;
	}
}
