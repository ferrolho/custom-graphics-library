package library;

import java.awt.Point;
import java.util.Stack;

import exceptions.ImpossibleToPopIdentityMatrix;
import algebra.Algebra;
import algebra.Matrix;

public class Polygon {

	private Point[] points;
	private Point centroid;
	private Stack<Matrix> matrixStack;

	public Polygon(Point[] points) {
		// save points
		this.points = points;

		// initialize matrix stack
		matrixStack = new Stack<Matrix>();
		matrixStack.push(new Matrix());

		// calculate centroid
		centroid = calculateCentroid();
	}

	public Point getPoint(int p) {
		Matrix m = Algebra.multiplyMatrices(new Matrix(points[p]),
				matrixStack.peek());

		return new Point((int) m.getColumn(0)[0], (int) m.getColumn(0)[1]);
	}

	private Point calculateCentroid() {
		int x = 0;
		for (int i = 0; i < points.length; i++)
			x += getPoint(i).getX();
		x /= points.length;

		int y = 0;
		for (int i = 0; i < points.length; i++)
			y += getPoint(i).getY();
		y /= points.length;

		return new Point(x, y);
	}

	public Point getCentroid() {
		return calculateCentroid();
	}

	public Matrix translate(double dx, double dy) {
		// calculate transformation matrix
		double[][] transfM = { { 1, 0, dx }, { 0, 1, dy }, { 0, 0, 1 } };

		return transformAndPushMatrix(transfM);
	}

	public Matrix scale(double sx, double sy) {
		// calculate transformation matrix
		double[][] transfM = { { sx, 0, 0 }, { 0, sy, 0 }, { 0, 0, 1 } };

		return transformAndPushMatrix(transfM);
	}

	public Matrix scaleFromCenter(double sx, double sy) {
		// translate to origin
		translate(-centroid.getX(), -centroid.getY());

		// calculate transformation matrix
		double[][] transfM = { { sx, 0, 0 }, { 0, sy, 0 }, { 0, 0, 1 } };

		// scale
		transformAndPushMatrix(transfM);

		// translate back to original place
		Matrix result = translate(centroid.getX(), centroid.getY());

		// pop auxiliar matrixes
		for (int i = 0; i < 3; i++)
			popMatrix();

		return pushMatrix(result);
	}

	public Matrix rotate(double angle) {
		// convert angle in degrees to radians
		angle = Math.toRadians(angle);

		// calculate transformation matrix
		double[][] transfM = { { Math.cos(angle), -Math.sin(angle), 0 },
				{ Math.sin(angle), Math.cos(angle), 0 }, { 0, 0, 1 } };

		return transformAndPushMatrix(transfM);
	}

	public Matrix rotateFromCenter(double angle) {
		// translate to origin
		translate(-centroid.getX(), -centroid.getY());

		// convert angle in degrees to radians
		angle = Math.toRadians(angle);

		// calculate transformation matrix
		double[][] transfM = { { Math.cos(angle), -Math.sin(angle), 0 },
				{ Math.sin(angle), Math.cos(angle), 0 }, { 0, 0, 1 } };

		// rotate
		transformAndPushMatrix(transfM);

		// translate back to original place
		Matrix result = translate(centroid.getX(), centroid.getY());

		// pop auxiliar matrixes
		for (int i = 0; i < 3; i++)
			popMatrix();

		return pushMatrix(result);
	}

	private Matrix transformAndPushMatrix(double[][] transfM) {
		// get transformation matrix
		Matrix m = new Matrix(3, 3, transfM);

		// get current matrix
		Matrix prev = matrixStack.peek();

		return pushMatrix(Algebra.multiplyMatrices(prev, m));
	}

	private Matrix pushMatrix(Matrix m) {
		// push to stack
		matrixStack.push(m);

		// return stack top
		return matrixStack.peek();
	}

	public void popMatrix() {
		try {
			if (matrixStack.size() <= 1)
				throw new ImpossibleToPopIdentityMatrix();
			else
				matrixStack.pop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void resetMatrixStack() {
		matrixStack.clear();
		matrixStack.push(new Matrix());
	}

}
