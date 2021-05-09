/*
 *  ============================================================================================
 *  enum which defines the type of shapes in A1
 *  YOUR UPI: xlad198
 *  ============================================================================================
 */
import java.util.*;
enum ShapeType { RECTANGLE, XRECTANGLE, SQUARE, OVAL, DYNAMIC, IMAGE;
	private static final Random rand = new Random();
	private static final int SIZE = values().length;
	public static final ShapeType getShapeType(int index) { return values()[index]; }
	public ShapeType next() {
		return values()[(ordinal() + 1) % values().length];
		}
	public static ShapeType getRandomShapeType()  {
	    return values()[rand.nextInt(SIZE)];
  }
}