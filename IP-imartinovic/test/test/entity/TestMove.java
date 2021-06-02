package test.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ip.model.Direction;
import ip.model.Move;
import ip.model.Tile;

public class TestMove {
	@Test
	void TestConstructorGetSet() {
		Tile t1 = new Tile(10);
		Tile t2 = new Tile(14);
		
		Move move1 = new Move(t1, t2, Direction.LEFT);
		Move move2 = new Move(t2, t1, Direction.ILLEGAL);
		
		assertEquals(t1, move1.getStart());
		assertEquals(t2, move1.getFinish());
		assertEquals(Direction.LEFT, move1.getDirection());
		
		assertEquals(t2, move2.getStart());
		assertEquals(t1, move2.getFinish());
		assertEquals(Direction.ILLEGAL, move2.getDirection());
	}
}
