package ip;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import ip.controller.ExitGameController;
import ip.model.Model;
import ip.view.ArithmeticSquareApp_Window;

public class Main {
	public static void main (String[] args) {
		Model model = new Model();
		ArithmeticSquareApp_Window window = new ArithmeticSquareApp_Window(model);
		window.setVisible(true);
		
		window.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				new ExitGameController(window).process();
			}
		});
	}
}
