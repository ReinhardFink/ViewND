package viewND.nd.primitive;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.text.DecimalFormat;

import viewND.CONSTANTS;
import viewND.nd.AffineTransformND;
import viewND.nd.InterfacePaintAbleND;

public class SimplePointND extends SimpleAbstractND implements InterfacePaintAbleND {
	
	private double[] x;
	
	private double rX;
	private double rY;
	
	public SimplePointND() {
		this.x = new double[CONSTANTS.n];
				
		this.rX = 30;
		this.rY = 30;
	}
	
	public void setXi(int i, double x_i) {
		x[i] = x_i;
	}
	
	public double getXi(int i) {
		return x[i];
	}
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		for (double x_i : x) {
			temp = Double.doubleToLongBits(x_i);
			result = prime * result + (int) (temp ^ (temp >>> 32));
		}
		return result;
	}

	@Override
	public boolean equals(Object o) {
		SimplePointND p = (SimplePointND)o;
		if (this == p)
			return true;
		if (p == null)
			return false;
		boolean b = true;
		for (int i = 0; i < CONSTANTS.n; i++) {
			if (Double.doubleToLongBits(x[i]) != Double.doubleToLongBits(p.getXi(i)))
				b = false;
		}
		return b;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append('(');
		for (int i = 0; i < CONSTANTS.n; i++) {
				buffer.append(new DecimalFormat("+#,##0.00;-#").format(x[i]));
				if (i < CONSTANTS.n - 1)
					buffer.append("|");
				else
					buffer.append(')');
			}
		if (getLabel() != null) {
			buffer.append(" with lable: ");
			buffer.append(getLabel());
		}
		return buffer.toString();
	}
	
	@Override
	public void paint(Graphics2D g2, AffineTransformND affin) {
		// set basic draw options
		super.paint(g2, affin);
		// Projection to x1/x2 planeAffineTransformND
		SimplePointND p = (SimplePointND)getTransformedObjectND(affin);
		//g2.draw(new Ellipse2D.Double(p.getXi(0), p.getXi(1), this.rX/CONSTANTS.SCALE_FACTOR, this.rY/CONSTANTS.SCALE_FACTOR));
		g2.draw(new Ellipse2D.Double(p.getXi(0), p.getXi(1), this.rX, this.rY));
		if (getLabel() != null)
			g2.drawString(getLabel(), (int)p.getXi(0) + CONSTANTS.LABEL_OFFSET_X, 
									  (int)p.getXi(1) + CONSTANTS.LABEL_OFFSET_Y);
	}

	@Override
	public InterfacePaintAbleND getTransformedObjectND(AffineTransformND affin) {
		return affin.transform(this);
	}

}
