package MapGenerator;

public class Map {
	//Note: place the killsToWin within the Map initializer method instead of here
	//Screen.killsToWin = loadScanner.nextInt();
	public int xMapSize = 0;
	public int yMapSize = 0;
	public int[] startCoord = null;
	public int[] endCoord = null;
	public int[][] quickArray = null;
	
	public Map(int xMapSize, int yMapSize, int[] startCoord, int[] endCoord){
		this.xMapSize = xMapSize;
		this.yMapSize = yMapSize;
		this.startCoord = startCoord;
		this.endCoord = endCoord;
		this.quickArray = new int[xMapSize][yMapSize];
		
		int index = this.xMapSize;
		while(index > 0){
			for(int x = 0; x < xMapSize; x++) {
				for(int y = 0; y < yMapSize; y++) {
					quickArray[x][y] = 0;
					//Screen.room.block[y][x].groundID = 0;
					if( x == startCoord[0] && y == startCoord[1] ){
						quickArray[x][y] = 1;
						//Screen.room.block[y][x].airID = 1;						
					}
					if(x == endCoord[0] && y == endCoord[1] ){
						quickArray[x][y] = 2;
						//Screen.room.block[y][x].airID = 2;
					}
				}
			}
			index -= 1;
		}
	}
}