package TowerDefense;

import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame {
	public static String title = "Tower Defense v1.0";
	public static Dimension size = new Dimension(1024, 768);					//700, 550

	public static void main (String args[]) {
		Frame frame = new Frame();
	}
	
	public Frame() {
		setTitle(title);
		setSize(size);
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initialize();
	}

	public void initialize() {
		this.setLayout(new GridLayout(1, 1, 0, 0));
		this.setVisible(true);
		Screen screen = new Screen(this);
		this.add(screen);
	}
}

