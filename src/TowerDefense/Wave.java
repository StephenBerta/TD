package TowerDefense;

public class Wave {
	public int spawnTimeMob1 = 4000, spawnFrameMob1 = 0;								//green mob spawn time
	public int spawnTimeMob2 = 2000, spawnFrameMob2 = 0;								//pink mob spawn time
	public int spawnTimeMob3 = 6000, spawnFrameMob3 = 0;								//yellow mob spawn time
	
	public void mobSpawner() {		
		if(spawnFrameMob1 >= spawnTimeMob1){
			for(int i = 0; i < Screen.mobs.length; i++){
				if(!Screen.mobs[i].inGame){
					Screen.mobs[i].spawnMob(Value.mobGreen);
					break;
				}
			}
			spawnFrameMob1 = 0;
		}
		else {
			spawnFrameMob1 += 1;
		}
		if(spawnFrameMob2 >= spawnTimeMob2){
			for(int i = 0; i < Screen.mobs.length; i++){
				if(!Screen.mobs[i].inGame){
					Screen.mobs[i].spawnMob(Value.mobPink);
					break;
				}
			}
			spawnFrameMob2 = 0;
		}
		else {
			spawnFrameMob2 += 1;
		}
		if(spawnFrameMob3 >= spawnTimeMob3){
			for(int i = 0; i < Screen.mobs.length; i++){
				if(!Screen.mobs[i].inGame){
					Screen.mobs[i].spawnMob(Value.mobYellow);
					break;
				}
			}
			spawnFrameMob3 = 0;
		}
		else {
			spawnFrameMob3 += 1;
		}

	}
}
