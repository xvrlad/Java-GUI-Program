/*
 *  ============================================================================================
 *  Text version of a painter
 *  YOUR UPI: xlad198
 *  ============================================================================================
 */
import java.awt.*;
class MockPainter implements Painter {
    public MockPainter() {}
	@Override
	public void setPaint(Color color) { System.out.printf("Color: %s\n", color.toString());}
	@Override
	public void setGraphics(Graphics g) {}
	@Override
	public void drawHandles(boolean isSelected,  int x, int y, int width, int height) {
		System.out.printf("selected: %b\n", isSelected);
	}
	public void drawRect(int x, int y, int width, int height) {
		System.out.printf("Draw Rectangle (%d, %d) %d x %d\n", x, y, width, height);
	}

	public void fillRect(int x, int y, int width, int height) {
		System.out.printf("Fill Rectangle (%d, %d) %d x %d\n", x, y, width, height);
	}

	@Override
	public void drawLine(int x1, int y1, int x2, int y2) {
		System.out.printf("Draw Line (%d, %d) to (%d, %d)\n", x1, y1, x2, y2);
	}

	@Override
	public void drawOval(int x1, int y1, int width, int height) {
		System.out.printf("Draw Oval (%d, %d) %d x %d\n", x1, y1, width, height);
	}

	@Override
	public void fillOval(int x1, int y1, int width, int height) {
		System.out.printf("Fill Oval (%d, %d) %d x %d\n", x1, y1, width, height);
	}
	@Override
	public void drawImage(Image img, int x, int y, int width, int height) {
		System.out.printf("Draw Image (%d, %d) %d x %d\n", x, y, width, height);
	}
}