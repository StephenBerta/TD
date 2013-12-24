package TowerDefense;
import java.awt.*;

import javax.swing.*;

public class Block extends Rectangle {
	public Rectangle towerRange;
	public int towerRangeNumeric = 150;
	public int groundID;
	public int airID;
	public int loseTime = 100;
	public int loseFrame = 0;
	public int shotMob = -1;
	public int shotMultiple = 0;
	public boolean shooting = false;
	
	public Block(int x, int y, int width, int height, int groundID, int airID) {
		setBounds(x, y, width, height);
		towerRange = new Rectangle(x - (towerRangeNumeric/2), y - (towerRangeNumeric/2), width + (towerRangeNumeric), height + (towerRangeNumeric));
		this.groundID = groundID;
		this.airID = airID;
	}
	
	public void draw(Graphics g) {
		g.drawImage(Screen.tileset_ground[groundID], x, y, width, height, null);
		if(airID != Value.airAir) {
			g.drawImage(Screen.tileset_air[airID], x, y, width, height, null);
		}
	}
	public void physic() {
		if(shotMob != -1 && towerRange.intersects(Screen.mobs[shotMob])) {
			shooting = true;
		} else {
			shooting = false;
		}
			if(!shooting) {
				if(airID == Value.airTowerLaser || airID == Value.airTowerRadiator || airID == Value.airTowerCannon) {	//for more towers just say || value.twoername
					for(int i=0;i<Screen.mobs.length;i++) {
						if(Screen.mobs[i].inGame) {
							if(towerRange.intersects(Screen.mobs[i])) {
								shooting = true;
								shotMob = i;
							}
						}
					}
				}
			}
			if(shooting) {
				if(Screen.mobs[shotMob].isDead()) {
					shooting = false;
					shotMob = -1;
					Screen.hasWon();
				}
			}
		}
	
	public void getMoney(int mobID) {
		Screen.coinage += Value.killReward[mobID];
	}
	
	public void fight(Graphics g) {
		if(Screen.isDebug) {
		if(airID == Value.airTowerLaser) {
			g.setColor(new Color(255,255,255,75));
			g.drawRect(towerRange.x, towerRange.y, towerRange.width, towerRange.height);
			g.setColor(new Color(190,190,190,50));
			g.fillRect(towerRange.x, towerRange.y, towerRange.width, towerRange.height);
			}
		}
		if(shooting) {
			if(airID == Value.airTowerLaser) {
				g.setColor(new Color(255,255,0));
				g.drawLine(x + (width/2), y + (height/2), Screen.mobs[shotMob].x + (Screen.mobs[shotMob].width/2) , Screen.mobs[shotMob].y + (Screen.mobs[shotMob].height/2));
				if(loseFrame >= loseTime) {
					Screen.mobs[shotMob].loseHealth(Value.airTowerLaserDamage);
					loseFrame = 0;
				}
				else {
					loseFrame += 1;
				}
			}
			if(airID == Value.airTowerRadiator) {
				for(int i=0;i<Screen.mobs.length;i++) {
					if(towerRange.intersects(Screen.mobs[i]) && Screen.mobs[i].inGame) {
						g.setColor(new Color(0,38,255));
						g.drawLine(x + (width/2), y + (height/2), Screen.mobs[i].x + (Screen.mobs[i].width/2) , Screen.mobs[i].y + (Screen.mobs[i].height/2));
						if(loseFrame >= loseTime) {
							Screen.mobs[i].loseHealth(Value.airTowerRadiatorDamage);
							loseFrame = 0;
						}
						else {
							loseFrame += 1;
						}
					}
				}
			}
			if(airID == Value.airTowerCannon) {
				g.setColor(new Color(250,50,0));
				for(int i=0;i<16;i++) {
				g.drawLine(x + (width/2) - i/4, y + (height/2), Screen.mobs[shotMob].x + (Screen.mobs[shotMob].width/2)  + i , Screen.mobs[shotMob].y + (Screen.mobs[shotMob].height/2));
				}
				if(loseFrame >= loseTime) {
					Screen.mobs[shotMob].loseHealth(Value.airTowerCannonDamage);
					loseFrame = 0;
				}
				else {
					loseFrame += 1;						
					}
				}
			}
		}
	
}


//if(shooting) {
//	for(int i=0;i<Screen.mobs.length;i++) {
//		if(towerRange.intersects(Screen.mobs[i])) {
//		g.setColor(new Color(255,255,0));
//		g.drawLine(x + (width/2), y + (height/2), Screen.mobs[i].x + (Screen.mobs[i].width/2) , Screen.mobs[i].y + (Screen.mobs[i].height/2));
//		}
//	}
//}