package library;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class CustomGraphics {

	public static void swap(Point point) {
		int temp = point.x;
		point.x = point.y;
		point.y = temp;
	}

	public static void drawPixel(int x, int y, Color color, Graphics2D g2d) {
		// set color
		g2d.setColor(color);

		// draw pixel
		g2d.drawLine(x, y, x, y);
	}

	public static void drawPixel(Point point, Color color, Graphics2D g2d) {
		drawPixel(point.x, point.y, color, g2d);
	}

	public static void drawLine(int xi, int yi, int xf, int yf, Color color,
			Graphics2D g2d) {
		// set color
		g2d.setColor(color);

		Point pi = new Point(xi, yi);
		Point pf = new Point(xf, yf);
		Point delta = new Point(Math.abs(xf - xi), Math.abs(yf - yi));

		boolean invertXY = false;
		if (delta.y > delta.x) {
			swap(pi);
			swap(pf);
			swap(delta);
			invertXY = true;
		}

		if (pf.x < pi.x) {
			int temp;

			// swap xi xf
			temp = pi.x;
			pi.x = pf.x;
			pf.x = temp;

			// swap yi yf
			temp = pi.y;
			pi.y = pf.y;
			pf.y = temp;
		}

		// do not touch this
		int inc2 = 2 * delta.y;
		int d = inc2 - delta.x; // d = 2*b – a;
		int inc1 = d - delta.x; // inc1 = 2*(b-a);

		int x = pi.x, y = pi.y;
		for (int i = 0; i < delta.x; i++, x++) {
			if (invertXY)
				drawPixel(y, x, color, g2d);
			else
				drawPixel(x, y, color, g2d);

			if (d >= 0) {
				if (pi.y > pf.y)
					y--;
				else
					y++;

				d += inc1;
			} else
				d += inc2;
		}
	}

	public static void drawRectangle(Rectangle r, Color color, Graphics2D g2d) {
		drawRectangle(r.getPoint(0).x, r.getPoint(0).y, r.getPoint(1).x,
				r.getPoint(1).y, r.getPoint(2).x, r.getPoint(2).y,
				r.getPoint(3).x, r.getPoint(3).y, color, g2d);
	}

	public static void drawRectangle(int xi, int yi, int xf, int yf,
			Color color, Graphics2D g2d) {
		// y correction
		if (yi > yf) {
			int temp = yi;
			yi = yf;
			yf = temp;
		}

		// x correction
		if (xi > xf) {
			int temp = xi;
			xi = xf;
			xf = temp;
		}

		// horizontal borders
		for (int i = xi; i <= xf; i++) {
			drawPixel(i, yi, color, g2d);
			drawPixel(i, yf, color, g2d);
		}

		// vertical borders
		for (int i = yi; i <= yf; i++) {
			drawPixel(xi, i, color, g2d);
			drawPixel(xf, i, color, g2d);
		}
	}

	public static void drawRectangle(int p1x, int p1y, int p2x, int p2y,
			int p3x, int p3y, int p4x, int p4y, Color color, Graphics2D g2d) {
		// p1 to p2
		drawLine(p1x, p1y, p2x, p2y, color, g2d);

		// p2 to p3
		drawLine(p2x, p2y, p3x, p3y, color, g2d);

		// p3 to p4
		drawLine(p3x, p3y, p4x, p4y, color, g2d);

		// p4 to p1
		drawLine(p4x, p4y, p1x, p1y, color, g2d);
	}

}
