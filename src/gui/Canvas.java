package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import library.CustomGraphics;
import library.Mouse;
import library.Rectangle;
import res.Values;

public class Canvas extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final Color backgroundColor = Color.WHITE;

	private ArrayList<Rectangle> rectangles;

	private Timer updateTimer, drawTimer;

	public Canvas() {
		// set canvas background
		setBackground(backgroundColor);
		setDoubleBuffered(true);

		// add mouse listener
		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);

		rectangles = new ArrayList<Rectangle>();

		for (int i = 0; i < 100; i++)
			rectangles.add(new library.Rectangle(20, 20, 60, 60));

		// initialize and start timers
		initTimers();
		startTimers();
	}

	private void initTimers() {
		int delay;

		delay = (int) Math.round(1000.0 / Values.Numbers.FPS);
		updateTimer = new Timer(delay, updateTimerListener);

		delay = (int) Math.round(1000.0 / Values.Numbers.drawFPS);
		drawTimer = new Timer(delay, drawTimerListener);
	}

	private void startTimers() {
		updateTimer.start();
		drawTimer.start();
	}

	double x = 0;

	ActionListener updateTimerListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			for (Rectangle rectangle : rectangles)
				rectangle.resetMatrixStack();

			x++;

			for (int i = 0; i < rectangles.size(); i++) {
				rectangles.get(i).rotateFromCenter(2 * x);
				rectangles.get(i).scale(i * 0.001 * x, i * 0.001 * x);
			}
		}
	};

	ActionListener drawTimerListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	};

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		draw(g);
	}

	private void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		if (Mouse.isPressed())
			CustomGraphics.drawRectangle(Mouse.getSrc().x, Mouse.getSrc().y,
					Mouse.getDest().x, Mouse.getDest().y, Color.RED, g2d);

		for (Rectangle rectangle : rectangles)
			CustomGraphics.drawRectangle(rectangle, Color.BLACK, g2d);
	}

}
