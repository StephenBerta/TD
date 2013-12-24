package MapGenerator;

import java.io.*;

public class MapGenerator {
	public static void main(String[] args) {
		Map map = new Map(new int[]{16, 16}, new int[]{0, 0}, new int[]{15, 15});
		Scout scout = new Scout(map.mapArray, map.startCoord, map.endCoord);
		for(int i = 0; i < 28; i++){
			scout.moveDirection();
		}
		
		try{
			File file = new File("save\\Level 1.txt");
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
			for(int x = 0; x < map.size[0]; x++) {
				for(int y = 0; y < map.size[1]; y++) {
					Integer test = map.mapArray[x][y];
					output.write(Integer.toString(test.intValue()));
					if((y + 1) != map.size[1]){
						output.write(", ");
					}
				}
				output.newLine();
			}
			output.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}
}
