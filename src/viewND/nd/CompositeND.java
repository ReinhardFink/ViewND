package viewND.nd;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class CompositeND implements InterfaceND {
	
	private ArrayList<SimpleAbstractND> objectsND;
	
	private String lable;
	
	public CompositeND() {
		this.objectsND = new ArrayList<SimpleAbstractND>();
	}
	
	public CompositeND(String lable) {
		this.objectsND = new ArrayList<SimpleAbstractND>();
		this.lable = lable;
	}
	
	@Override
	public void setLabel(String lable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}

	public void add(SimpleAbstractND objectND) {
		objectsND.add(objectND);
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		if (lable != null)
			buffer.append(lable + "\n");
		for (SimpleAbstractND aOND : objectsND)
			buffer.append(aOND.toString());
		buffer.append('\n');
		return buffer.toString();
	}
	
	public void paint(Graphics2D g2, AffineTransformND affin) {
		for (SimpleAbstractND aOND : objectsND)
			aOND.paint(g2, affin);
	}
}
