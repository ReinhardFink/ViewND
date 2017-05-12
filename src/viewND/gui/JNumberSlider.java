package viewND.gui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import viewND.CONSTANTS;

public class JNumberSlider extends JPanel {

	private static final long serialVersionUID = 3659791998872082572L;
	private JTextField numberField;
	private JSlider numberSlider;
	private double min;
	private double max;
	private double number;

	public JNumberSlider(boolean textFieldFirst, String label,
			double min, double max, double number) {
		this.min = min;
		this.max = max;
		this.number = getCheckedNumber(number);
		this.createGUI(textFieldFirst, label);
		
	}

	public double getNumber() {
		return this.number;
	}

	/**
	 * Override this Method
	 */
	public void onChange() {
	};

	public void reset(double min, double max, double number) {
		this.min = min;
		this.max = max;
		this.number = getCheckedNumber(number);
		numberField.setText(new Double(number).toString());
		numberSlider.setValue(getPercentageOfRange());
	}

	/** Set NumberTextField to Double in Range, otherwise keep old Number */
	private double getCheckedNumber(double number) {
		if (number < min || number > max) {
			number = (max - min) / 2;
			System.out.println("ERROR: number out of Range => set to (max-min)/2");
		}
		return number;
	}

	private int getPercentageOfRange() {
		return (int) Math.round((number - min) / (max - min) * 100);
	}
	
	private int getPercentageOfSlider() {
		return (int) Math.round(((double)numberSlider.getValue() - numberSlider.getMinimum()) /
				(numberSlider.getMaximum() - numberSlider.getMinimum()) * 100);
	}

	
	private void createGUI(boolean textFieldFirst, String label) {
		this.setLayout(new GridLayout(2, 1));
		JPanel panelForLabelAndTextField = new JPanel();
		JLabel jlabel = new JLabel(label);
		jlabel.setFont(new Font(CONSTANTS.FONTTYPE, Font.PLAIN,
				CONSTANTS.FONTSIZE));
		panelForLabelAndTextField.add(jlabel);
		this.numberField = new JTextField(new Double(number).toString());
		this.numberField.setPreferredSize(CONSTANTS.TEXTFIELD21);
		this.numberField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					try {
						number = new Double(numberField.getText())
								.doubleValue();
					} catch (NumberFormatException NFE) {
						numberField.setText(new Double(number)
								.toString());
						return;
					}
					if (getPercentageOfRange() != getPercentageOfSlider()) {
						numberSlider.setValue(getPercentageOfRange());
						onChange();
					}
				}
			}
		});
		panelForLabelAndTextField.add(numberField);
		
		this.numberSlider = new JSlider();
		this.numberSlider.setValue(getPercentageOfRange());
		this.numberSlider.addChangeListener(e -> {
			if (getPercentageOfRange() != getPercentageOfSlider()) {
				number = min + (max - min) * (double)getPercentageOfSlider() / 100;
				numberField.setText(new Double(number).toString());
				onChange();
			}
		});
		
		if (textFieldFirst == true) {
			this.add(panelForLabelAndTextField);
			this.add(this.numberSlider);
		} else {
			this.add(this.numberSlider);
			this.add(panelForLabelAndTextField);
		}
	}

}
