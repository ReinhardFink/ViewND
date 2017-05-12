package viewND.nd;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public abstract class SimpleAbstractND implements InterfaceND {
	
	private Color color;
	private BasicStroke stroke;
	private String label;
	
	public SimpleAbstractND(Color color, BasicStroke stroke) {
		this.color = color;
		this.stroke = stroke;
	}
	
	public SimpleAbstractND() {
		this(Color.BLACK, null);
		//this.stroke = new BasicStroke((float)1/CONSTANTS.SCALE_FACTOR);
		this.stroke = new BasicStroke(1);
	}
	
	public void setLabel(String lable) {
		this.label = lable;
	}

	public String getLabel() {
		return label;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setStroke(BasicStroke stroke) {
		this.stroke = stroke;
	}

	public BasicStroke getStroke() {
		return stroke;
	}

	public Color getColor() {
		return color;
	}

	public void paint(Graphics2D g2, AffineTransformND affin) {
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(stroke);
		g2.setColor(color);
	}
	
	public abstract InterfaceND getTransformedObjectND(AffineTransformND affin);
}