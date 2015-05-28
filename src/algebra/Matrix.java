package algebra;

import java.awt.Point;

import exceptions.InvalidMatrix;

public class Matrix {

	private double[][] matrix;

	public Matrix() {
		this(3);
	}

	public Matrix(int size) {
		try {
			if (size == 0)
				throw new InvalidMatrix();
			else {
				matrix = new double[size][size];

				for (int i = 0; i < size; i++)
					matrix[i][i] = 1;
			}
		} catch (InvalidMatrix e) {
			e.printStackTrace();
		}
	}

	public Matrix(int x, int y, int z) {
		matrix = new double[3][1];

		matrix[0][0] = x;
		matrix[1][0] = y;
		matrix[2][0] = z;
	}

	public Matrix(Point p) {
		matrix = new double[3][1];

		matrix[0][0] = p.getX();
		matrix[1][0] = p.getY();
		matrix[2][0] = 1;
	}

	public Matrix(Point[] points) {
		matrix = new double[3][points.length];

		for (int i = 0; i < points.length; i++) {
			matrix[0][i] = points[i].getX();
			matrix[1][i] = points[i].getY();
			matrix[2][i] = 1;
		}
	}

	public Matrix(int w, int h, double[][] m) {
		try {
			if (w != m[0].length || h != m.length)
				throw new InvalidMatrix();
			else
				matrix = m;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getWidth() {
		return matrix[0].length;
	}

	public int getHeight() {
		return matrix.length;
	}

	public double get(int line, int column) {
		return matrix[line][column];
	}

	public double[] getColumn(int column) {
		double[] col = new double[getHeight()];

		for (int i = 0; i < getHeight(); i++)
			col[i] = matrix[i][column];

		return col;
	}

	public void print() {
		for (int i = 0; i < getHeight(); i++) {
			for (int j = 0; j < getWidth(); j++)
				System.out.print(matrix[i][j] + "\t");

			System.out.println();
		}
	}

}
