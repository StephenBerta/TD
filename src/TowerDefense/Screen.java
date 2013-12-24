package TowerDefense;
//2x2 pixel TD game so 20 in here is really 10 pixels in photoshop!!
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Screen extends JPanel implements Runnable {				//change to Jpanel to run in ide
	public Thread thread = new Thread(this);
	
	public static Image[] tileset_ground = new Image[100];
	public static Image[] tileset_air = new Image [100];
	public static Image[] tileset_res = new Image [100];
	public static Image[] tileset_mob = new Image [100];
	
	public static int myWidth, myHeight;
	public static int coinage;
	public static int health;
	public static int killed = 0;
	public static int killsToWin = 0;
	public static int level =1;
	public static int maxLevel =3;
	public static int winTime = 4000;
	public static int winFrame = 0;
	
	public static int buttonX = 500;
	public static int buttonY = 100;
	public Rectangle buttonStart;
	
	public static boolean isFirst = true;
	public static boolean isDebug = false;
	public static boolean isWin = false;
	public static boolean isPaused = false;
	public static boolean startGame = false;
	
	public static Point mse = new Point(0,0);
	
	public static Room room;
	public static Save save;
	public static Store store;
	public static int myBorder = 5;								//pixel thickness of border
	
	public static Mob[] mobs = new Mob[100];
	
	public Screen(Frame frame) {
		frame.addMouseListener(new Key());
		frame.addMouseMotionListener(new Key());
		thread.start();
	}
	
	public static void hasWon() {
		if(killed == killsToWin) {
			isWin = true;
			killed = 0;
			coinage = 0;
		}
	}
	
	public void define () {
		room = new Room();
		save = new Save();
		store = new Store();
		
		coinage = Value.startingCoinage;
		health = Value.startingHealth;
		
		for(int i=0;i<tileset_ground.length;i++) {
			tileset_ground[i] = new ImageIcon("res/tileset_ground.png").getImage();
			tileset_ground[i] = createImage(new FilteredImageSource(tileset_ground[i].getSource(), new CropImageFilter(0, 26*i, 26, 26)));
			
		}
		for(int i=0;i<tileset_air.length;i++) {
			tileset_air[i] = new ImageIcon("res/tileset_air.png").getImage();
			tileset_air[i] = createImage(new FilteredImageSource(tileset_air[i].getSource(), new CropImageFilter(0, 26*i, 26, 26)));
			
		}
		
		tileset_res[0] = new ImageIcon("res/cell.png").getImage();
		tileset_res[1] = new ImageIcon("res/heart.png").getImage();
		tileset_res[2] = new ImageIcon("res/coin.png").getImage();
		
		tileset_mob[0] = new ImageIcon("res/mob1.png").getImage();
		tileset_mob[1] = new ImageIcon("res/mob2.png").getImage();
		
		save.loadSave(new File("save/mission" + level + ".steve"));
		
		for(int i=0;i<mobs.length;i++) {
			mobs[i] = new Mob();
		}
	}
	
	public void paintComponent(Graphics g) {
		int mouseButton = Key.clickNumber;
		if(isFirst) {
			myWidth = getWidth();
			myHeight = getHeight();
			myBorder = 5;
			define();
		}
		
		if(!startGame) {
			buttonStart = new Rectangle((getWidth()-buttonX)/2, (getHeight()-buttonY)/2, buttonX, buttonY);
			
			g.setColor(new Color(50,50,50));
			g.fillRect(0, 0, getWidth(), getHeight());
			
			g.setColor(new Color(0,0,0));
			g.setFont(new Font("Times New Roman", Font.BOLD, 35));
			g.drawString(Frame.title, getWidth()/4, getHeight()/4);
			
			g.setColor(new Color(100,100,100));
			g.fillRect((getWidth()-buttonX)/2, (getHeight()-buttonY)/2, buttonX, buttonY);
			
			g.setColor(new Color(190,190,190));
			g.setFont(new Font("Times New Roman", Font.BOLD, 35));
			g.drawString("START GAME", (getWidth() - (buttonX)/2)/2, (getHeight()+(buttonY/4))/2);
			
			if(buttonStart.contains(Screen.mse)) {
				g.setColor(new Color(150,150,150));
				g.fillRect((getWidth()-buttonX)/2, (getHeight()-buttonY)/2, buttonX, buttonY);
				g.setColor(new Color(75,75,75));
				g.setFont(new Font("Times New Roman", Font.BOLD, 35));
				g.drawString("CLICK HERE", (getWidth() - (buttonX)/2)/2, (getHeight()+(buttonY/4))/2);
				if(mouseButton  == 1) {
					isFirst = false;
					startGame = true;
					System.out.println("finally");
				}
			}
		}
		if(startGame) {
			g.setColor(new Color(75,75,75));				//background color
			g.fillRect(0, 0, getWidth(), getHeight());     //Clear screen.
			g.setColor(new Color(0, 0, 0));					//border color
			for(int i=0; i<myBorder + 1; i++) {
				g.drawLine(room.block[0][room.worldWidth-1].x + room.blockSize + i, 0, room.block[0][room.worldWidth-1].x + room.blockSize + i, room.block[room.worldHeight-1][0].y + room.blockSize); //right border
				g.drawLine(room.block[0][0].x - i, 0, room.block[0][0].x - i, room.block[room.worldHeight-1][0].y + room.blockSize + i); //left border
				g.drawLine(room.block[0][0].x - myBorder + 1, room.block[room.worldHeight-1][0].y + room.blockSize + i, room.block[0][room.worldWidth-1].x + room.blockSize + myBorder, room.block[room.worldHeight-1][0].y + room.blockSize + i); //drawing bottom border.
			}
			room.draw(g);   //Drawing the room.
			for(int i=0;i<mobs.length;i++) {
				if(mobs[i].inGame) {
					mobs[i].draw(g);
				}
			}
		
			store.draw(g);  //Drawing the store.
			
			if(health <1) {
				g.setColor(new Color(240, 20, 20));
				g.fillRect(0, 0,  myWidth, myHeight);
				g.setColor(new Color(255, 255, 255));
				g.setFont(new Font("Courier New", Font.BOLD, 14));
				g.drawString("GAME OVER, Sucks To Suck", 10, 10);
			}
			if(isWin) {
				g.setColor(new Color(255,255,255));
				g.fillRect(0,  0, getWidth(), getHeight());
				g.setColor(new Color(0, 0, 0));
				g.setFont(new Font("Courier New", Font.BOLD, 14));
				if(level > maxLevel) {
					g.drawString("Congratulations you beat the game! The window will now close...", 10, 10);
				}
				else {
				g.drawString("You beat the level! Please wait for next level...", 10, 10);
				}
			}
		}
	}
	
	public int spawnTime = 5000, spawnFrame = 0;										//green mob spawn time
	public int spawnTimeMob2 = 2500, spawnFrameMob2 = 0;								//pink mob spawn time
	public void mobSpawner() {
		if(spawnFrame >= spawnTime){
			for(int i=0;i<mobs.length;i++){
				if(!mobs[i].inGame){
					mobs[i].spawnMob(Value.mobGreen);
					break;
				}
			}
			spawnFrame = 0;
		}
		else {
			spawnFrame += 1;
		}
		if(spawnFrameMob2 >= spawnTimeMob2){
			for(int i=0;i<mobs.length;i++){
				if(!mobs[i].inGame){
					mobs[i].spawnMob(Value.mobPink);
					break;
				}
			}
			spawnFrameMob2 = 0;
		}
		else {
			spawnFrameMob2 += 1;
		}
	}
	
	public void run() {
		while(true) {
			if(!isPaused) {
				if(!isFirst && health > 0 && !isWin) {
					room.physic(); 
					mobSpawner();
					for(int i=0;i<mobs.length;i++) {
						if(mobs[i].inGame) {
							mobs[i].physic();
						}
					}
				}
				else {
					if(isWin) {
						if(winFrame >= winTime) {
							if(level == maxLevel) {
								System.exit(0);
							}
							else {
								level +=1;
								define();
								isWin = false;
							}
							winFrame = 0;
						}
						else {
							winFrame += 1;
						}
					}
				}
				repaint();
				try {
					Thread.sleep(1);
				}
				catch(Exception e) {}
			}
			else {
				try{
					Thread.sleep(1);
				}
				catch(Exception e) {}
			}
		}
	}
}
