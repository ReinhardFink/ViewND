package viewND.nd;

import java.awt.Graphics2D;

public interface InterfaceND {
	
	public void setLabel(String lable);

	public String getLabel();
	
	public String toString();

	public void paint(Graphics2D g2, AffineTransformND affin);

}
