package test.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import ip.model.Model;
import ip.view.ArithmeticSquareApp_Window;

public abstract class TestController {
	protected Model model;
	protected ArithmeticSquareApp_Window window;
	
	@BeforeEach
	public void setUp() {
		model = new Model();
		window = new ArithmeticSquareApp_Window(model);
		window.setVisible(true);
	}
	
	@AfterEach
	public void tearDown() {
		window.setVisible(false);
	}
	
}
