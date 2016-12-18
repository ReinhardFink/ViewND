package viewND;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import viewND.JNumberSlider;

@SuppressWarnings("serial")
public class ViewNDSliderControl extends JScrollPane {

	private ViewNDPaint viewNDPaint;

	private JPanel controlPanel;

	private JNumberSlider[][][] slidersForRotation;

	public ViewNDSliderControl(ViewNDPaint viewNDPaint) {
		super();
;		this.viewNDPaint = viewNDPaint;
		controlPanel = new JPanel(new GridLayout(2 * CONSTANTS.n_over_2(CONSTANTS.n) + 3, 1));
		slidersForRotation = new JNumberSlider[2][CONSTANTS.n][CONSTANTS.n];
		controlPanel.add(new JLabel("Rotation Coordinates System"));
		createSliders(CONSTANTS.SYSTEM);
		controlPanel.add(new JLabel("Rotation Cubus"));
		createSliders(CONSTANTS.CUBUS);
		controlPanel.add(createResetButton());
		//JScrollPane scrollpane = new JScrollPane(controlPanel);
		//scrollpane.setViewportView(controlPanel);
	    //this.add(scrollpane);
	    this.setViewportView(controlPanel);
	}

	public ViewNDPaint getViewNDPaint() {
		return viewNDPaint;
	}

	private void createSliders(final int index) {
		for (int first = 0; first < CONSTANTS.n - 1; first++)
			for (int second = first + 1; second < CONSTANTS.n; second++) {
				JNumberSlider slider = null;
				// System.out.println(first + " " + second);
				slider = new JNumberSlider(true, "Rotation " + first + " " + second, -180, 180, 0) {
					public void onChange() {
						AffineTransformND affinND = new AffineTransformND();
						for (int i = 0; i < CONSTANTS.n - 1; i++)
							for (int j = i + 1; j < CONSTANTS.n; j++) {
								affinND.concatenate(AffineTransformND.getRotation_i_j(i, j, 2 * Math.PI / 360
										* slidersForRotation[index][i][j].getNumber()));
								//System.out.println("index: " + index + " : " + i + " : " + j);
							}
						viewNDPaint.setAffND(index, affinND);
						//System.out.println("index: " + index);
						viewNDPaint.repaint();
					}
				};
				slidersForRotation[index][first][second] = slider;
				controlPanel.add(slider);
			}
	}

	private JButton createResetButton() {
		JButton reset = new JButton("Reset");
		reset.addActionListener(e -> {
			for (int index = 0; index < 2; index++)
				for (int first = 0; first < CONSTANTS.n - 1; first++)
					for (int second = first + 1; second < CONSTANTS.n; second++)
						slidersForRotation[index][first][second].reset(-180, 180, 0);
			viewNDPaint.setAffND(0, new AffineTransformND());
			viewNDPaint.setAffND(1, new AffineTransformND());
			viewNDPaint.repaint();
		});
		return reset;
	}
}
