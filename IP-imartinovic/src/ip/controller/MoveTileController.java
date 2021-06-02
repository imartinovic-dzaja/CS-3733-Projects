package ip.controller;

import ip.model.Direction;
import ip.model.Model;
import ip.model.Move;
import ip.view.ArithmeticSquareApp_Window;
import ip.view.UpdateButtons;

public class MoveTileController {
	private Model model;
	private ArithmeticSquareApp_Window  window;
	
	public MoveTileController(Model model, ArithmeticSquareApp_Window  window) {
		this.model = model;
		this.window = window;
	}
	
	public void process(Direction dir) {
		if (model.getSelectedTile() == null) {
			return ;
		}
		
		Move move = model.getMoveForSelectedTileGivenDirection(dir);
		model.setSelectedTile(null);
		model.getBoard().performMove(move);
		model.getBoard().updateAvailableMoves();
		UpdateButtons.enableButtons(window, model.selectedTileAvailableMoves());
		model.hasWonOrLost();
		window.repaint();
	}
}
