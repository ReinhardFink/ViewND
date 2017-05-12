package viewND.gui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import viewND.CONSTANTS;

@SuppressWarnings("serial")
public class ViewND extends JPanel {
	
	private JPanel controlPanel;
	private ViewNDPaint viewNDPaint;
	private ViewNDSliderControl viewNDSliderControl;

	public ViewND() {
		super();
		this.setLayout(new BorderLayout());
		this.setSize(CONSTANTS.PAINT_PANEL_X + CONSTANTS.CONTROL_PANEL_X, CONSTANTS.PAINT_PANEL_Y);
		
		this.viewNDPaint = new ViewNDPaint();
		this.add(viewNDPaint,BorderLayout.CENTER);
		
		controlPanel = new JPanel(new BorderLayout());
		//controlPanel.setSize(CONSTANTS.CONTROL_PANEL_X, CONSTANTS.CONTROL_PANEL_Y);
		controlPanel.add(createDimensionConrol(),BorderLayout.NORTH);
		
		viewNDSliderControl = new ViewNDSliderControl(viewNDPaint);
		
		controlPanel.add(viewNDSliderControl,BorderLayout.CENTER);
		
		this.add(controlPanel,BorderLayout.EAST);
	}

	private JPanel createDimensionConrol() {
		JPanel topLabel = new JPanel();
		topLabel.add(new JLabel("Dimension Coordinates System, n = "));
		
		JTextField dimension = new JTextField(new Integer(CONSTANTS.n).toString());
		
		dimension.addActionListener(e -> {
			CONSTANTS.n = new Integer(dimension.getText());
			
			ViewND.this.remove(viewNDPaint);
			ViewND.this.controlPanel.remove(viewNDSliderControl);
			
			viewNDPaint = new ViewNDPaint();
			ViewND.this.add(viewNDPaint,BorderLayout.CENTER);
			
			viewNDSliderControl = new ViewNDSliderControl(viewNDPaint);
			controlPanel.add(viewNDSliderControl,BorderLayout.CENTER);
			controlPanel.validate();
			//controlPanel.repaint();
			
			ViewND.this.repaint();
		});
		
		topLabel.add(dimension);
		return topLabel;
	}
}
