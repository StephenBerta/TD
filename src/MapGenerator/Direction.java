package MapGenerator;

public class Direction{
	public Orientation orientation;
	public double distance;
	public int[] coordinate;
	
	public Direction(Orientation orientation, double distance, int[] coordinate){
		this.orientation = orientation;
		this.distance = distance;
		this.coordinate = coordinate;
	}
	
	public static enum Orientation {
		North{
			@Override
			public int[] direction() {
				int[] direction = new int[]{0, -1};
				return direction;
			}
		},
		East{
			@Override
			public int[] direction() {
				int[] direction = new int[]{1, 0};
				return direction;
			}
		},
		South{
			@Override
			public int[] direction() {
				int[] direction = new int[]{0, 1};
				return direction;
			}
		},
		West{
			@Override
			public int[] direction() {
				int[] direction = new int[]{-1, 0};
				return direction;
			}
		};
		
		public abstract int[] direction();
	}	
}