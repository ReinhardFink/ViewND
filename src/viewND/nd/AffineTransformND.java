package viewND.nd;

import java.text.DecimalFormat;

import viewND.CONSTANTS;

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

	public double[][] m;

	public AffineTransformND() {
		this.m = new double[CONSTANTS.n][CONSTANTS.n];
		for (int i = 0; i < CONSTANTS.n; i++)
			m[i][i] = 1;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (int line = 0; line < CONSTANTS.n; line++) {
			for (int row = 0; row < CONSTANTS.n; row++) {
				buffer.append(new DecimalFormat("+#,##0.00;-#").format(m[line][row]));
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
				if (Math.abs(this.m[line][row] - t.m[line][row]) > CONSTANTS.DELTA)
					return false;
		return true;
	}

	/*
	 * [this] = [this] x [t]
	 */
	public AffineTransformND concatenate(AffineTransformND t) {

		double temp[][] = new double[CONSTANTS.n][CONSTANTS.n];

		for (int line = 0; line < CONSTANTS.n; line++)
			for (int row = 0; row < CONSTANTS.n; row++) {
				double sum = 0;
				for (int i = 0; i < CONSTANTS.n; i++)
					sum += m[line][i] * t.m[i][row];
				temp[line][row] = sum;
			}
		this.m = temp;
		return this;
	}

	/*
	 * [new] = [this] x [t]
	 */
	public AffineTransformND mult(AffineTransformND t) {

		double newM[][] = new double[CONSTANTS.n][CONSTANTS.n];

		for (int line = 0; line < CONSTANTS.n; line++)
			for (int row = 0; row < CONSTANTS.n; row++) {
				double sum = 0;
				for (int i = 0; i < CONSTANTS.n; i++)
					sum += m[line][i] * t.m[i][row];
				newM[line][row] = sum;
			}
		AffineTransformND temp = new AffineTransformND();
		temp.m = newM;
		return temp;
	}

	/*
	 * Example for rotation instance for rotation IN x2 & x4 plane in R^4
	 * 
	 * [ 1 0 0 0 ] [x1] = [ x1 ] [ 0 cos 0 -sin ] [x2] = [x2 * cos - x4 * sin ]
	 * [ 0 0 1 0 ] [x3] = [ x3 ] [ 0 sin 0 cos ] [x4] = [x2 * sin + x4 * cos ]
	 */
	public static AffineTransformND getRotation_i_j(int i, int j, double theta) {
		AffineTransformND rotation = new AffineTransformND();
		rotation.m[i][i] = 0;
		rotation.m[j][j] = 0;
		rotation.m[i][i] = +Math.cos(theta);
		rotation.m[i][j] = -Math.sin(theta);
		rotation.m[j][i] = +Math.sin(theta);
		rotation.m[j][j] = +Math.cos(theta);
		return rotation;
	}

	/*
	 * returns with this transformation transformed point
	 */
	public SimplePointND transform(SimplePointND p) {
		SimplePointND newPoint = new SimplePointND();
		for (int line = 0; line < CONSTANTS.n; line++) {
			double sum = 0;
			for (int i = 0; i < CONSTANTS.n; i++)
				sum += this.m[line][i] * p.getXi(i);
			newPoint.setXi(line, sum);
		}
		return newPoint;
	}

}
