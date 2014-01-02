package TowerDefense;

import java.util.Random;

public class Wave {
	public static int spawnTimeMob1 = 2200, spawnFrameMob1 = 0;								//green mob spawn time
	public static int spawnTimeMob2 = 4500, spawnFrameMob2 = 0;								//pink mob spawn time
	public static int spawnTimeMob3 = 6150, spawnFrameMob3 = 0;								//yellow mob spawn time
	public static int mobSpawnCount1 = 0, mobSpawnCount2 = 0, mobSpawnCount3 = 0;
	
	
	public static void mobSpawner() {
//		int mobSpawnGoal1;
//		int mobSpawnGoal2;
//		int mobSpawnGoal3;
//		mobSpawnGoal1 = 1;
		if(spawnFrameMob1 >= spawnTimeMob1){				// && mobSpawnCount1 < mobSpawnGoal1
			for(int i = 0; i < Screen.mobs.length; i++){
				if(!Screen.mobs[i].inGame){
					Screen.mobs[i].spawnMob(Value.mobGreen);
					mobSpawnCount1 += 1;
					break;
				}
			}
			spawnFrameMob1 = 0;
		}
		else {
			spawnFrameMob1 += 1;
//			if(mobSpawnCount1 >= mobSpawnGoal1){
//				Screen.hasWonWave();
//				mobSpawnGoal1 = Screen.killsToWin + Screen.currentWave;
//				System.out.println(mobSpawnGoal1);
//			}
		}
		if(spawnFrameMob2 >= spawnTimeMob2){
			for(int i = 0; i < Screen.mobs.length; i++){
				if(!Screen.mobs[i].inGame){
					Screen.mobs[i].spawnMob(Value.mobPink);
					mobSpawnCount2 += 1;
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
					mobSpawnCount3 += 1;
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
