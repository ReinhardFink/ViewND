package viewND.nd;

import java.text.DecimalFormat;

import viewND.CONSTANTS;
import viewND.nd.primitive.SimplePointND;

public class AffineTransformND {

	/*
	 * Contains all stuff for affine transformations.
	 * 
	 * Example for Dimension n = 3
	 *
	 */
	// [ x'] [ m00 m01 m02 ] [ x ] [ m00x + m01y + m02z ]
	// [ y']=[ m10 m11 m12 ] [ y ]=[ m10x + m11y + m12z ]
	// [ z'] [ m20 m21 m22 ] [ z ] [ m20x + m21y + m22z ]

	public double[][] matrix;

	public AffineTransformND() {
		this.matrix = new double[CONSTANTS.n][CONSTANTS.n];
		for (int i = 0; i < CONSTANTS.n; i++)
			matrix[i][i] = 1;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (int line = 0; line < CONSTANTS.n; line++) {
			for (int row = 0; row < CONSTANTS.n; row++) {
				buffer.append(new DecimalFormat("+#,##0.00;-#").format(matrix[line][row]));
				buffer.append('|');
			}
			buffer.append('\n');
		}
		return buffer.toString();
	}

	public boolean equals(Object o) {
		AffineTransformND t = (AffineTransformND) o;
		for (int line = 0; line < CONSTANTS.n; line++)
			for (int row = 0; row < CONSTANTS.n; row++)
				if (Math.abs(this.matrix[line][row] - t.matrix[line][row]) > CONSTANTS.DELTA)
					return false;
		return true;
	}

	private double[][] createNewMatrix(AffineTransformND t) {
		double temp[][] = new double[CONSTANTS.n][CONSTANTS.n];	
		for (int line = 0; line < CONSTANTS.n; line++)
			for (int row = 0; row < CONSTANTS.n; row++) {
				double sum = 0;
				for (int i = 0; i < CONSTANTS.n; i++)
					sum += matrix[line][i] * t.matrix[i][row];
				temp[line][row] = sum;
			}
		return temp;
	}

	/*
	 * [new] = [this] x [t]
	 */
	public AffineTransformND mult(AffineTransformND t) {
		AffineTransformND newAT = new AffineTransformND();
		newAT.matrix = createNewMatrix(t);
		return newAT;
	}

	/*
	 * [this] = [this] x [t]
	 */
	public AffineTransformND concatenate(AffineTransformND t) {
		this.matrix = createNewMatrix(t);
		return this;
	}

	/*
	 * Example for rotation instance for rotation IN x2 & x4 plane in R^4
	 * 
	 * [ 1 0 0 0 ] [x1] = [ x1 ] [ 0 cos 0 -sin ] [x2] = [x2 * cos - x4 * sin ]
	 * [ 0 0 1 0 ] [x3] = [ x3 ] [ 0 sin 0 cos ] [x4] = [x2 * sin + x4 * cos ]
	 */
	public static AffineTransformND getRotation_i_j(int i, int j, double theta) {
		AffineTransformND rotation = new AffineTransformND();
		rotation.matrix[i][i] = 0;
		rotation.matrix[j][j] = 0;
		rotation.matrix[i][i] = +Math.cos(theta);
		rotation.matrix[i][j] = -Math.sin(theta);
		rotation.matrix[j][i] = +Math.sin(theta);
		rotation.matrix[j][j] = +Math.cos(theta);
		return rotation;
	}

	/*
	 * returns an with this transformation transformed pointND
	 */
	public SimplePointND transform(SimplePointND p) {
		SimplePointND newPoint = new SimplePointND();
		for (int line = 0; line < CONSTANTS.n; line++) {
			double sum = 0;
			for (int i = 0; i < CONSTANTS.n; i++)
				sum += this.matrix[line][i] * p.getXi(i);
			newPoint.setXi(line, sum);
		}
		return newPoint;
	}

}
