package test.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ip.model.Coordinate;



public class TestCoordinate {
	@Test
	void testCoodinate() {
	Coordinate coord = new Coordinate(3, 7);
	assertEquals(coord.x, 3);
	assertEquals(coord.y,7);
	}
}
