package library;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter {
	private static Point src, dest;
	private static Boolean pressed;

	public Mouse() {
		src = new Point();
		dest = new Point();
		pressed = false;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		switch (e.getButton()) {
		case MouseEvent.BUTTON1:
			src.setLocation(e.getX(), e.getY());
			dest.setLocation(e.getX(), e.getY());
			pressed = true;
			break;
		case MouseEvent.BUTTON2:
			break;
		case MouseEvent.BUTTON3:
			break;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		switch (e.getButton()) {
		case MouseEvent.BUTTON1:
			dest.setLocation(e.getX(), e.getY());
			pressed = false;
			break;
		case MouseEvent.BUTTON2:
			break;
		case MouseEvent.BUTTON3:
			break;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		dest.setLocation(e.getX(), e.getY());
	}

	public static Point getSrc() {
		return src;
	}

	public static void setSrc(Point src) {
		Mouse.src = src;
	}

	public static Point getDest() {
		return dest;
	}

	public static void setDest(Point dst) {
		Mouse.dest = dst;
	}

	public static Boolean isPressed() {
		return pressed;
	}

	public static void setPressed(Boolean pressed) {
		Mouse.pressed = pressed;
	}
}
