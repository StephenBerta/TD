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
	public static int currentWave = 0;
	public static int killsToWin = 0;
	public static int wavesToWin = 0;
	public static int level = 1;
	public static int maxLevel = 3;
	public static int winTime = 4000;
	public static int winFrame = 0;
	
	public static int buttonX = 500;
	public static int buttonY = 100;
	public static int buttonXoptions = 200;
	public static int buttonYoptions = 100;
	public Rectangle buttonStart;
	public Rectangle buttonOptions;
	public Rectangle buttonBack;
	public Rectangle buttonEasy;
	public Rectangle buttonMedium;
	public Rectangle buttonHard;
	
	
	public static boolean isFirst = true;
	public static boolean isDebug = false;
	public static boolean isWin = false;
	public static boolean isPaused = false;
	public static boolean startGame = false;
	public static boolean startOptions = false;
	
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
	
	public static void hasWonWave() {
		if(killed == killsToWin) {
			killed = 0;
			currentWave += 1;
			
		}
	}
	public static void hasWon() {
		if(currentWave == wavesToWin) {
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
		Value.getDifficultyVariables();
		for(int i=0;i<tileset_ground.length;i++) {
			tileset_ground[i] = new ImageIcon("res/tileset_ground.png").getImage();
			tileset_ground[i] = createImage(
				new FilteredImageSource(
					tileset_ground[i].getSource(),
					new CropImageFilter(0, 26 * i, 26, 26)
				)
			);
			
		}
		for(int i = 0; i < tileset_air.length; i++) {
			tileset_air[i] = new ImageIcon("res/tileset_air.png").getImage();
			tileset_air[i] = createImage(
				new FilteredImageSource(
					tileset_air[i].getSource(),
					new CropImageFilter(0, 26 * i, 26, 26)
				)
			);
		}
		
		tileset_res[0] = new ImageIcon("res/cell.png").getImage();
		tileset_res[1] = new ImageIcon("res/heart.png").getImage();
		tileset_res[2] = new ImageIcon("res/coin.png").getImage();
		
		tileset_mob[0] = new ImageIcon("res/mob1.png").getImage();
		tileset_mob[1] = new ImageIcon("res/mob2.png").getImage();
		tileset_mob[2] = new ImageIcon("res/mob3.png").getImage();
		
		save.loadSave(new File("save/mission" + level + ".steve"));
		
		for(int i=0;i<mobs.length;i++) {
			mobs[i] = new Mob();
		}
	}
	
	
	public void paintScreens(Graphics g) {
		int mouseButton = Key.clickNumber;
		int clicks = Key.clickCount;	//get number of clicks to relay to second screen
		int clicked = 0; //tell when to click difficulties
		
		if(isFirst) {
			myWidth = getWidth();
			myHeight = getHeight();
			myBorder = 5;
			define();
		}
		
		if(!startGame && !startOptions) {
			buttonStart = new Rectangle(
				(getWidth() - buttonX) / 2,
				(getHeight() - buttonY) / 2,
				buttonX,
				buttonY
			);
			
			buttonOptions = new Rectangle(
					(getWidth() - buttonX) / 2,
					(getHeight() + 2*buttonY) / 2,
					buttonX,
					buttonY
				);
			
			//setbackground
			g.setColor(new Color(50,50,50));
			g.fillRect(0, 0, getWidth(), getHeight());
			
			//screen title above start
			g.setColor(new Color(0,0,0));
			g.setFont(new Font("Times New Roman", Font.BOLD, 35));
			g.drawString(
				Frame.title,
				getWidth() / 4,
				getHeight() / 4
			);
			
			//color of start button
			g.setColor(new Color(100,100,100));
			g.fillRect(
				(getWidth() - buttonX) / 2,
				(getHeight() - buttonY) / 2,
				buttonX,
				buttonY
			);
			//color of options button
			g.setColor(new Color(100,100,100));
			g.fillRect(
				(getWidth() - buttonX) / 2,
				(getHeight() + 2*buttonY) / 2,
				buttonX,
				buttonY
			);
			
			g.setColor(new Color(190,190,190));
			g.setFont(new Font("Times New Roman", Font.BOLD, 35));
			g.drawString(
				"START GAME",
				(getWidth() - (buttonX) / 2) / 2,
				(getHeight() + (buttonY / 4)) / 2);
			g.drawString(
				"OPTIONS",
				(getWidth() - (buttonX) / 3) / 2,
				(getHeight() - buttonOptions.height*2));
			//startbutton
			if(buttonStart.contains(Screen.mse)) {
				g.setColor(new Color(150,150,150));
				g.fillRect(
					(getWidth() - buttonX) / 2,
					(getHeight() - buttonY) / 2,
					buttonX,
					buttonY
				);
				g.setColor(new Color(75,75,75));
				g.setFont(new Font("Times New Roman", Font.BOLD, 35));
				g.drawString(
					"CLICK HERE",
					(getWidth() - buttonX / 2) / 2,
					(getHeight() + (buttonY / 4)) / 2);
				if(mouseButton  == 1) {
					isFirst = false;
					startGame = true;				
				}
			}
			
			//select options
			if(buttonOptions.contains(Screen.mse)) {
				g.setColor(new Color(255,255,255));
				g.fillRect(
					(getWidth() - buttonX) / 2,
					(getHeight() + 2*buttonY) / 2,
					buttonX,
					buttonY
				);
				g.setColor(new Color(0,0,0));
				g.setFont(new Font("Times New Roman", Font.BOLD, 35));
				g.drawString(
						"CLICK HERE",
						(getWidth() - (buttonX) / 3) / 2,
						(getHeight() - buttonOptions.height*2));
				if(mouseButton  == 1) {
					startOptions = true;
					startGame = false;
					
					
				}
			}
		}
		
		//paint options
		if(!startGame && startOptions) {
			
			
			
			startGame = false;
			g.setColor(new Color(50,50,50));
			g.fillRect(0, 0, getWidth(), getHeight());
			
			//easy button
			g.setColor(new Color(150,150,150));
			buttonEasy = new Rectangle(
					(getWidth()/10 + buttonXoptions/10) / 2,
					(getHeight() - buttonYoptions) / 2,
					buttonXoptions,
					buttonYoptions
				);
			//medium button
			buttonMedium = new Rectangle(
					((getWidth()/10 + buttonXoptions/10)/2) + buttonXoptions*2 - buttonXoptions/4,
					(getHeight() - buttonYoptions) / 2,
					buttonXoptions,
					buttonYoptions
				);
			
			//hard button
			buttonHard = new Rectangle(
					((getWidth()/10 + buttonXoptions/10)/2) + buttonXoptions/2 + buttonXoptions*3,
					(getHeight() - buttonYoptions) / 2,
					buttonXoptions,
					buttonYoptions
				);
			//paint easy
			g.setColor(new Color(0,250,0));
			g.fillRect(
					((getWidth()/10 + buttonXoptions/10)/2),
					(getHeight() - buttonYoptions) / 2,
					buttonXoptions,
					buttonYoptions
					);
			//paint medium
			g.setColor(new Color(125,125,0));
			g.fillRect(
					((getWidth()/10 + buttonXoptions/10)/2) + buttonXoptions*2 - buttonXoptions/4,
					(getHeight() - buttonYoptions) / 2,
					buttonXoptions,
					buttonYoptions
					);
			//paint hard
			g.setColor(new Color(150,0,0));
			g.fillRect(
					((getWidth()/10 + buttonXoptions/10)/2) + buttonXoptions/2 + buttonXoptions*3,
					(getHeight() - buttonYoptions) / 2,
					buttonXoptions,
					buttonYoptions
					);
			//easy text
			g.setColor(new Color(0,0,0));
			g.setFont(new Font("Times New Roman", Font.BOLD, 35));
			g.drawString(
					"EASY",
					((getWidth()/10 + buttonXoptions/10) / 2) + buttonXoptions/3,
					(getHeight()/2 + buttonYoptions/10)
					);
			//medium text
			g.setColor(new Color(0,0,0));
			g.setFont(new Font("Times New Roman", Font.BOLD, 35));
			g.drawString(
					"MEDIUM",
					((getWidth()/10 + buttonXoptions/10)/2) + buttonXoptions*2 - buttonXoptions/4 + buttonXoptions/10,
					(getHeight()/2 + buttonYoptions/10)
					);
			//hard text
			g.setColor(new Color(0,0,0));
			g.setFont(new Font("Times New Roman", Font.BOLD, 35));
			g.drawString(
					"DON'T",
					((getWidth()/10 + buttonXoptions/10)/2) + buttonXoptions/2 + buttonXoptions*3 + buttonXoptions/4,
					(getHeight()/2 + buttonYoptions/10)
					);
			//right click
			g.setColor(new Color(0,0,0));
			g.setFont(new Font("Times New Roman", Font.BOLD, 35));
			g.drawString(
				"SCROLL CLICK DIFFICULTY",
				getWidth() / 4,
				getHeight() / 4
			);
			
			
				//set easy
				if(buttonEasy.contains(Screen.mse)) {
					g.setColor(new Color(0,0,0,50));
					g.fillRect(
							((getWidth()/10 + buttonXoptions/10)/2),
							(getHeight() - buttonYoptions) / 2,
							buttonXoptions,
							buttonYoptions
							);
					if(mouseButton == 2) {
						Value.isEasy = true;
						Value.isMedium = false;
						Value.isHard = false;
						startOptions = false;
						startGame = false;
					}
				}
				//set medium
				if(buttonMedium.contains(Screen.mse)) {
					g.setColor(new Color(0,0,0,50));
					g.fillRect(
							((getWidth()/10 + buttonXoptions/10)/2) + buttonXoptions*2 - buttonXoptions/4,
							(getHeight() - buttonYoptions) / 2,
							buttonXoptions,
							buttonYoptions
							);
					if(mouseButton == 2) {
						Value.isEasy = false;
						Value.isMedium = true;
						Value.isHard = false;
						startOptions = false;
						startGame = false;
					}
				}
				//set hard
				if(buttonHard.contains(Screen.mse)) {
					g.setColor(new Color(0,0,0,50));
					g.fillRect(
							((getWidth()/10 + buttonXoptions/10)/2) + buttonXoptions/2 + buttonXoptions*3,
							(getHeight() - buttonYoptions) / 2,
							buttonXoptions,
							buttonYoptions
							);
					if(mouseButton == 2) {
						Value.isEasy = false;
						Value.isMedium = false;
						Value.isHard = true;
						startOptions = false;
						startGame = false;
					}
				}
		}
	}
	
	
	public void paintComponent(Graphics g) {
		
		paintScreens(g);
		
		if(startGame) {
			g.setColor(new Color(75,75,75));				//background color
			g.fillRect(0, 0, getWidth(), getHeight());     //Clear screen.
			g.setColor(new Color(0, 0, 0));					//border color
			for(int i = 0; i < myBorder + 1; i++) {
				g.drawLine(
					room.block[0][room.worldWidth-1].x + room.blockSize + i,
					0,
					room.block[0][room.worldWidth-1].x + room.blockSize + i,
					room.block[room.worldHeight-1][0].y + room.blockSize
				); //right border
				g.drawLine(
					room.block[0][0].x - i,
					0,
					room.block[0][0].x - i,
					room.block[room.worldHeight-1][0].y + room.blockSize + i
				); //left border
				g.drawLine(
					room.block[0][0].x - myBorder + 1,
					room.block[room.worldHeight-1][0].y + room.blockSize + i,
					room.block[0][room.worldWidth-1].x + room.blockSize + myBorder,
					room.block[room.worldHeight-1][0].y + room.blockSize + i
				); //drawing bottom border.
			}
			room.draw(g);   //Drawing the room.
			for(int i = 0; i < mobs.length; i++) {
				if(mobs[i].inGame) {
					mobs[i].draw(g);
				}
			}
			store.draw(g);  //Drawing the store.
			if(health < 1) {
				g.setColor(new Color(240, 20, 20));
				g.fillRect(0, 0,  myWidth, myHeight);
				g.setColor(new Color(255, 255, 255));
				g.setFont(new Font("Courier New", Font.BOLD, 14));
				g.drawString("GAME OVER, GET BACK TO WORK YOU BAFOON!", 10, 10);
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
	
//	public int spawnTimeMob1 = 4000, spawnFrameMob1 = 0;										//green mob spawn time
//	public int spawnTimeMob2 = 2000, spawnFrameMob2 = 0;								//pink mob spawn time
//	public int spawnTimeMob3 = 6000, spawnFrameMob3 = 0;								//yellow mob spawn time
//	public void mobSpawner() {		
//		if(spawnFrameMob1 >= spawnTimeMob1){
//			for(int i = 0; i < mobs.length; i++){
//				if(!mobs[i].inGame){
//					mobs[i].spawnMob(Value.mobGreen);
//					break;
//				}
//			}
//			spawnFrameMob1 = 0;
//		}
//		else {
//			spawnFrameMob1 += 1;
//		}
//		if(spawnFrameMob2 >= spawnTimeMob2){
//			for(int i = 0; i < mobs.length; i++){
//				if(!mobs[i].inGame){
//					mobs[i].spawnMob(Value.mobPink);
//					break;
//				}
//			}
//			spawnFrameMob2 = 0;
//		}
//		else {
//			spawnFrameMob2 += 1;
//		}
//		if(spawnFrameMob3 >= spawnTimeMob3){
//			for(int i = 0; i < mobs.length; i++){
//				if(!mobs[i].inGame){
//					mobs[i].spawnMob(Value.mobYellow);
//					break;
//				}
//			}
//			spawnFrameMob3 = 0;
//		}
//		else {
//			spawnFrameMob3 += 1;
//		}
//	}
	
	public void run() {
		while(true) {
			if(!isPaused) {
				if(!isFirst && health > 0 && !isWin) {
					room.physic(); 
					Wave.mobSpawner();
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
								killed = 0;				//reset number killed on win
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
