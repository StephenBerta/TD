package MapGenerator;

import java.awt.*;

public class Map extends Rectangle{
	//Note: place the killsToWin within the Map initializer method instead of here
	//Screen.killsToWin = loadScanner.nextInt();
	public int[] size = null;
	public int[] startCoord = null;
	public int[] endCoord = null;
	public int[][] mapArray = null;

	public Map(int[] mapSize, int[] startCoord, int[] endCoord){
		this.size = mapSize;
		this.startCoord = startCoord;
		this.endCoord = endCoord;
		this.mapArray = new int[mapSize[0]][mapSize[1]];
		initialize();
	}
	
	private void initialize(){
		for(int i = 0; i <= this.size[0]; i++){
			for(int x = 0; x < this.size[0]; x++) {
				for(int y = 0; y < this.size[1]; y++) {
					mapArray[x][y] = 0;
					mapArray[x][y] = x == startCoord[0] && y == startCoord[1] ? 1 : mapArray[x][y];
					mapArray[x][y] = x == endCoord[0] && y == endCoord[1] ? 2 : mapArray[x][y];
				}
			}
		}
	}
}
