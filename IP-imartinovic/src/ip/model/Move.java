package ip.model;

public class Move {
	private Tile start;
	private Tile finish;
	private Direction direction;
	
		
		public Move(Tile start, Tile finish, Direction direction) {
			this.start = start;
			this.finish = finish;
			this.direction = direction;
		}
		
		public Tile getStart() {return start;}
		public Tile getFinish() {return finish;}
		public Direction getDirection() {return direction;}
		
}
