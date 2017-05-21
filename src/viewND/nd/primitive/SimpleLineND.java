package viewND.nd.primitive;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import viewND.CONSTANTS;
import viewND.nd.AffineTransformND;
import viewND.nd.InterfacePaintAbleND;

public class SimpleLineND extends SimpleAbstractND implements InterfacePaintAbleND {
	
	private SimplePointND startPoint;
	private SimplePointND endPoint;
	
	public SimpleLineND(SimplePointND startPoint, SimplePointND endPoint) {
		super();
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	
	public SimpleLineND(SimplePointND startPoint, SimplePointND endPoint, Color color) {
		this(startPoint, endPoint);
		setColor(color);
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(startPoint);
		buffer.append(" -> ");
		buffer.append(endPoint);
		buffer.append('\n');
		return buffer.toString();
	}

	@Override
	public void paint(Graphics2D g2, AffineTransformND affin) {
		// set basic draw options
		super.paint(g2, affin);
		SimpleLineND l = (SimpleLineND)getTransformedObjectND(affin);		
		g2.draw(new Line2D.Double(l.startPoint.getXi(0),l.startPoint.getXi(1),
								  l.endPoint.getXi(0),  l.endPoint.getXi(1)));
		
		if (startPoint.getLabel() != null) 
			g2.drawString(startPoint.getLabel(), (int)l.startPoint.getXi(0) + CONSTANTS.LABEL_OFFSET_X, 
												 (int)l.startPoint.getXi(1) + CONSTANTS.LABEL_OFFSET_Y);
		if (endPoint.getLabel() != null) 
			g2.drawString(endPoint.getLabel(), (int)l.endPoint.getXi(0) + CONSTANTS.LABEL_OFFSET_X,
					   						   (int)l.endPoint.getXi(1) + CONSTANTS.LABEL_OFFSET_Y);
		
	}

	@Override
	public InterfacePaintAbleND getTransformedObjectND(AffineTransformND transform) {
		return (new SimpleLineND(transform.transform(this.startPoint),
				  		   transform.transform(this.endPoint)));
	}
}