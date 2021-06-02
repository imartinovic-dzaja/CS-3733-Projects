package ip.model;


public class Tile {
	private int index;
	private int value;
	private int row;
	private int column;
	
		public Tile(int value) {
			this.index = -1; 	//will be assigned later once the board is set
			this.setRowAndColumn(0); //will set the row and column to -1
			this.value = value;
		}
	
		public int getIndex() {return this.index;}
		public int getValue() {return this.value;}
		public int getRow() {return this.row;}
		public int getColumn() {return this.column;}
		
		public void setValue(int value) {this.value = value;}
		/*Assume the index will always be on the board*/
		public void setIndex(int index, int boardsize) {
			this.index = index;
			this.setRowAndColumn(boardsize); 
		}
		/*
		 * Sets appropriate row and column based on boardsize and index
		 * Tiles are indexed as following
		 *  0 					1 			... boardsize - 1
		 *  boardsize + 1 , boardsize +2, 	... 2*boardsize -1 
		 *  .					.						.
		 *  .								.			.
		 *  .					.			...boardsize^2 - 1
		 */
		private void setRowAndColumn(int boardsize) {
			if (this.index == -1) { //tile is not on the board
				this.row = -1;
				this.column = -1;
			}
			else {
				this.row = this.index / boardsize;
				this.column = this.index % boardsize;
			}
		}
		
		public boolean isAtCoordinate (Coordinate c) {
			return (this.column == c.x && this.row == c.y);
		}

}
