package viewND;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class LineND extends AbstractObjectND {
	
	private PointND startPoint;
	private PointND endPoint;
	
	public LineND(PointND startPoint, PointND endPoint) {
		super();
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	
	public LineND(PointND startPoint, PointND endPoint, Color color) {
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
	public AbstractObjectND getTransformedObjectND(AffineTransformND transform) {
		return (new LineND(transform.transformPointND(this.startPoint),
				  		   transform.transformPointND(this.endPoint)));
	}

	@Override
	public void paint(Graphics2D g2, AffineTransformND affin) {
		// set basic draw options
		super.paint(g2, affin);
		LineND l = (LineND)getTransformedObjectND(affin);		
		g2.draw(new Line2D.Double(l.startPoint.getXi(0),l.startPoint.getXi(1),
								  l.endPoint.getXi(0),  l.endPoint.getXi(1)));
		
		if (startPoint.getLabel() != null) 
			g2.drawString(startPoint.getLabel(), (int)l.startPoint.getXi(0) + CONSTANTS.LABEL_OFFSET_X, 
												 (int)l.startPoint.getXi(1) + CONSTANTS.LABEL_OFFSET_Y);
		if (endPoint.getLabel() != null) 
			g2.drawString(endPoint.getLabel(), (int)l.endPoint.getXi(0) + CONSTANTS.LABEL_OFFSET_X,
					   						   (int)l.endPoint.getXi(1) + CONSTANTS.LABEL_OFFSET_Y);
		
	}
}