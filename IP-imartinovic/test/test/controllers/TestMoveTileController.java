package test.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import ip.controller.MoveTileController;
import ip.model.Direction;
import ip.view.UpdateButtons;

public class TestMoveTileController extends TestController {
	@Test 
	public void testMoveConroller() {
		MoveTileController controller = new MoveTileController(model, window);
		model.setSelectedTile(model.getBoard().getTiles().get(4));
		UpdateButtons.enableButtons(window, model.selectedTileAvailableMoves());
		window.repaint();
		
		controller.process(Direction.DOWN);
		
		assertEquals(null, model.getSelectedTile());
		assertEquals(11, model.getBoard().getMoves().size());
		assertEquals(8, model.getBoard().getTiles().size());
		assertEquals(4, model.getBoard().getTiles().get(6).getValue());
		assertFalse(window.getBtnUp().isEnabled());
		assertFalse(window.getBtnDown().isEnabled());
		assertFalse(window.getBtnLeft().isEnabled());
		assertFalse(window.getBtnRight().isEnabled());
	}
}	
