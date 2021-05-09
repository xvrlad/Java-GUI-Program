/*
 *	===============================================================================
 *	DynamicRectangleShape.java : A shape that is a dynamic rectangle.
 *  YOUR UPI: xlad198
 *	=============================================================================== */
import java.awt.*;

class DynamicRectangleShape extends RectangleShape {
    DynamicRectangleShape() {
        super();
    }

    DynamicRectangleShape(int x, int y, int w, int h, int mw, int mh, Color bc, Color fc, PathType pt) {
        super(x, y, w, h, mw, mh, bc, fc, pt);
    }

    public void draw(Painter g) {
        if (this.path.deltaY < 0) {
            g.setPaint(borderColor);
            g.fillRect(x, y, width, height);
            g.setPaint(fillColor);
            g.drawRect(x, y, width, height);
        } else {
            super.draw(g);
        }
    }
}