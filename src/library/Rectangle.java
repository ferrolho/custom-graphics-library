package library;

import java.awt.Point;

public class Rectangle extends Polygon {

	public Rectangle(int x1, int y1, int x2, int y2) {
		super(new Point[] { new Point(x1, y1), new Point(x2, y1),
				new Point(x2, y2), new Point(x1, y2) });
	}

	public Rectangle(int p1x, int p1y, int p2x, int p2y, int p3x, int p3y,
			int p4x, int p4y) {
		super(new Point[] { new Point(p1x, p1y), new Point(p2x, p2y),
				new Point(p3x, p3y), new Point(p4x, p4y) });
	}

}
