package viewND.nd.compositum;

import java.awt.BasicStroke;

import viewND.CONSTANTS;
import viewND.nd.InterfacePaintAbleND;
import viewND.nd.primitive.SimpleLineND;
import viewND.nd.primitive.SimplePointND;

public class SystemND extends CompositeND implements InterfacePaintAbleND {

	public SystemND(String label) {
		super(label);
		SimplePointND origin = new SimplePointND();
		for (int i = 0; i < CONSTANTS.n; i++) {
			SimplePointND end_i = new SimplePointND();
			end_i.setXi(i, CONSTANTS.SYSTEM_SIZE);
			end_i.setLabel("x_" + i);
			SimpleLineND l_i = new SimpleLineND(origin, end_i);
			l_i.setColor(CONSTANTS.color[i]);
			l_i.setStroke(new BasicStroke(2));
			add(l_i);
		}
	}
}
