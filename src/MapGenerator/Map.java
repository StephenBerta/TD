package MapGenerator;

public class Map {
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
		int index = this.size[0];
		while(index > 0){
			for(int x = 0; x < size[0]; x++) {
				for(int y = 0; y < size[1]; y++) {
					mapArray[x][y] = 0;
					if( x == startCoord[0] && y == startCoord[1] ){
						mapArray[x][y] = 1;
					}
					if(x == endCoord[0] && y == endCoord[1] ){
						mapArray[x][y] = 2;
					}
				}
			}
			index -= 1;
		}
		
		
	}
}