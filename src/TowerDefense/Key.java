package TowerDefense;

import java.awt.event.*;
import java.awt.*;

public class Key implements MouseMotionListener, MouseListener {
	public static int clickNumber = 0;
	public static int clickCount = 0;

	public void mouseClicked(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {
		Screen.store.click(e.getButton());
		clickNumber = e.getButton();
		clickCount = e.getClickCount();
	}

	public void mouseReleased(MouseEvent e) {}
	
	public void mouseDragged(MouseEvent e) {
		Screen.mse = new Point(
			(e.getX()) + ((Frame.size.width - Screen.myWidth) / 2),
			(e.getY()) - ((Frame.size.height - (Screen.myHeight)) - (Frame.size.width - Screen.myWidth) / 2)
		);
	}
	
	public void mouseMoved(MouseEvent e) {
		Screen.mse = new Point(
			(e.getX()) - ((Frame.size.width - Screen.myWidth) / 2),
			(e.getY()) - ((Frame.size.height - (Screen.myHeight)) - (Frame.size.width - Screen.myWidth) / 2)
		);
	}
}
