package test.entity;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import ip.model.Board;
import ip.model.Direction;
import ip.model.Move;
import ip.model.Tile;

public class TestBoard {
	
	
	@Test
	void BoardFunctionalityTest() {
	Tile tile0 = new Tile(1);
	Tile tile1 = new Tile(4);
	Tile tile2 = new Tile(3);
	Tile tile3 = new Tile(7);
	Tile tile4 = new Tile(2);
	Tile tile5 = new Tile(5);
	Tile tile6 = new Tile(9);
	Tile tile7 = new Tile(8); 
	Tile tile8 = new Tile(6);
	Tile tile9 = new Tile(10); //to test if addTile will return false since board is full
	
	Board board = new Board(3);
	assertEquals(3, board.BOARDSIZE);
	assertEquals(4, board.centerIndex);
	
	assertTrue(board.addTile(tile0));
	assertTrue(board.addTile(tile1));
	assertTrue(board.addTile(tile2));
	assertTrue(board.addTile(tile3));
	assertTrue(board.addTile(tile4));
	assertTrue(board.addTile(tile5));
	assertTrue(board.addTile(tile6));
	assertTrue(board.addTile(tile7));
	assertTrue(board.addTile(tile8));
	assertFalse(board.addTile(tile9));

	
	/*Setup the following 3x3 board 
	 * 1		4		3
	 * 7		2		5
	 * 9		8		6
	 * */
	/*First lets test findDirection*/
	Move move1 = new Move(tile4, tile3, board.findDirection(tile4, tile3));	//should be left, valid
	Move move2 = new Move(tile4, tile5, board.findDirection(tile4, tile5));	//should be right, valid
	Move move3 = new Move(tile4, tile1, board.findDirection(tile4, tile1));	//should be up, valid
	Move move4 = new Move(tile4, tile7, board.findDirection(tile4, tile7));	//should be down, valid
	
	Move move5 = new Move(tile4, tile0, board.findDirection(tile4, tile0));	//should be illegal, invalid
	Move move6 = new Move(tile4, tile2, board.findDirection(tile4, tile2));	//should be illegal, invalid 
	Move move7 = new Move(tile4, tile6, board.findDirection(tile4, tile6));	//should be illegal. invalid
	Move move8 = new Move(tile4, tile8, board.findDirection(tile4, tile8));	//should be illegal, invalid
	
	Move move9 = new Move(tile2, tile3, board.findDirection(tile2, tile3));	//should be illegal, invalid
	Move move10 = new Move(tile6, tile5, board.findDirection(tile6, tile5));	//should be illegal, invalid
	
	Move move11 = new Move(tile0, tile2, board.findDirection(tile0, tile2)); 	//should be illegal, invalid
	Move move12 = new Move(tile2, tile8, board.findDirection(tile2, tile4)); 	//should be illegal, invalid
	
	Move move13 = new Move(tile2, tile5, board.findDirection(tile2, tile5));	//should be down, invalid
	Move move14 = new Move(tile5, tile4, board.findDirection(tile5, tile4));	//should be left, invalid
	

	assertEquals(Direction.LEFT, move1.getDirection());
	assertEquals(Direction.RIGHT, move2.getDirection());
	assertEquals(Direction.UP, move3.getDirection());
	assertEquals(Direction.DOWN, move4.getDirection());
	
	assertEquals(Direction.ILLEGAL, move5.getDirection());
	assertEquals(Direction.ILLEGAL, move6.getDirection());
	assertEquals(Direction.ILLEGAL, move7.getDirection());
	assertEquals(Direction.ILLEGAL, move8.getDirection());
	
	assertEquals(Direction.ILLEGAL, move9.getDirection());
	assertEquals(Direction.ILLEGAL, move10.getDirection());

	assertEquals(Direction.ILLEGAL, move11.getDirection());
	assertEquals(Direction.ILLEGAL, move12.getDirection());
	
	assertEquals(Direction.DOWN, move13.getDirection());
	assertEquals(Direction.LEFT, move14.getDirection());

	/*Next let's test if the above moves are valid*/
	assertTrue(board.isValidMove(move1));
	assertTrue(board.isValidMove(move2));
	assertTrue(board.isValidMove(move3));
	assertTrue(board.isValidMove(move4));
	
	assertFalse(board.isValidMove(move5));
	assertFalse(board.isValidMove(move6));
	assertFalse(board.isValidMove(move7));
	assertFalse(board.isValidMove(move8));
	
	assertFalse(board.isValidMove(move9));
	assertFalse(board.isValidMove(move10));
	
	assertFalse(board.isValidMove(move11));
	assertFalse(board.isValidMove(move12));
	
	assertFalse(board.isValidMove(move13));
	assertFalse(board.isValidMove(move14));
	
	/*Test removing tiles*/
	assertTrue(board.removeTile(tile0));
	assertFalse(board.removeTile(tile9));
	
	/*Board should now look like
	 * 			4		3
	 * 7		2		5
	 * 9		8		6
	 * */
	
	/*Let us attempt making some moves*/
	/*Note: performMove does only the arithmetic, it assumes 
	 * the move was valid
	 * */
	Move div = new Move(tile4, tile7, Direction.DOWN);
	board.performMove(div);
	assertEquals(4, tile7.getValue());

	Move add = new Move(tile7, tile8, Direction.RIGHT);
	board.performMove(add);
	assertEquals(10, tile8.getValue());
	
	Move sub = new Move(tile2, tile1, Direction.LEFT);
	board.performMove(sub);
	assertEquals(1, tile1.getValue());
	
	Move mul = new Move(tile6, tile3, Direction.UP);
	board.performMove(mul);
	assertEquals(63, tile3.getValue());
	
	Move illegal = new Move(tile5, tile2, Direction.ILLEGAL);
	assertFalse(board.performMove(illegal));
	
	/*Our board now looks like:
	 * 			1		
	 * 63				5
	 * 					10
	 * */
	
	/*Next let us check the getters*/
	/*Since we haven't updated the available moves, there should be 2 of them*/
	assertEquals(0, board.getMoves().size());
	board.updateAvailableMoves();
	assertEquals(2, board.getMoves().size()); //there should only be one move with this board configuration
	assertEquals(tile5, board.getMoves().get(0).getStart());
	assertEquals(tile8, board.getMoves().get(0).getFinish());
	assertEquals(tile8, board.getMoves().get(1).getStart());
	assertEquals(tile5, board.getMoves().get(1).getFinish());
	
	
	assertEquals(4, board.getTiles().size()); //should be 4 tiles left
	assertEquals(tile1, board.getTiles().get(0));
	assertEquals(tile3, board.getTiles().get(1));
	assertEquals(tile5, board.getTiles().get(2));
	assertEquals(tile8, board.getTiles().get(3));
	}
	
	
}
