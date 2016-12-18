package viewND;

import java.awt.Color;
import java.awt.Dimension;

public class CONSTANTS {
	
	
	//Dimension for R^n
	public static int n = 3;
	
	public static int n_over_2(int n) { return n * (n-1) / 2; }
	
	public static final double DELTA =1.0E-15;

	// Size for ViewNDPaint
	public final static int PAINT_PANEL_X = 700;
	public final static int PAINT_PANEL_Y = 700;
	// Size for ViewNDControl
	public final static int CONTROL_PANEL_X = 400;
	public final static int CONTROL_PANEL_Y = 400;
	
	// Center of Coordinates System
	public final static int CENTER_X = 5 * PAINT_PANEL_X/12;
	public final static int CENTER_Y = 5 * PAINT_PANEL_Y/12;
	// Length of Coordinates System
	//public final static double COORDINATES_UNITS = 2.1;
	
	// Constants to identify Rotations in Arrays
	public final static int SYSTEM = 0;
	public final static int CUBUS = 1;
	// Size System
	public static final int SYSTEM_SIZE = 300;
	
	// Offset for label in AbstaractObjectND in pixel
	public static final int LABEL_OFFSET_X = 3;
	public static final int LABEL_OFFSET_Y = 3;

	// Size  Cube
	public final static int CUBUS_SIZE = SYSTEM_SIZE/2;
	
	public static Color[] color = {	Color.RED, 
									Color.GREEN, 
									Color.BLUE, 
									Color.ORANGE,
									Color.CYAN,
									Color.MAGENTA, 
									Color.YELLOW,
									Color.DARK_GRAY}; 
	
	public static final String FONTTYPE = "serif";
	public static final int FONTSIZE = 11;
	public static final int CONTROLSHIGHT = 15;
	public static final Dimension TEXTFIELD21 =  new Dimension(170,CONTROLSHIGHT);
			
	// Labels
	public final static String labelCLEAR = "Clear";
	public final static String labelITERATOR = "Start";
	public final static String labelBRUTALFORCE2 = "BF2";

	

}
