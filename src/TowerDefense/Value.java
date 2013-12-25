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
	public static int[] mobWalkSpeeds = {30, 20, 60};
	
	public static int[] killReward = {5, 2, 20};
	
	public static int startingCoinage = 100;
	public static int startingHealth = 10;
	
	public static int difficultyLevel = 1;
	
	public static boolean isEasy = true;						//default to easy difficulty
	public static boolean isMedium = false;
	public static boolean isHard = false;
}
