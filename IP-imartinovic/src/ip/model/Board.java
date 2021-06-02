package ip.model;

import java.util.ArrayList;

public class Board{
	public final int BOARDSIZE; 	
	public final int centerIndex;
	private ArrayList<Tile> tiles;
	private ArrayList<Move> availableMoves;
	
		public Board(int size) {
		BOARDSIZE = size; 
		centerIndex = (BOARDSIZE*BOARDSIZE - 1)/2;
		tiles = new ArrayList<Tile>();
		availableMoves = new ArrayList<Move>();
		} 
	
		public ArrayList<Tile> getTiles() {return tiles;}
		public ArrayList<Move> getMoves() {return availableMoves;}
		
		public boolean addTile(Tile t) {
			if (tiles.size() >= 9) {
				return false;
			}
			t.setIndex(tiles.size(), this.BOARDSIZE); 
			tiles.add(t);
			return true;
		}
		
		public boolean removeTile(Tile t) {return tiles.remove(t);}
		
		public void updateAvailableMoves() {
			availableMoves = new ArrayList<Move>();
			for (Tile t1 : tiles) {
				for (Tile t2: tiles) {
					Move move = new Move (t1, t2, findDirection(t1,t2));
					if (isValidMove(move)) {
						availableMoves.add(move);
					}
				}
			}			
		}
		
		/*Figure out which direction from start to finish tile*/
		public Direction findDirection(Tile start, Tile finish) {
			//the direction of the moves can be found using the difference of start and finish indices
			int startColumn = start.getColumn();
			int finishColumn = finish.getColumn();
			int indexDifference = finish.getIndex() - start.getIndex();
			if (indexDifference == 1 && finishColumn != 0) {return Direction.RIGHT;}
			else if (indexDifference == -1 && startColumn != 0) {return Direction.LEFT;}
			else if (indexDifference == -BOARDSIZE) {return Direction.UP;}
			else if (indexDifference == BOARDSIZE) {return Direction.DOWN;}
			else {return Direction.ILLEGAL;}
		}
		
		public boolean isValidMove(Move move) {
			switch(move.getDirection()) {
				case RIGHT:
					return true; 		//RIGHT is addition and we may always add
				case LEFT:
					return (move.getFinish().getValue() > move.getStart().getValue()); //LEFT is subtraction and we may only subtract if tile to the left is greater than tile to the left
				case UP:
					return true; 		//UP is multiplication and we may always multiply
				case DOWN:
					return ((move.getFinish().getValue() % move.getStart().getValue()) == 0); //DOWN is division and we may only divide if top tile divides into bottom tile
				default:
					return false;		//in the usual case the move is illegal hence invalid
			}
		}
		
		/**
		 * Perform a move under the assumption that the move is valid
		 * @param move
		 */
		public boolean performMove(Move move) {
			int startValue = move.getStart().getValue();
			int finishValue = move.getFinish().getValue();
			
			if (move.getDirection().equals(Direction.UP)) {
				move.getFinish().setValue(finishValue*startValue);
			} else if (move.getDirection().equals(Direction.DOWN)) {
				move.getFinish().setValue(finishValue/startValue);
			} else if (move.getDirection().equals(Direction.LEFT)) {
				move.getFinish().setValue(finishValue-startValue);
			} else if (move.getDirection().equals(Direction.RIGHT)) {
				move.getFinish().setValue(finishValue+startValue);
			} else {
				return false;		//if the move is illegal, do nothing
			}
			//We remove the starting tile from the board
			this.removeTile(move.getStart());
			return true;
		}
		
}
