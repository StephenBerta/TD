package TowerDefense;
import java.awt.*;

public class Room {
	
	public int worldWidth = 16;
	public int worldHeight = 12;
	public int blockSize = 52;
	public Block[][] block;
			
	public Room() {
		define();
	}
	
	//Define the border and sub-border of the map
	public void define() {
		//Instance of Rectangle, defining height and width
		block = new Block[worldHeight][worldWidth];
		//Provide a block for each known cell
		for(int y = 0; y < block.length; y++) {
			for(int x = 0; x < block[0].length; x++) {
				block[y][x] = new Block(					
					(Screen.myWidth / 2) - ((worldWidth * blockSize) / 2) + (x * blockSize),
					y * blockSize,
					blockSize,
					blockSize,
					Value.groundGrass,
					Value.airAir
				);
			}
		}
	}
	
	public void physic() {
		for(int y = 0; y < block.length; y++) {
			for(int x = 0; x < block[0].length; x++) {
				block[y][x].physic();
			}
		}
	}
	
	public void draw(Graphics g) {
		for(int y = 0; y < block.length; y++) {
			for(int x = 0; x < block[0].length; x++) {
				block[y][x].draw(g);
			}
		}
		for(int y = 0; y < block.length; y++) {
			for(int x = 0; x < block[0].length; x++) {
				block[y][x].fight(g);
			}
		}
	}
}
