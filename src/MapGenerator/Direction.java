package MapGenerator;

public enum Direction {
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
