/*
 *    ==========================================================================================
 *    AnimationViewer.java : Moves shapes around on the screen according to different paths.
 *    It is the main drawing area where shapes are added and manipulated.
 *    YOUR UPI: xlad198
 *    ==========================================================================================
 */

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

public class AnimationViewer extends JComponent implements Runnable {
	private Thread animationThread = null;    // the thread for animation
    private static int DELAY = 20;         // the current animation speed
	private ArrayList<Shape> shapes = new ArrayList<Shape>(); //create the ArrayList to store shapes
	private Painter painter = new GraphicsPainter();
	private ShapeType currentShapeType=Shape.DEFAULT_SHAPETYPE; // the current shape type,
	private PathType currentPathType=Shape.DEFAULT_PATHTYPE;  // the current path type
	private Color currentBorderColor=Shape.DEFAULT_BORDER_COLOR, currentFillColor=Shape.DEFAULT_FILL_COLOR;  // the current fill colour of a shape
	private int marginWidth=Shape.DEFAULT_MARGIN_WIDTH, marginHeight = Shape.DEFAULT_MARGIN_HEIGHT, currentWidth=Shape.DEFAULT_WIDTH, currentHeight=Shape.DEFAULT_HEIGHT;
	private String currentImageFileName = Shape.DEFAULT_IMAGE_FILENAME;
	/** Constructor of the AnimationPanel */
    public AnimationViewer(boolean isGraphicsVersion) {
		if (isGraphicsVersion) {
			start();
			addMouseListener(new MyMouseAdapter());
		}
	}

    /** create a new shape
     * @param x     the x-coordinate of the mouse position
     * @param y    the y-coordinate of the mouse position */
    protected void createNewShape(int x, int y) {
		switch (currentShapeType) {
			case RECTANGLE: {
				shapes.add( new RectangleShape(x, y,currentWidth,currentHeight,marginWidth,marginHeight,currentBorderColor,currentFillColor,currentPathType ) );
				break;
			} case XRECTANGLE: {
				shapes.add(new XRectangleShape(x,y,currentWidth,currentHeight,marginWidth,marginHeight,currentBorderColor,currentFillColor,currentPathType));
				break;
		   	} case OVAL: {
			  	shapes.add(new OvalShape(x,y,currentWidth,currentHeight,marginWidth,marginHeight,currentBorderColor,currentFillColor,currentPathType));
			  	break;
		   	} case SQUARE: {
			  	shapes.add(new SquareShape(x,y,Math.min(currentWidth,currentHeight),marginWidth,marginHeight,currentBorderColor,currentFillColor,currentPathType));
			  	break;
		    }
			case DYNAMIC: {
				shapes.add(new DynamicRectangleShape(x, y, currentWidth,currentHeight,marginWidth,marginHeight,currentBorderColor,currentFillColor,currentPathType));
				break;
			}
			case IMAGE: {
				shapes.add(new ImageRectangleShape(x, y, currentWidth,currentHeight,marginWidth,marginHeight,currentBorderColor,currentFillColor,currentPathType, currentImageFileName));
				break;
			}
	   }
    }
    /**    move and paint all shapes within the animation area
     * @param g    the Graphics control */
    public void paintComponent(Graphics g) {
		((GraphicsPainter)painter).setGraphics(g);
		super.paintComponent(g);
        for (Shape currentShape: shapes) {
            currentShape.move();
		    currentShape.draw(painter);
		    currentShape.drawHandles(painter);
		}
    }
    // add a list of set methods:



// you don't need to make any changes after this line ______________
	/** get the current width
	 * @return currentWidth - the width value */
	public int getCurrentWidth() { return currentWidth; }
	/** get the current height
	 * @return currentHeight - the height value */
	public int getCurrentHeight() { return currentHeight; }
	/** get the current fill colour
	 * @return currentFillColor - the fill colour value */
	public Color getCurrentFillColor() { return currentFillColor; }
	/** get the current border colour
	 * @return currentBorderColor - the border colour value */
	public Color getCurrentBorderColor() { return currentBorderColor; }

	/* Inner member class for mouse event handling */
    class MyMouseAdapter extends MouseAdapter {
		public void mouseClicked( MouseEvent e ) {
			boolean found = false;
			for (Shape currentShape: shapes)
				if ( currentShape.contains( e.getPoint()) ) { // if the mousepoint is within a shape, then set the shape to be selected/deselected
					currentShape.setSelected( ! currentShape.isSelected() );
					found = true;
				}
			if (!found) createNewShape(e.getX(), e.getY());
		}
	}
    /**    update the painting area
     * @param g    the graphics control */
    public void update(Graphics g){ paint(g); }
    /** reset the margin size of all shapes from our ArrayList */
    public void resetMarginSize() {
        marginWidth = getWidth();
        marginHeight = getHeight() ;
        for (Shape currentShape: shapes)
			currentShape.setMarginSize(marginWidth,marginHeight );
    }
  public void start() {
        animationThread = new Thread(this);
        animationThread.start();
    }
    public void stop() {
        if (animationThread != null) {
            animationThread = null;
        }
    }
    public void run() {
        Thread myThread = Thread.currentThread();
        while(animationThread==myThread) {
            repaint();
            pause(DELAY);
        }
    }
    private void pause(int milliseconds) {
        try {
            Thread.sleep((long)milliseconds);
        } catch(InterruptedException ie) {}
    }

    public ShapeType getCurrentShapeType() {
    	return this.currentShapeType;
	}

	public PathType getCurrentPathType() {
    	return this.currentPathType;
	}

	public void setCurrentShapeType(int index) {
    	this.currentShapeType = ShapeType.getShapeType(index);
	}

	public void setCurrentPathType(int index) {
    	this.currentPathType = PathType.getPathType(index);
	}

	public void setCurrentWidth(int w) {
    	this.currentWidth = w;
    	for (Shape shape : shapes) {
    		if (shape.isSelected()) {
    			shape.setWidth(w);
			}
		}
	}

	public void setCurrentHeight(int h) {
    	this.currentHeight = h;
		for (Shape shape : shapes) {
			if (shape.isSelected()) {
				shape.setHeight(h);
			}
		}
	}

	public void setCurrentFillColor(Color fc) {
    	this.currentFillColor = fc;
    	for (Shape shape : shapes) {
    		if (shape.isSelected()) {
    			shape.setFillColor(fc);
			}
		}
	}

	public void setCurrentBorderColor(Color bc) {
    	this.currentBorderColor = bc;
    	for (Shape shape : shapes) {
    		if (shape.isSelected()) {
    			shape.setBorderColor(bc);
			}
		}
	}
}
