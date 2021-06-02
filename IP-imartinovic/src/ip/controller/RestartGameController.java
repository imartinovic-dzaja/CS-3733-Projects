package ip.controller;

import ip.model.Model;
import ip.view.ArithmeticSquareApp_Window;
import ip.view.UpdateButtons;

public class RestartGameController {
	private Model model;
	private ArithmeticSquareApp_Window  window;
	
	public RestartGameController(Model model, ArithmeticSquareApp_Window  window) {
		this.model = model;
		this.window = window;
	}
	
	public void process() {
		model.restartGame();
		UpdateButtons.enableButtons(window, model.selectedTileAvailableMoves());
		window.repaint();
	}
}
