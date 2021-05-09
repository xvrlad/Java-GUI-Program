/*
 *	===============================================================================
 *	ImageRectangleShape.java : A shape that is an image rectangle.
 *  YOUR UPI: xlad198
 *	=============================================================================== */

import java.awt.*;

class ImageRectangleShape extends RectangleShape {
    private Image image;
    private String imageFilename;

    ImageRectangleShape(String filename) {
        super();
        this.imageFilename = filename;
        this.loadImage(this.imageFilename);
    }
    ImageRectangleShape(int x, int y, int w, int h, int mw, int mh, Color bc, Color fc, PathType pt, String imageFilename) {
        super(x, y, w, h, mw, mh, bc, fc, pt);
        this.imageFilename = imageFilename;
        this.loadImage(this.imageFilename);
    }

    public void loadImage(String filename) {
        this.image = Toolkit.getDefaultToolkit().createImage(A2.class.getResource(filename));
    }

    public void draw(Painter g) {
        g.drawImage(this.image, this.x, this.y, this.width, this.height);
        g.setPaint(borderColor);
        g.drawRect(x, y, width, height);
    }
}