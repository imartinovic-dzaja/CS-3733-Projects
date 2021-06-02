package test.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ip.model.Coordinate;
import ip.model.Tile;

public class TestTile {
	
	@Test
	void testTileConstructorSetGet() {
		Tile tile = new Tile(100);
		assertEquals(-1, tile.getIndex());
		assertEquals(-1, tile.getRow());
		assertEquals(-1, tile.getColumn());
		assertEquals(100, tile.getValue());
		
		tile.setIndex(6, 3); 
		/*Put the tile at index 6 on a 3*3 board
		 * 0 1 2
		 * 3 4 5
		 * 6 7 8
		 * */
		assertEquals(6, tile.getIndex());
		assertEquals(2, tile.getRow());
		assertEquals(0, tile.getColumn());
		assertEquals(100, tile.getValue());
	
		tile.setValue(69);
		assertEquals(69, tile.getValue());
	}
	
	@Test
	void testTileIsAtCoordinate() {
		Tile tile = new Tile(17);
		tile.setIndex(13, 5);
		/*Set the tile on index 13 on a 5x5 board
		 * 0	1	2	3	4
		 * 5	6	7	8	9	
		 * 10	11	12	13	14
		 * 15	16	17	18	19
		 * 20	21	22	23	24
		 * */
		Coordinate correctCoordinate = new Coordinate(3, 2);
		Coordinate incorrectRow = new Coordinate(3, 1);
		Coordinate incorrectColumn = new Coordinate(4,2);
		Coordinate incorrectCompletely = new Coordinate (4,4);
		
		assertTrue(tile.isAtCoordinate(correctCoordinate));
		assertFalse(tile.isAtCoordinate(incorrectRow));
		assertFalse(tile.isAtCoordinate(incorrectColumn));
		assertFalse(tile.isAtCoordinate(incorrectCompletely));
	}
	 
 }

