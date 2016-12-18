package viewND;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class ViewNDPaint extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private ObjectND coordinateSystemND;
	private ObjectND cubusND;
	private AffineTransformND[] affinTransformationND;

	public ViewNDPaint() {
		super();
		this.setSize(CONSTANTS.PAINT_PANEL_X, CONSTANTS.PAINT_PANEL_Y);
		coordinateSystemND = new SystemND("Koordinates - System with dimension" + CONSTANTS.n);
		cubusND = new CubeND("Cubus with dimension" + CONSTANTS.n);
		affinTransformationND = new AffineTransformND[2];
		affinTransformationND[0] = new AffineTransformND();
		affinTransformationND[1] = new AffineTransformND();
	}

	public void setAffND(int index, AffineTransformND affND) {
		this.affinTransformationND[index] = affND;
	}

	public void paint(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.translate(CONSTANTS.CENTER_X,CONSTANTS.CENTER_Y);
		//g2.scale(CONSTANTS.SCALE_FACTOR,CONSTANTS.SCALE_FACTOR);
		
		coordinateSystemND.paint(g2, affinTransformationND[CONSTANTS.SYSTEM]);
		
		cubusND.paint(g2, affinTransformationND[CONSTANTS.SYSTEM].mult(affinTransformationND[CONSTANTS.CUBUS]));
	}
}
