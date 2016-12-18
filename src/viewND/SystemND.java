package viewND;

import java.awt.BasicStroke;

public class SystemND extends ObjectND {

	public SystemND(String label) {
		super(label);
		PointND origin = new PointND();
		for (int i = 0; i < CONSTANTS.n; i++) {
			PointND end_i = new PointND();
			end_i.setXi(i, CONSTANTS.SYSTEM_SIZE);
			end_i.setLabel("x_" + i);
			LineND l_i = new LineND(origin, end_i);
			l_i.setColor(CONSTANTS.color[i]);
			l_i.setStroke(new BasicStroke(2));
			add(l_i);
		}
	}
}
