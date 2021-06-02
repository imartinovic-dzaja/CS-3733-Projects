package ip.controller;

import java.awt.Point;
import java.util.ArrayList;

import ip.model.Coordinate;
import ip.model.Model;
import ip.model.Tile;
import ip.view.ArithmeticSquareApp_Window;
import ip.view.UpdateButtons;

public class SelectTileController {
	private Model model;
	private ArithmeticSquareApp_Window  window;
	
	public SelectTileController(Model model, ArithmeticSquareApp_Window  window) {
		this.model = model;
		this.window = window;
	}
	
	public void process(Point p) {
		Coordinate c = window.getPanel().pointToCoordinate(p);
		
		ArrayList<Tile> tiles = model.getBoard().getTiles();
		for (Tile t : tiles) {
			if (t.isAtCoordinate(c)) {
				model.setSelectedTile(t);
				UpdateButtons.enableButtons(window, model.selectedTileAvailableMoves());
				window.repaint();
			}
		}
	}
}
