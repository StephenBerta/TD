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

		//Calculate the distances from all possible directions
		for(int i = 0; i < Direction.Orientation.values().length; i++){
			int[] relativeCoordinate = (Direction.Orientation.values()[i]).direction();
//			this.currentCoord[0] += relativeCoordinate[0];
//			this.currentCoord[1] += relativeCoordinate[1];
			double distance = Math.sqrt(Math.pow((this.endCoord[0] - relativeCoordinate[0]), 2) + Math.pow((this.endCoord[1] - relativeCoordinate[1]), 2));
			try{
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
		double shortestDistance = directionList.get(0).distance;
		for(int i = 0; i < directionList.size(); i++){
			if(directionList.get(i).distance < shortestDistance){
				shortestDistance = directionList.get(i).distance;
			}
		}
				
		Iterator<Direction> iterator = directionList.iterator();
		while(iterator.hasNext()){
			if(iterator.next().distance != shortestDistance){
				iterator.remove();
			}
		}
		
		//Of remaining directions, choose one
		Direction value = directionList.get(RandomGenerator.RandomInteger(0, directionList.size() - 1));
		return value;
	}
	
	public void moveDirection(){
		int[] headingCoord = getDirection().coordinate;
		for(int i = 0; i < headingCoord.length; i++){
			this.headingCoord[i] = headingCoord[i];
		}
		for(int i = 0; i < this.currentCoord.length; i++){
			this.currentCoord[i] += headingCoord[i];
		}
		this.mapArray[this.currentCoord[0]][this.currentCoord[1]] = 1;
	}
}
