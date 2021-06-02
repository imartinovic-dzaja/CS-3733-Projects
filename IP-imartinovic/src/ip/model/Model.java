package ip.model;

import java.util.ArrayList;

public class Model {
	private Board board;
	private Tile selectedTile;
	private boolean hasWon;
	private boolean hasLost;
	
	public Model() {
		restartGame();
	}
	
	public Board getBoard() {return board;}
	public Tile getSelectedTile() {return selectedTile;}
	
	public boolean setSelectedTile(Tile t) {
		if (t == null || board.getTiles().contains(t)){
			selectedTile = t;
			return true;
		}
		return false;
		}
	
	public boolean getHasWon() {return hasWon;}
	public boolean getHasLost() {return hasLost;}
	
	public void hasWonOrLost() {
		//if only one Tile remains and it is in the center of the board, you have won
		if (board.getTiles().size() == 1 && board.getTiles().get(0).getIndex() == board.centerIndex) {
			hasWon = true;
		}
		//otherwise if no more moves are available you have lost
		else if(board.getMoves().isEmpty()) {
			hasLost = true;
		}
	}
	
	/**
	 * Return all the moves that are possible with the selected piece
	 * @return
	 */
	public ArrayList<Move> selectedTileAvailableMoves() {
		ArrayList<Move> moves = new ArrayList<Move>();
		if (selectedTile == null) {
			return moves;
		}
		for (Move m : board.getMoves()) {
			if(m.getStart().equals(selectedTile)) {
				moves.add(m);
			}
		}
		return moves;
	}
	
	/*This function is used with conjuction of movement buttons,
	 * it assumes that there exist a move for the given direction
	 * since otherwise the buttons would be inactive
	 * */
	public Move getMoveForSelectedTileGivenDirection(Direction dir) {
		if (selectedTile == null) {return null;}
		for(Move m : this.selectedTileAvailableMoves()) {
			if (m.getDirection() == dir) {
				return m;
			}
		}
		return null; //this piece of code should never execute when a player interacts
	}
	
	public void restartGame() {
		selectedTile = null;
		hasWon = false;
		hasLost = false;
		board = new Board(3);
		
		board.addTile(new Tile(1));
		board.addTile(new Tile(4));
		board.addTile(new Tile(3));
		board.addTile(new Tile(7));
		board.addTile(new Tile(2));
		board.addTile(new Tile(5));
		board.addTile(new Tile(9));
		board.addTile(new Tile(8));
		board.addTile(new Tile(6));
		
		board.updateAvailableMoves();
	}
}
