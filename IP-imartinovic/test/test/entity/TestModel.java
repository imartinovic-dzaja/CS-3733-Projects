package test.entity;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


import ip.model.Board;
import ip.model.Direction;
import ip.model.Model;
import ip.model.Move;
import ip.model.Tile;

public class TestModel {
	
	@Test
	public void testModel() {
		Model model = new Model();	
		/*Checks constructor and getBoard*/
		assertEquals(1, model.getBoard().getTiles().get(0).getValue());
		assertEquals(4, model.getBoard().getTiles().get(1).getValue());
		assertEquals(3, model.getBoard().getTiles().get(2).getValue());
		assertEquals(7, model.getBoard().getTiles().get(3).getValue());
		assertEquals(2, model.getBoard().getTiles().get(4).getValue());
		assertEquals(5, model.getBoard().getTiles().get(5).getValue());
		assertEquals(9, model.getBoard().getTiles().get(6).getValue());
		assertEquals(8, model.getBoard().getTiles().get(7).getValue());
		assertEquals(6, model.getBoard().getTiles().get(8).getValue());
	
		/*No selected Tile*/
		assertEquals(null, model.getSelectedTile());
		model.setSelectedTile(null);
		assertEquals(null, model.getSelectedTile());
		assertEquals(0, model.selectedTileAvailableMoves().size());
		assertEquals(null, model.getMoveForSelectedTileGivenDirection(Direction.LEFT));
		
		/*Select tile not on the board*/
		assertFalse(model.setSelectedTile(new Tile(0)));
		/*Select tile in the center of the board*/
		assertTrue(model.setSelectedTile(model.getBoard().getTiles().get(4)));
		assertEquals(4, model.selectedTileAvailableMoves().size());
		assertEquals(1, model.selectedTileAvailableMoves().get(0).getFinish().getIndex());
		assertEquals(3, model.selectedTileAvailableMoves().get(1).getFinish().getIndex());
		assertEquals(5, model.selectedTileAvailableMoves().get(2).getFinish().getIndex());
		assertEquals(7, model.selectedTileAvailableMoves().get(3).getFinish().getIndex());
		
		assertEquals(1, model.getMoveForSelectedTileGivenDirection(Direction.UP).getFinish().getIndex());
		assertEquals(3, model.getMoveForSelectedTileGivenDirection(Direction.LEFT).getFinish().getIndex());
		assertEquals(5, model.getMoveForSelectedTileGivenDirection(Direction.RIGHT).getFinish().getIndex());
		assertEquals(7, model.getMoveForSelectedTileGivenDirection(Direction.DOWN).getFinish().getIndex());
		
		model.getBoard().updateAvailableMoves();
		model.getHasLost();
		assertFalse(model.getHasLost());
		assertFalse(model.getHasWon());
		
		//Now let us attempt to loose the game
		ArrayList<Tile> tiles = model.getBoard().getTiles();
		Board board = model.getBoard();
		Move loosingMove1 = new Move (tiles.get(4), tiles.get(1), Direction.UP);
		Move loosingMove2 = new Move (tiles.get(5), tiles.get(2), Direction.UP);
		Move loosingMove3 = new Move (tiles.get(8), tiles.get(7), Direction.UP);
		Move loosingMove4 = new Move (tiles.get(7), tiles.get(6), Direction.UP);
		Move loosingMove5 = new Move (tiles.get(6), tiles.get(3), Direction.UP);
		Move loosingMove6 = new Move (tiles.get(3), tiles.get(0), Direction.UP);
		Move loosingMove7 = new Move (tiles.get(0), tiles.get(1), Direction.UP);
		Move loosingMove8 = new Move (tiles.get(1), tiles.get(2), Direction.UP);

		board.performMove(loosingMove1);
		board.performMove(loosingMove2);
		board.performMove(loosingMove3);
		board.performMove(loosingMove4);
		board.performMove(loosingMove5);
		board.performMove(loosingMove6);
		board.performMove(loosingMove7);
		/*Right before our final move*/
		model.getBoard().updateAvailableMoves();
		model.hasWonOrLost();
		assertFalse(model.getHasLost());
		assertFalse(model.getHasWon());
		
		board.performMove(loosingMove8);
		/*After our final move*/
		model.getBoard().updateAvailableMoves();
		model.hasWonOrLost();
		assertTrue(model.getHasLost());
		assertFalse(model.getHasWon());
		
		
		/*Test restartGame*/
		model.restartGame();
		assertEquals(1, model.getBoard().getTiles().get(0).getValue());
		assertEquals(4, model.getBoard().getTiles().get(1).getValue());
		assertEquals(3, model.getBoard().getTiles().get(2).getValue());
		assertEquals(7, model.getBoard().getTiles().get(3).getValue());
		assertEquals(2, model.getBoard().getTiles().get(4).getValue());
		assertEquals(5, model.getBoard().getTiles().get(5).getValue());
		assertEquals(9, model.getBoard().getTiles().get(6).getValue());
		assertEquals(8, model.getBoard().getTiles().get(7).getValue());
		assertEquals(6, model.getBoard().getTiles().get(8).getValue());
	
		/*No selected Tile*/
		assertEquals(null, model.getSelectedTile());
		
		/*Game not won nor lost*/
		model.getBoard().updateAvailableMoves();
		model.getHasLost();
		assertFalse(model.getHasLost());
		assertFalse(model.getHasWon());
	
		
		//Now let us attempt to loose the game
				tiles = model.getBoard().getTiles();
				board = model.getBoard();
				Move winningMove1 = new Move (tiles.get(7), tiles.get(8), Direction.UP);
				Move winningMove2 = new Move (tiles.get(1), tiles.get(2), Direction.UP);
				Move winningMove3= new Move (tiles.get(8), tiles.get(5), Direction.UP);
				Move winningMove4 = new Move (tiles.get(2), tiles.get(5), Direction.UP);
				Move winningMove5 = new Move (tiles.get(6), tiles.get(3), Direction.UP);
				Move winningMove6 = new Move (tiles.get(0), tiles.get(3), Direction.UP);
				Move winningMove7 = new Move (tiles.get(3), tiles.get(4), Direction.UP);
				Move winningMove8 = new Move (tiles.get(5), tiles.get(4), Direction.UP);

				board.performMove(winningMove1);
				board.performMove(winningMove2);
				board.performMove(winningMove3);
				board.performMove(winningMove4);
				board.performMove(winningMove5);
				board.performMove(winningMove6);
				board.performMove(winningMove7);
				
				/*Right before our final move*/
				model.getBoard().updateAvailableMoves();
				model.hasWonOrLost();
				assertFalse(model.getHasLost());
				assertFalse(model.getHasWon());
				
				board.performMove(winningMove8);
				/*After our final move*/
				model.getBoard().updateAvailableMoves();
				model.hasWonOrLost();
				assertFalse(model.getHasLost());
				assertTrue(model.getHasWon());
	
	}	
	
		
}
