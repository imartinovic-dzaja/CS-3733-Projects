package test.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Point;

import org.junit.jupiter.api.Test;

import ip.controller.SelectTileController;
import ip.model.Coordinate;
import ip.model.Tile;
import ip.view.PuzzlePanel;

public class TestSelectTileController extends TestController {
	
	
	@Test
	public void testSelectController() {
		SelectTileController controller = new SelectTileController(model, window);
		Point p = coordinateToPoint(new Coordinate(1,1));
		assertEquals(1, window.getPanel().pointToCoordinate(p).x);
		assertEquals(1, window.getPanel().pointToCoordinate(p).y);
		
		controller.process(p);
		
		Tile tile = model.getSelectedTile();
		assertEquals(4, tile.getIndex());
		assertTrue(window.getBtnUp().isEnabled());
		assertTrue(window.getBtnDown().isEnabled());
		assertTrue(window.getBtnLeft().isEnabled());
		assertTrue(window.getBtnRight().isEnabled());
		
	}
	
	public Point coordinateToPoint(Coordinate coord) {
		int boxsize = PuzzlePanel.boxsize;
		int offsetX = PuzzlePanel.offsetX;
		int offsetY = PuzzlePanel.offsetY;
		return new Point(coord.x*boxsize + offsetX + boxsize/2, coord.y*boxsize + offsetY + boxsize/2);
	}
}
