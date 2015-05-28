package algebra;

import exceptions.InvalidMatricesForMultiplication;

public class Algebra {

	public static final Matrix multiplyMatrices(Matrix m1, Matrix m2) {
		Matrix result = null;

		try {
			if (m1.getHeight() != m2.getWidth())
				throw new InvalidMatricesForMultiplication();
			else {
				double[][] m = new double[m2.getHeight()][m1.getWidth()];

				for (int k = 0; k < m1.getWidth(); k++) {
					for (int i = 0; i < m2.getHeight(); i++) {
						double res = 0;

						for (int j = 0; j < m2.getWidth(); j++)
							res += m2.get(i, j) * m1.get(j, k);

						m[i][k] = res;
					}

					result = new Matrix(m1.getWidth(), m1.getHeight(), m);
				}
			}
		} catch (InvalidMatricesForMultiplication e) {
			e.printStackTrace();
		}

		return result;
	}

}
