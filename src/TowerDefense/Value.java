package TowerDefense;

public class Value {
	public static int groundGrass = 0;			//can place towers here
	public static int groundRock = 1;			//ground mobs walk on
	
	public static int airAir = -1;				//air block
	public static int airCave = 0;
	public static int airTrashCan = 1;
	public static int airTowerLaser = 2;
	public static int airTowerLaserDamage = 100;
	public static int airTowerRadiator = 3;
	public static int airTowerRadiatorDamage = 50;
	public static int airTowerCannon = 4;
	public static int airTowerCannonDamage = 300;
	
	
	
	public static int mobAir = -1;
	public static int mobGreen = 0;
	public static int mobPink = 1;
	public static int mobYellow = 2;
	public static int[] mobHealth = {5200, 2600, 10400};
	public static int[] mobWalkSpeeds = {0,0,0};
    public static int[] killReward = {0,0,0};
	
	
	
	public static int startingCoinage = 100;
	public static int startingHealth = 10;
	
	public static int difficultyLevel = 1;
	
	public static boolean isEasy = true;						//default to easy difficulty
	public static boolean isMedium = false;
	public static boolean isHard = false;
	
	//set difficulty-specific variables
	//0=green
	//1=pink
	//2=yellow
	public static void getDifficultyVariables() {
		if(isEasy && !isMedium && !isHard){
			 mobWalkSpeeds[0] = 30;	
			 mobWalkSpeeds[1] = 20;
			 mobWalkSpeeds[2] = 60;
		     killReward[0] = 5;
		     killReward[1] = 2;
		     killReward[2] = 20;
		     
		}
		
		if(!isEasy && isMedium && !isHard){
			 mobWalkSpeeds[0] = 20;	
			 mobWalkSpeeds[1] = 15;
			 mobWalkSpeeds[2] = 40;
		     killReward[0] = 5;
		     killReward[1] = 2;
		     killReward[2] = 20;
			}
	
		if(!isEasy && !isMedium && isHard){
			 mobWalkSpeeds[0] = 18;	
			 mobWalkSpeeds[1] = 10;
			 mobWalkSpeeds[2] = 25;
		     killReward[0] = 3;
		     killReward[1] = 1;
		     killReward[2] = 5;
			 airTowerLaserDamage = 50;
		     airTowerRadiatorDamage = 25;
		     airTowerCannonDamage = 150;
		}
	}
}
