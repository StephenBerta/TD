<<<<<<< HEAD
package MapGenerator;

import java.util.Random;

public class RandomGenerator {
	public static Integer RandomInteger(Integer minimum, Integer maximum){
		Integer value = null;
		Random random = new Random();
		value = random.nextInt((maximum - minimum) + 1) + minimum;
		return value;
	}
	
	public static Integer[] RandomInteger(Integer minimum, Integer maximum, Integer length){
		Integer[] value = new Integer[length];
		for(Integer i=0; i < length; i++) {
			value[i] = RandomInteger(minimum, maximum);
		}
		return value;
	}
}
=======
package MapGenerator;

import java.util.Random;

public class RandomGenerator {
	public static Integer RandomInteger(Integer minimum, Integer maximum){
		Integer value = null;
		Random random = new Random();
		value = random.nextInt((maximum - minimum) + 1) + minimum;
		return value;
	}
	
	public static Integer[] RandomInteger(Integer minimum, Integer maximum, Integer length){
		Integer[] value = new Integer[length];
		for(Integer i=0; i < length; i++) {
			value[i] = RandomInteger(minimum, maximum);
		}
		return value;
	}
}
>>>>>>> ede9bdbce585517955c943c818753c628476841c
