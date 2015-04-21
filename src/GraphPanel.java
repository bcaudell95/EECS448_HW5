import java.awt.Graphics;

import javax.swing.JPanel;


public class GraphPanel extends JPanel {
	
	private static final int PREF_W = 490;
	private static final int PREF_H = 330;
	private static final int[] X_TICK_MARK_POS = {PREF_W/6, 2*PREF_W/6, 3*PREF_W/6, 4*PREF_W/6, 5*PREF_W/6};
	private static final String[] X_AXIS_LABELS = {"E D", "M D", "E V", "M V", "Custom"};
	private static final int[] Y_TICK_MARK_POS = {PREF_H/6, 5*PREF_H/6};
	private static final String[] Y_AXIS_LABELS = {"1", "0"};
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintGraph(g);
	};
	
	public void paintGraph(Graphics g) {
		//First show the axes for the graph
		//horizontal axis
		int y = PREF_H-20;
		int x;
		g.drawLine(20, y, PREF_W-5, y);
		for(int i = 0;i<X_TICK_MARK_POS.length;i++) {
			x = X_TICK_MARK_POS[i];
			
			String label = X_AXIS_LABELS[i];
			g.drawLine(x,y-5,x,y+5);
			g.drawChars(label.toCharArray(), 0, label.length(), x-10, y+20);
		}
		
		//Vertical Axis
		x=20;
		g.drawLine(x, 20, x, PREF_H-20);
		for(int i = 0;i<Y_TICK_MARK_POS.length;i++) {
			y = Y_TICK_MARK_POS[i];
			
			String label = Y_AXIS_LABELS[i];
			g.drawLine(x-5,y,x+5,y);
			g.drawChars(label.toCharArray(), 0, label.length(), x-15, y+5);
		}
	}
}
