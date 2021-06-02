package ip.view;

import java.util.ArrayList;

import ip.model.Direction;
import ip.model.Move;

public class UpdateButtons {

	public static void enableButtons(ArithmeticSquareApp_Window window, ArrayList<Move> moves) {
		window.getBtnUp().setEnabled(false);
		window.getBtnDown().setEnabled(false);
		window.getBtnLeft().setEnabled(false);
		window.getBtnRight().setEnabled(false);
		window.getBtnRestart().setEnabled(true);
	
		for (Move m : moves) {
			if (m.getDirection().equals(Direction.UP)) {
				window.getBtnUp().setEnabled(true);
			} else if (m.getDirection().equals(Direction.DOWN)) {
				window.getBtnDown().setEnabled(true);
			} else if (m.getDirection().equals(Direction.LEFT)) {
				window.getBtnLeft().setEnabled(true);
			} else if (m.getDirection().equals(Direction.RIGHT)) {
				window.getBtnRight().setEnabled(true);
			} 
		}
	}
}
