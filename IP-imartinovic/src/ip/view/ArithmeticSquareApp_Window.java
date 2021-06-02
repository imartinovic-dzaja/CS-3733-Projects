package ip.view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ip.controller.MoveTileController;
import ip.controller.RestartGameController;
import ip.controller.SelectTileController;
import ip.model.Direction;
import ip.model.Model;
import ip.model.Move;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ArithmeticSquareApp_Window extends JFrame {
	
	private Model model;
	private JPanel contentPane;
	private PuzzlePanel panel;
	JButton btnUp;
	JButton btnDown;
	JButton btnLeft;
	JButton btnRight;
	JButton btnRestartGame;

	public PuzzlePanel getPanel() {
		return panel;
	}
	
	public JButton getBtnUp() {return btnUp;}
	public JButton getBtnDown() {return btnDown;}
	public JButton getBtnLeft() {return btnLeft;}
	public JButton getBtnRight() {return btnRight;}
	public JButton getBtnRestart() {return btnRestartGame;}
	/**
	 * Create the frame.
	 */
	public ArithmeticSquareApp_Window(Model model) {
		this.model = model;
		setResizable(false);
		setTitle("ArithmeticSquareApp");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 510, 534);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		panel = new PuzzlePanel(model);
		panel.addMouseListener(new MouseAdapter( ) {
			@Override
			public void mousePressed(MouseEvent me) {
				new SelectTileController(model, ArithmeticSquareApp_Window.this).process(me.getPoint());
			}
		});
		
		btnUp = new JButton("^");	
		btnDown = new JButton("v");
		btnLeft = new JButton("<");
		btnRight = new JButton(">");
		btnRestartGame = new JButton("Restart Game");
		
		btnUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new MoveTileController(model, ArithmeticSquareApp_Window.this).process(Direction.UP);
				
			}
			
		});
		
		btnDown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new MoveTileController(model, ArithmeticSquareApp_Window.this).process(Direction.DOWN);
				
			}
			
		});
		
		btnLeft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new MoveTileController(model, ArithmeticSquareApp_Window.this).process(Direction.LEFT);
				
			}
			
		});
		
		btnRight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new MoveTileController(model, ArithmeticSquareApp_Window.this).process(Direction.RIGHT);
				
			}
			
		});
		
		btnRestartGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new RestartGameController(model, ArithmeticSquareApp_Window.this).process();
			}
			
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(39)
							.addComponent(btnLeft)
							.addGap(55)
							.addComponent(btnRight))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(88)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnRestartGame)
								.addComponent(btnDown)
								.addComponent(btnUp))))
					.addGap(86))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 475, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(168)
							.addComponent(btnUp)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnRight)
								.addComponent(btnLeft))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDown)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnRestartGame)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		UpdateButtons.enableButtons(this, new ArrayList<Move>());
	}
}
