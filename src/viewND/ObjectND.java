package viewND;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class ObjectND {
	
	private ArrayList<AbstractObjectND> objectsND;
	
	private String lable;
	
	public ObjectND() {
		this.objectsND = new ArrayList<AbstractObjectND>();
	}
	
	public ObjectND(String lable) {
		this.objectsND = new ArrayList<AbstractObjectND>();
		this.lable = lable;
	}
	
	public void add(AbstractObjectND objectND) {
		objectsND.add(objectND);
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		if (lable != null)
			buffer.append(lable + "\n");
		for (AbstractObjectND aOND : objectsND)
			buffer.append(aOND.toString());
		buffer.append('\n');
		return buffer.toString();
	}
	
	public void paint(Graphics2D g2, AffineTransformND affin) {
		for (AbstractObjectND aOND : objectsND)
			aOND.paint(g2, affin);
	}
}
