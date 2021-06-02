package test.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import ip.controller.RestartGameController;
import ip.model.Tile;
import ip.view.UpdateButtons;

public class TestRestartGameController extends TestController {
	@Test 
	public void testRestartConroller() {
		RestartGameController controller = new RestartGameController(model, window);
		model.setSelectedTile(model.getBoard().getTiles().get(4));
		UpdateButtons.enableButtons(window, model.selectedTileAvailableMoves());
		window.repaint();
		
		controller.process();
		
		ArrayList<Tile> tiles = model.getBoard().getTiles();
		assertEquals(1, tiles.get(0).getValue());
		assertEquals(4, tiles.get(1).getValue());
		assertEquals(3, tiles.get(2).getValue());
		assertEquals(7, tiles.get(3).getValue());
		assertEquals(2, tiles.get(4).getValue());
		assertEquals(5, tiles.get(5).getValue());
		assertEquals(9, tiles.get(6).getValue());
		assertEquals(8, tiles.get(7).getValue());
		assertEquals(6, tiles.get(8).getValue());
	
		assertEquals(18, model.getBoard().getMoves().size());
		assertFalse(model.getHasLost());
		assertFalse(model.getHasWon());
		assertEquals(null, model.getSelectedTile());
		
		assertFalse(window.getBtnUp().isEnabled());
		assertFalse(window.getBtnDown().isEnabled());
		assertFalse(window.getBtnLeft().isEnabled());
		assertFalse(window.getBtnRight().isEnabled());
	}
}
