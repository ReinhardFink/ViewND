package viewND;

public class CubeND extends ObjectND {
	
	private int numberOfPoints;
	private PointND[] pointND;

	public	CubeND(String label) {
		numberOfPoints = (int) Math.pow(2.0, CONSTANTS.n);
		createPoints();
		createLines();
	}
	
	public void createPoints() {
		pointND = new PointND[numberOfPoints];
		for (int i = 0; i < numberOfPoints; i++) {
			// System.out.print(i + ": ");
			pointND[i] = new PointND();
			int pos = 0;
			int dividend = i;
			while (dividend > 0) {
				pointND[i].setXi(pos, (dividend % 2) * CONSTANTS.CUBUS_SIZE);
				// System.out.print(digitalPointArray[i][pos]);
				dividend /= 2;
				pos++;
			}
		}
	}

	private void createLines() {
		for (int first = 0; first < numberOfPoints - 1; first++)
			for (int second = first + 1; second < numberOfPoints; second++) {
				int firstXORSecond = first ^ second;
				//System.out.println(first + " : " + second + " -> " + firstXORSecond);
				switch (firstXORSecond) {
				case 1:
					this.add(new LineND(pointND[first],pointND[second],CONSTANTS.color[0]));
					break;
				case 2:
					this.add(new LineND(pointND[first],pointND[second],CONSTANTS.color[1]));
					break;
				case 4:
					this.add(new LineND(pointND[first],pointND[second],CONSTANTS.color[2]));
					break;
				case 8:
					this.add(new LineND(pointND[first],pointND[second],CONSTANTS.color[3]));
					break;
				case 16:
					this.add(new LineND(pointND[first],pointND[second],CONSTANTS.color[4]));
					break;
				}
			}
	}
}
