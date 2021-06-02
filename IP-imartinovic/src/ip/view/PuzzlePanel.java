package ip.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JPanel;

import ip.model.Board;
import ip.model.Coordinate;
import ip.model.Model;
import ip.model.Tile;

public class PuzzlePanel extends JPanel{
	
	Model model;
	public static final int boxsize = 80;
	public static final int gap = 2;
	public static final int offsetX = 25;
	public static final int offsetY = 100;
	
	public PuzzlePanel(Model m) {
		this.model = m;
	}
	
	public Rectangle computeRectangle(int index, int boardsize) {
		Tile t = new Tile(0); //create a token tile just to draw the rectangle
		t.setIndex(index, boardsize);
		
		int row = t.getRow();
		int column = t.getColumn();
		Rectangle rectangle = new Rectangle(offsetX + column*boxsize+gap,offsetY + row*boxsize+gap, boxsize-gap, boxsize-gap);
		return rectangle;
	}
	
	public Coordinate pointToCoordinate(Point p) {
		return new Coordinate((p.x - offsetX)/boxsize,(p.y - offsetY)/boxsize);
	}
	
		
	@Override 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(model == null) {return;}
		
		Board board = model.getBoard();
		for (int i = 0; i < board.BOARDSIZE*board.BOARDSIZE; ++i) {
			g.setColor(Color.GRAY);
			Rectangle r = computeRectangle(i, board.BOARDSIZE);
			g.fillRect(r.x, r.y, r.width, r.height);
		}
		if (model.getSelectedTile() != null) {
			g.setColor(Color.YELLOW);
			Rectangle r = computeRectangle(model.getSelectedTile().getIndex(), model.getBoard().BOARDSIZE);
			g.fillRect(r.x, r.y, r.width, r.height);
		}
		
		if (model.getHasWon()) {
			g.setColor(Color.GREEN);
			Rectangle r = computeRectangle(model.getBoard().centerIndex, model.getBoard().BOARDSIZE);
			g.fillRect(r.x, r.y, r.width, r.height);
		} else if (model.getHasLost()) {
			for (Tile t : board.getTiles()) {
				g.setColor(Color.RED);
				Rectangle r = computeRectangle(t.getIndex(), board.BOARDSIZE);
				g.fillRect(r.x, r.y, r.width, r.height);
			}
		}
		
		for (Tile t : board.getTiles()) {
			g.setColor(Color.BLACK);
			int row = t.getRow();
			int column = t.getColumn();
			String value = Integer.toString(t.getValue());
			g.drawString(value,offsetX + column*boxsize + boxsize/2,offsetY + row*boxsize + boxsize/2);
		}
	}
}
