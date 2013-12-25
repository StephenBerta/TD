package TowerDefense;

import java.awt.*;

public class Mob extends Rectangle {
	public int xC, yC;
	public int health;
//	public int greenHP = 5200;			//multiples of 52 to show correctly 
	public int healthSpace = 3, healthHeight = 6;
	public int mobSize = 52;			//same as block size
	public int mobWalk = 0;
	public int upward = 0, downward =1, right =2, left =3;
	public int direction = right;
	public int mobID = Value.mobGreen;
	public boolean inGame = false;
	public boolean hasUpward = false;
	public boolean hasDownward = false;
	public boolean hasRight = false;
	public boolean hasLeft = false;
	public int walkFrame = 0, walkSpeed = 40;
	
	public Mob() {}
	
	public void spawnMob(int mobID) {
		for(int y = 0; y < Screen.room.block.length; y++) {
			if(Screen.room.block[y][0].groundID == Value.groundRock) {
				setBounds(
					Screen.room.block[y][0].x,
					Screen.room.block[y][0].y,
					mobSize,
					mobSize
				); 
				xC = 0;
				yC = y;
			}
		}
		this.mobID = mobID;
		this.health = Value.mobHealth[mobID];
		inGame = true;
	}
	
	public void deleteMob() {
		inGame = false;
		direction = right;
		mobWalk = 0;
	}
	
	public void loseCastleHealth() {
		Screen.health -= 1;
	}
	
	public void physic() {
		for(int i=0;i<Value.mobWalkSpeeds.length;i++) {
			if(i == mobID) {
				walkSpeed = Value.mobWalkSpeeds[i];
				if(mobID == Value.mobWalkSpeeds[0]) {
					walkSpeed = walkSpeed - Screen.killed*2;
				} 
				if(mobID == Value.mobWalkSpeeds[1]) {
					walkSpeed = walkSpeed - Screen.killed/2;
				}
			}
		}
		
		if(walkFrame >= walkSpeed) {
			if(direction == right) {
				x += 1;
			}
			else if (direction == upward) {
				y -= 1;
			}
			else if(direction == downward ) {
				y += 1;
			}
			else if(direction == left ) {
				x -= 1;
			}
			mobWalk += 1;
			if(mobWalk == Screen.room.blockSize) {
				if(direction == right) {
					xC += 1;
					hasRight = true;
				}
				else if (direction == upward) {
					yC -= 1;
					hasUpward = true;
				}
				else if(direction == downward ) {
					yC += 1;
					hasDownward = true;
				}
				else if(direction == downward ) {
					yC += 1;
					hasDownward = true;
				}
				else if(direction == left) {
					xC -= 1;
					hasLeft = true;
				}
				if(!hasUpward){
					try{
						if(Screen.room.block[yC+1][xC].groundID == Value.groundRock) {
							direction = downward;
						}
					}
					catch(Exception e) {}
				}
				if(!hasDownward) {
					try{
						if(Screen.room.block[yC-1][xC].groundID == Value.groundRock) {
							direction = upward;
					   }
					}
					catch(Exception e) {}
				}
				if(!hasLeft) {
					try{
						if(Screen.room.block[yC][xC+1].groundID == Value.groundRock) {
							direction = right;
						}
					}
					catch(Exception e) {}
				}
				if(!hasRight) {
					try{
						if(Screen.room.block[yC][xC-1].groundID == Value.groundRock) {
							direction = left;
						}				
					}
					catch(Exception e) {}
				}
				if(Screen.room.block[yC][xC].airID == Value.airCave) {
					deleteMob();
					loseCastleHealth();
				}
				hasUpward = false;
				hasDownward = false;
				hasRight = false;
				hasLeft = false;
				mobWalk=0;
			}
			walkFrame = 0;
		}
		else {
			walkFrame += 1;
		}
	}
	
	public void loseHealth(int amo) {
		health -= amo;
		checkDeath();
	}
	
	public void checkDeath() {
		if(health <= 0) {
			deleteMob();
			Screen.room.block[0][0].getMoney(mobID);
			Screen.killed += 1;
			Screen.hasWon();
			if(Screen.isDebug) {
			System.out.println(Screen.killed);
			}
		}
	}
	
	public boolean isDead() {
		if(inGame) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public void draw(Graphics g) {
		g.drawImage(Screen.tileset_mob[mobID], x ,y, width, height, null);
		if(mobID == Value.mobGreen) {
			g.setColor(new Color(180, 50, 50));
			g.fillRect(x , y - (healthSpace + healthHeight), width, healthHeight);
			g.setColor(new Color(50, 180, 50));
			g.fillRect(x , y - (healthSpace + healthHeight), health / 100, healthHeight);
			g.setColor(new Color(0, 0, 0));
			g.drawRect(x, y - (healthSpace + healthHeight), health / 100, healthHeight);
			g.setColor(new Color(180, 0, 0));
			g.drawString(Integer.toString(health/26), x, y-10);
		}
		
		if(mobID == Value.mobPink) {
			g.setColor(new Color(180, 50, 50));
			g.fillRect(x , y - (healthSpace + healthHeight), width, healthHeight);
			g.setColor(new Color(50, 180, 50));
			g.fillRect(x , y - (healthSpace + healthHeight), health / 50, healthHeight);
			g.setColor(new Color(0, 0, 0));
			g.drawRect(x, y - (healthSpace + healthHeight), health / 50, healthHeight);
			g.setColor(new Color(180, 0, 0));
			g.drawString(Integer.toString(health/26), x, y-10);
		}
		
		if(mobID == Value.mobYellow) {
			g.setColor(new Color(180, 50, 50));
			g.fillRect(x , y - (healthSpace + healthHeight), width, healthHeight);
			g.setColor(new Color(50, 180, 50));
			g.fillRect(x , y - (healthSpace + healthHeight), health / 200, healthHeight);
			g.setColor(new Color(0, 0, 0));
			g.drawRect(x, y - (healthSpace + healthHeight), health / 200, healthHeight);
			g.setColor(new Color(180, 0, 0));
			g.drawString(Integer.toString(health/26), x, y-10);
		}
	}
}
