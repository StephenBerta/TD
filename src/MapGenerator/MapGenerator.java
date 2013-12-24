package MapGenerator;

import java.io.*;

public class MapGenerator {
	public static void main(String[] args) {
		Map map = new Map(16, 16, new int[]{0, 0}, new int[]{15, 15});
		
		try{
			File file = new File("save\\Level 1.txt");
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
			
			for(int x = 0; x < map.xMapSize; x++) {
				for(int y = 0; y < map.yMapSize; y++) {
					Integer test = map.quickArray[x][y];
					output.write(Integer.toString(test.intValue()));
					if((y + 1) != map.yMapSize){
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
