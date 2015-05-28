package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.UIManager;

import res.Values;

public class Window {

	private JFrame frame;
	private Canvas canvas;

	public Window() {
		frame = new JFrame();
		frame.setTitle(Values.Strings.TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Error initiating the frame\n");
		}

		// add canvas
		canvas = new Canvas();
		frame.getContentPane().add(canvas, BorderLayout.CENTER);

		setSizeAndLocation();
	}

	private void setSizeAndLocation() {
		// set size
		frame.setSize(700, 500);

		// set location
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((int) (dim.getWidth() / 2 - frame.getWidth() / 2),
				(int) (dim.getHeight() / 2 - frame.getHeight() / 2));
	}

	public void start() {
		frame.setVisible(true);
	}

}
