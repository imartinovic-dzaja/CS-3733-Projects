package ip.controller;

import javax.swing.JOptionPane;

import ip.view.ArithmeticSquareApp_Window;

public class ExitGameController {
	private ArithmeticSquareApp_Window  window;
	
	public ExitGameController(ArithmeticSquareApp_Window  window) {
		this.window = window;
	}
	
	public void process() {
		int c = JOptionPane.showConfirmDialog(window, "Are you sure you wish to exit?");
		if (c == JOptionPane.OK_OPTION) {
			window.setVisible(false);
			window.dispose();
		}
	}
}
