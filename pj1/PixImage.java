/* PixImage.java */

/**
 *  The PixImage class represents an image, which is a rectangular grid of
 *  color pixels.  Each pixel has red, green, and blue intensities in the range
 *  0...255.  Descriptions of the methods you must implement appear below.
 *  They include a constructor of the form
 *
 *      public PixImage(int width, int height);
 *
 *  that creates a black (zero intensity) image of the specified width and
 *  height.  Pixels are numbered in the range (0...width - 1, 0...height - 1).
 *
 *  All methods in this class must be implemented to complete Part I.
 *  See the README file accompanying this project for additional details.
 */

public class PixImage {

    /**
     *  Define any variables associated with a PixImage object here.  These
     *  variables MUST be private.
     */

    private int width;
    private int height;
    private short[][][] pixel;


    /**
     * PixImage() constructs an empty PixImage with a specified width and height.
     * Every pixel has red, green, and blue intensities of zero (solid black).
     *
     * @param width the width of the image.
     * @param height the height of the image.
     */
    public PixImage(int width, int height) {
        this.width = width;
        this.height = height;
        pixel = new short[width][height][3];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                for (int k = 0; k < 3; k++) {
                    pixel[i][j][k] = 0;
                }
            }
        }
    }

    /**
     * getWidth() returns the width of the image.
     *
     * @return the width of the image.
     */
    public int getWidth() {
        return width;
    }

    /**
     * getHeight() returns the height of the image.
     *
     * @return the height of the image.
     */
    public int getHeight() {
        return height;
    }

    /**
     * getRed() returns the red intensity of the pixel at coordinate (x, y).
     *
     * @param x the x-coordinate of the pixel.
     * @param y the y-coordinate of the pixel.
     * @return the red intensity of the pixel at coordinate (x, y).
     */
    public short getRed(int x, int y) {
        return pixel[x][y][0];
    }

    /**
     * getGreen() returns the green intensity of the pixel at coordinate (x, y).
     *
     * @param x the x-coordinate of the pixel.
     * @param y the y-coordinate of the pixel.
     * @return the green intensity of the pixel at coordinate (x, y).
     */
    public short getGreen(int x, int y) {
        return pixel[x][y][1];
    }

    /**
     * getBlue() returns the blue intensity of the pixel at coordinate (x, y).
     *
     * @param x the x-coordinate of the pixel.
     * @param y the y-coordinate of the pixel.
     * @return the blue intensity of the pixel at coordinate (x, y).
     */
    public short getBlue(int x, int y) {
        return pixel[x][y][2];
    }

    /**
     * setPixel() sets the pixel at coordinate (x, y) to specified red, green,
     * and blue intensities.
     *
     * If any of the three color intensities is NOT in the range 0...255, then
     * this method does NOT change any of the pixel intensities.
     *
     * @param x the x-coordinate of the pixel.
     * @param y the y-coordinate of the pixel.
     * @param red the new red intensity for the pixel at coordinate (x, y).
     * @param green the new green intensity for the pixel at coordinate (x, y).
     * @param blue the new blue intensity for the pixel at coordinate (x, y).
     */
    public void setPixel(int x, int y, short red, short green, short blue) {
        if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255);
            else {pixel[x][y][0] = red; pixel[x][y][1] = green; pixel[x][y][2] = blue;
        }
    }

    /**
     * toString() returns a String representation of this PixImage.
     *
     * This method isn't required, but it should be very useful to you when
     * you're debugging your code.  It's up to you how you represent a PixImage
     * as a String.
     *
     * @return a String representation of this PixImage.
     */
    public String toString() {
        String s = "";
        for (int j = 0; j < height; j++) {
            for (int i =0; i < width; i++) {
                s = s + "[" + i + "," + j + "]:";
                for (int k = 0; k < 3; k++) {
                    s = s + pixel[i][j][k] + " ";
                }
                s = s + "; ";
                if (i == width - 1) {
                    s += '\n';
                }
            }
        }
        return s;
    }

    /**
     * a method to determine the position of a pixel: it's in the inner part, or boundary(exluding corner),
     * or corner.
     *
     * @param x the x-coordinate of the pixel.
     * @param y the y-coordinate of the pixel.
     * @return a string to describe the position of pixel at (x, y), i.e. "in", "left", "right", "top", "buttom",
     * "topleft", "topright", "buttomleft", "buttomright".
     */
    private String position(int x, int y) {
        String s = "";
        if (x > 0 && x < width - 1 && y > 0 && y < height -1) s = "in";
        if (x > 0 && x < width - 1 && y == 0) s = "top";
        if (x > 0 && x < width - 1 && y == height - 1) s = "buttom";
        if (x == 0 && y > 0 && y < height - 1) s = "left";
        if (x == width - 1 && y > 0 && y < height - 1) s = "right";
        if (x == 0 && y == 0) s = "topleft";
        if (x == width - 1 && y == 0) s = "topright";
        if (x == 0 && y == height - 1) s = "buttomleft";
        if (x == width - 1 && y == height - 1) s = "buttomright";
        return s;
    }

    private short neighborRED(int x, int y) {
        short r = 0;
        if (position(x, y) == "in") {
            r = (short)(( getRed(x -1, y-1) + getRed(x, y - 1) + getRed(x + 1, y - 1) + getRed(x - 1, y) +
                    getRed(x, y) + getRed(x + 1, y) + getRed(x - 1, y + 1) + getRed(x, y + 1) +
                    getRed(x + 1, y + 1) ) / 9);
        }
        if (position(x, y) == "top") {
            r = (short)(( getRed(x - 1, y) + getRed(x, y) + getRed(x + 1, y) + getRed(x - 1, y + 1) +
                    getRed(x, y + 1) + getRed(x + 1, y + 1) ) / 6);
        }
        if (position(x, y) == "buttom") {
            r = (short)(( getRed(x - 1, y - 1) + getRed(x, y - 1) + getRed(x + 1, y - 1) +
                    getRed(x - 1, y) + getRed(x, y) + getRed(x + 1, y) ) / 6);
        }
        if (position(x, y) == "left") {
            r = (short)(( getRed(x, y - 1) + getRed(x + 1, y - 1) + getRed(x, y) +
                    getRed(x + 1, y) + getRed(x, y + 1) + getRed(x + 1, y + 1) ) / 6);
        }
        if (position(x, y) == "right") {
            r = (short)(( getRed(x - 1, y - 1) + getRed(x, y - 1) + getRed(x - 1, y) +
                    getRed(x, y) + getRed(x - 1, y + 1) + getRed(x, y + 1) ) / 6);
        }
        if (position(x, y) == "topleft") {
            r = (short)(( getRed(x, y) + getRed(x + 1, y) + getRed(x, y + 1) + getRed(x + 1, y + 1) ) / 4);
        }
        if (position(x, y) == "topright") {
            r = (short)(( getRed(x -1 , y) + getRed(x, y) + getRed(x - 1, y + 1) + getRed(x, y + 1) ) / 4);
        }
        if (position(x, y) == "buttomleft") {
            r = (short)(( getRed(x, y - 1) + getRed(x + 1, y - 1) + getRed(x, y) + getRed(x + 1, y) ) / 4);
        }
        if (position(x, y) == "buttomright") {
            r = (short)(( getRed(x - 1, y - 1) + getRed(x, y - 1) + getRed(x - 1, y) + getRed(x, y) ) / 4);
        }
        return r;
    }

    private short neighborGREEN(int x, int y) {
        short r = 0;
        if (position(x, y) == "in") {
            r = (short)(( getGreen(x -1, y-1) + getGreen(x, y - 1) + getGreen(x + 1, y - 1) + getGreen(x - 1, y) +
                    getGreen(x, y) + getGreen(x + 1, y) + getGreen(x - 1, y + 1) + getGreen(x, y + 1) +
                    getGreen(x + 1, y + 1) ) / 9);
        }
        if (position(x, y) == "top") {
            r = (short)(( getGreen(x - 1, y) + getGreen(x, y) + getGreen(x + 1, y) + getGreen(x - 1, y + 1) +
                    getGreen(x, y + 1) + getGreen(x + 1, y + 1) ) / 6);
        }
        if (position(x, y) == "buttom") {
            r = (short)(( getGreen(x - 1, y - 1) + getGreen(x, y - 1) + getGreen(x + 1, y - 1) +
                    getGreen(x - 1, y) + getGreen(x, y) + getGreen(x + 1, y) ) / 6);
        }
        if (position(x, y) == "left") {
            r = (short)(( getGreen(x, y - 1) + getGreen(x + 1, y - 1) + getGreen(x, y) +
                    getGreen(x + 1, y) + getGreen(x, y + 1) + getGreen(x + 1, y + 1) ) / 6);
        }
        if (position(x, y) == "right") {
            r = (short)(( getGreen(x - 1, y - 1) + getGreen(x, y - 1) + getGreen(x - 1, y) +
                    getGreen(x, y) + getGreen(x - 1, y + 1) + getGreen(x, y + 1) ) / 6);
        }
        if (position(x, y) == "topleft") {
            r = (short)(( getGreen(x, y) + getGreen(x + 1, y) + getGreen(x, y + 1) + getGreen(x + 1, y + 1) ) / 4);
        }
        if (position(x, y) == "topright") {
            r = (short)(( getGreen(x -1 , y) + getGreen(x, y) + getGreen(x - 1, y + 1) + getGreen(x, y + 1) ) / 4);
        }
        if (position(x, y) == "buttomleft") {
            r = (short)(( getGreen(x, y - 1) + getGreen(x + 1, y - 1) + getGreen(x, y) + getGreen(x + 1, y) ) / 4);
        }
        if (position(x, y) == "buttomright") {
            r = (short)(( getGreen(x - 1, y - 1) + getGreen(x, y - 1) + getGreen(x - 1, y) + getGreen(x, y) ) / 4);
        }
        return r;
    }

    private short neighborBLUE(int x, int y) {
        short r = 0;
        if (position(x, y) == "in") {
            r = (short)(( getBlue(x -1, y-1) + getBlue(x, y - 1) + getBlue(x + 1, y - 1) + getBlue(x - 1, y) +
                    getBlue(x, y) + getBlue(x + 1, y) + getBlue(x - 1, y + 1) + getBlue(x, y + 1) +
                    getBlue(x + 1, y + 1) ) / 9);
        }
        if (position(x, y) == "top") {
            r = (short)(( getBlue(x - 1, y) + getBlue(x, y) + getBlue(x + 1, y) + getBlue(x - 1, y + 1) +
                    getBlue(x, y + 1) + getBlue(x + 1, y + 1) ) / 6);
        }
        if (position(x, y) == "buttom") {
            r = (short)(( getBlue(x - 1, y - 1) + getBlue(x, y - 1) + getBlue(x + 1, y - 1) +
                    getBlue(x - 1, y) + getBlue(x, y) + getBlue(x + 1, y) ) / 6);
        }
        if (position(x, y) == "left") {
            r = (short)(( getBlue(x, y - 1) + getBlue(x + 1, y - 1) + getBlue(x, y) +
                    getBlue(x + 1, y) + getBlue(x, y + 1) + getBlue(x + 1, y + 1) ) / 6);
        }
        if (position(x, y) == "right") {
            r = (short)(( getBlue(x - 1, y - 1) + getBlue(x, y - 1) + getBlue(x - 1, y) +
                    getBlue(x, y) + getBlue(x - 1, y + 1) + getBlue(x, y + 1) ) / 6);
        }
        if (position(x, y) == "topleft") {
            r = (short)(( getBlue(x, y) + getBlue(x + 1, y) + getBlue(x, y + 1) + getBlue(x + 1, y + 1) ) / 4);
        }
        if (position(x, y) == "topright") {
            r = (short)(( getBlue(x -1 , y) + getBlue(x, y) + getBlue(x - 1, y + 1) + getBlue(x, y + 1) ) / 4);
        }
        if (position(x, y) == "buttomleft") {
            r = (short)(( getBlue(x, y - 1) + getBlue(x + 1, y - 1) + getBlue(x, y) + getBlue(x + 1, y) ) / 4);
        }
        if (position(x, y) == "buttomright") {
            r = (short)(( getBlue(x - 1, y - 1) + getBlue(x, y - 1) + getBlue(x - 1, y) + getBlue(x, y) ) / 4);
        }
        return r;
    }

    /**
     * boxBlur() returns a blurred version of "this" PixImage.
     *
     * If numIterations == 1, each pixel in the output PixImage is assigned
     * a value equal to the average of its neighboring pixels in "this" PixImage,
     * INCLUDING the pixel itself.
     *
     * A pixel not on the image boundary has nine neighbors--the pixel itself and
     * the eight pixels surrounding it.  A pixel on the boundary has six
     * neighbors if it is not a corner pixel; only four neighbors if it is
     * a corner pixel.  The average of the neighbors is the sum of all the
     * neighbor pixel values (including the pixel itself) divided by the number
     * of neighbors, with non-integer quotients rounded toward zero (as Java does
     * naturally when you divide two integers).
     *
     * Each color (red, green, blue) is blurred separately.  The red input should
     * have NO effect on the green or blue outputs, etc.
     *
     * The parameter numIterations specifies a number of repeated iterations of
     * box blurring to perform.  If numIterations is zero or negative, "this"
     * PixImage is returned (not a copy).  If numIterations is positive, the
     * return value is a newly constructed PixImage.
     *
     * IMPORTANT:  DO NOT CHANGE "this" PixImage!!!  All blurring/changes should
     * appear in the new, output PixImage only.
     *
     * @param numIterations the number of iterations of box blurring.
     * @return a blurred version of "this" PixImage.
     */
    public PixImage boxBlur(int numIterations) {
        // Replace the following line with your solution.
        if (numIterations <= 0)
            return this;
        else {
            PixImage[] pixArray = new PixImage[numIterations + 1];
            pixArray[0] = this;
            for (int i = 1; i <= numIterations; i++) {
                pixArray[i] = new PixImage(width, height);
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        pixArray[i].setPixel(x, y, pixArray[i - 1].neighborRED(x, y),
                                pixArray[i - 1].neighborGREEN(x, y), pixArray[i - 1].neighborBLUE(x, y));
                    }
                }
            }
            return pixArray[numIterations];
        }
    }

    /**
     * mag2gray() maps an energy (squared vector magnitude) in the range
     * 0...24,969,600 to a grayscale intensity in the range 0...255.  The map
     * is logarithmic, but shifted so that values of 5,080 and below map to zero.
     *
     * DO NOT CHANGE THIS METHOD.  If you do, you will not be able to get the
     * correct images and pass the autograder.
     *
     * @param mag the energy (squared vector magnitude) of the pixel whose
     * intensity we want to compute.
     * @return the intensity of the output pixel.
     */
    private static short mag2gray(long mag) {
        short intensity = (short) (30.0 * Math.log(1.0 + (double) mag) - 256.0);

        // Make sure the returned intensity is in the range 0...255, regardless of
        // the input value.
        if (intensity < 0) {
            intensity = 0;
        } else if (intensity > 255) {
            intensity = 255;
        }
        return intensity;
    }

    /**
     * a method to calculate the x-component of the gradient of color c at pixel(x,y).
     *
     * @param x the x-coordinate of the pixel.
     * @param y the y-coordinate of the pixel.
     * @param c the color we want to calculate.
     * @return the x-component of the gradient of color c at pixel(x,y).
     */
    private int gx(int x, int y, char c) {
        int gx = 0;
        if (c == 'r') {
            int gxRed = 0;
            if (position(x, y) == "in") {
                gxRed = getRed(x - 1, y - 1) - getRed(x + 1, y - 1) + 2 * (getRed(x - 1, y) - getRed(x + 1, y)) +
                        getRed(x - 1, y + 1) - getRed(x + 1, y + 1);
            }
            if (position(x, y) == "top") {
                gxRed = getRed(x - 1, y) - getRed(x + 1, y) + 2 * (getRed(x - 1, y) - getRed(x + 1, y)) +
                        getRed(x - 1, y + 1) - getRed(x + 1, y + 1);
            }
            if (position(x, y) == "buttom") {
                gxRed = getRed(x - 1, y - 1) - getRed(x + 1, y - 1) + 2 * (getRed(x - 1, y) - getRed(x + 1, y)) +
                        getRed(x - 1, y) - getRed(x + 1, y);
            }
            if (position(x, y) == "left") {
                gxRed = getRed(x, y - 1) - getRed(x + 1, y - 1) + 2 * (getRed(x, y) - getRed(x + 1, y)) +
                        getRed(x, y + 1) - getRed(x + 1, y + 1);
            }
            if (position(x, y) == "right") {
                gxRed = getRed(x - 1, y - 1) - getRed(x, y - 1) + 2 * (getRed(x - 1, y) - getRed(x, y)) +
                        getRed(x - 1, y + 1) - getRed(x, y + 1);
            }
            if (position(x, y) == "topleft") {
                gxRed = getRed(x, y) - getRed(x + 1, y) + 2 * (getRed(x, y) - getRed(x + 1, y)) +
                        getRed(x, y + 1) - getRed(x + 1, y + 1);
            }
            if (position(x, y) == "topright") {
                gxRed = getRed(x - 1, y) - getRed(x, y) + 2 * (getRed(x - 1, y) - getRed(x, y)) +
                        getRed(x - 1, y + 1) - getRed(x, y + 1);
            }
            if (position(x, y) == "buttomleft") {
                gxRed = getRed(x, y - 1) - getRed(x + 1, y - 1) + 2 * (getRed(x, y) - getRed(x + 1, y)) +
                        getRed(x, y) - getRed(x + 1, y);
            }
            if (position(x, y) == "buttomright") {
                gxRed = getRed(x - 1, y - 1) - getRed(x, y - 1) + 2 * (getRed(x - 1, y) - getRed(x, y)) +
                        getRed(x - 1, y) - getRed(x, y);
            }
            gx = gxRed;
        }
        if (c == 'g') {
            int gxGreen = 0;
            if (position(x, y) == "in") {
                gxGreen = getGreen(x - 1, y - 1) - getGreen(x + 1, y - 1) + 2 * (getGreen(x - 1, y) - getGreen(x + 1, y)) +
                        getGreen(x - 1, y + 1) - getGreen(x + 1, y + 1);
            }
            if (position(x, y) == "top") {
                gxGreen = getGreen(x - 1, y) - getGreen(x + 1, y) + 2 * (getGreen(x - 1, y) - getGreen(x + 1, y)) +
                        getGreen(x - 1, y + 1) - getGreen(x + 1, y + 1);
            }
            if (position(x, y) == "buttom") {
                gxGreen = getGreen(x - 1, y - 1) - getGreen(x + 1, y - 1) + 2 * (getGreen(x - 1, y) - getGreen(x + 1, y)) +
                        getGreen(x - 1, y) - getGreen(x + 1, y);
            }
            if (position(x, y) == "left") {
                gxGreen = getGreen(x, y - 1) - getGreen(x + 1, y - 1) + 2 * (getGreen(x, y) - getGreen(x + 1, y)) +
                        getGreen(x, y + 1) - getGreen(x + 1, y + 1);
            }
            if (position(x, y) == "right") {
                gxGreen = getGreen(x - 1, y - 1) - getGreen(x, y - 1) + 2 * (getGreen(x - 1, y) - getGreen(x, y)) +
                        getGreen(x - 1, y + 1) - getGreen(x, y + 1);
            }
            if (position(x, y) == "topleft") {
                gxGreen = getGreen(x, y) - getGreen(x + 1, y) + 2 * (getGreen(x, y) - getGreen(x + 1, y)) +
                        getRed(x, y + 1) - getRed(x + 1, y + 1);
            }
            if (position(x, y) == "topright") {
                gxGreen = getGreen(x - 1, y) - getGreen(x, y) + 2 * (getGreen(x - 1, y) - getGreen(x, y)) +
                        getGreen(x - 1, y + 1) - getGreen(x, y + 1);
            }
            if (position(x, y) == "buttomleft") {
                gxGreen = getGreen(x, y - 1) - getGreen(x + 1, y - 1) + 2 * (getGreen(x, y) - getGreen(x + 1, y)) +
                        getRed(x, y) - getRed(x + 1, y);
            }
            if (position(x, y) == "buttomright") {
                gxGreen = getGreen(x - 1, y - 1) - getGreen(x, y - 1) + 2 * (getGreen(x - 1, y) - getGreen(x, y)) +
                        getGreen(x - 1, y) - getGreen(x, y);
            }
            gx = gxGreen;
        }
        if (c == 'b') {
            int gxBlue = 0;
            if (position(x, y) == "in") {
                gxBlue = getBlue(x - 1, y - 1) - getBlue(x + 1, y - 1) + 2 * (getBlue(x - 1, y) - getBlue(x + 1, y)) +
                        getBlue(x - 1, y + 1) - getBlue(x + 1, y + 1);
            }
            if (position(x, y) == "top") {
                gxBlue = getBlue(x - 1, y) - getBlue(x + 1, y) + 2 * (getBlue(x - 1, y) - getBlue(x + 1, y)) +
                        getBlue(x - 1, y + 1) - getBlue(x + 1, y + 1);
            }
            if (position(x, y) == "buttom") {
                gxBlue = getBlue(x - 1, y - 1) - getRed(x + 1, y - 1) + 2 * (getRed(x - 1, y) - getRed(x + 1, y)) +
                        getRed(x - 1, y) - getRed(x + 1, y);
            }
            if (position(x, y) == "left") {
                gxBlue = getBlue(x, y - 1) - getBlue(x + 1, y - 1) + 2 * (getBlue(x, y) - getBlue(x + 1, y)) +
                        getBlue(x, y + 1) - getBlue(x + 1, y + 1);
            }
            if (position(x, y) == "right") {
                gxBlue = getBlue(x - 1, y - 1) - getBlue(x, y - 1) + 2 * (getBlue(x - 1, y) - getBlue(x, y)) +
                        getBlue(x - 1, y + 1) - getBlue(x, y + 1);
            }
            if (position(x, y) == "topleft") {
                gxBlue = getBlue(x, y) - getBlue(x + 1, y) + 2 * (getBlue(x, y) - getBlue(x + 1, y)) +
                        getBlue(x, y + 1) - getBlue(x + 1, y + 1);
            }
            if (position(x, y) == "topright") {
                gxBlue = getBlue(x - 1, y) - getBlue(x, y) + 2 * (getBlue(x - 1, y) - getBlue(x, y)) +
                        getBlue(x - 1, y + 1) - getBlue(x, y + 1);
            }
            if (position(x, y) == "buttomleft") {
                gxBlue = getBlue(x, y - 1) - getBlue(x + 1, y - 1) + 2 * (getBlue(x, y) - getBlue(x + 1, y)) +
                        getBlue(x, y) - getBlue(x + 1, y);
            }
            if (position(x, y) == "buttomright") {
                gxBlue = getBlue(x - 1, y - 1) - getBlue(x, y - 1) + 2 * (getBlue(x - 1, y) - getBlue(x, y)) +
                        getBlue(x - 1, y) - getBlue(x, y);
            }
            gx = gxBlue;
        }
        return gx;
    }

    /**
     * a method to calculate the y-component of the gradient of color c at pixel(x,y).
     *
     * @param x the x-coordinate of the pixel.
     * @param y the y-coordinate of the pixel.
     * @param c the color we want to calculate.
     * @return the y-component of the gradient of color c at pixel(x,y).
     */
    private int gy(int x, int y, char c) {
        int gy = 0;
        if (c == 'r') {
            int gyRed = 0;
            if (position(x, y) == "in") {
                gyRed = getRed(x - 1, y - 1) - getRed(x - 1, y + 1) + 2 * (getRed(x, y - 1) - getRed(x, y + 1)) +
                        getRed(x + 1, y - 1) - getRed(x + 1, y + 1);
            }
            if (position(x, y) == "top") {
                gyRed = getRed(x - 1, y) - getRed(x - 1, y + 1) + 2 * (getRed(x, y) - getRed(x, y + 1)) +
                        getRed(x + 1, y) - getRed(x + 1, y + 1);
            }
            if (position(x, y) == "buttom") {
                gyRed = getRed(x - 1, y - 1) - getRed(x - 1, y) + 2 * (getRed(x, y - 1) - getRed(x, y)) +
                        getRed(x + 1, y - 1) - getRed(x + 1, y);
            }
            if (position(x, y) == "left") {
                gyRed = getRed(x, y - 1) - getRed(x, y + 1) + 2 * (getRed(x, y - 1) - getRed(x, y + 1)) +
                        getRed(x + 1, y - 1) - getRed(x + 1, y + 1);
            }
            if (position(x, y) == "right") {
                gyRed = getRed(x - 1, y - 1) - getRed(x - 1, y + 1) + 2 * (getRed(x, y - 1) - getRed(x, y + 1)) +
                        getRed(x, y - 1) - getRed(x, y + 1);
            }
            if (position(x, y) == "topleft") {
                gyRed = getRed(x, y) - getRed(x, y + 1) + 2 * (getRed(x, y) - getRed(x, y + 1)) +
                        getRed(x + 1, y) - getRed(x + 1, y + 1);
            }
            if (position(x, y) == "topright") {
                gyRed = getRed(x - 1, y) - getRed(x - 1, y + 1) + 2 * (getRed(x, y) - getRed(x, y + 1)) +
                        getRed(x, y) - getRed(x, y + 1);
            }
            if (position(x, y) == "buttomleft") {
                gyRed = getRed(x, y - 1) - getRed(x, y) + 2 * (getRed(x, y - 1) - getRed(x, y)) +
                        getRed(x + 1, y - 1) - getRed(x + 1, y);
            }
            if (position(x, y) == "buttomright") {
                gyRed = getRed(x - 1, y - 1) - getRed(x - 1, y) + 2 * (getRed(x, y - 1) - getRed(x, y)) +
                        getRed(x, y - 1) - getRed(x, y);
            }
            gy = gyRed;
        }
        if (c == 'g') {
            int gyGreen = 0;
            if (position(x, y) == "in") {
                gyGreen = getGreen(x - 1, y - 1) - getGreen(x - 1, y + 1) + 2 * (getGreen(x, y - 1) - getGreen(x, y + 1)) +
                        getGreen(x + 1, y - 1) - getGreen(x + 1, y + 1);
            }
            if (position(x, y) == "top") {
                gyGreen = getGreen(x - 1, y) - getGreen(x - 1, y + 1) + 2 * (getGreen(x, y) - getGreen(x, y + 1)) +
                        getGreen(x + 1, y) - getGreen(x + 1, y + 1);
            }
            if (position(x, y) == "buttom") {
                gyGreen = getGreen(x - 1, y - 1) - getGreen(x - 1, y) + 2 * (getGreen(x, y - 1) - getGreen(x, y)) +
                        getGreen(x + 1, y - 1) - getGreen(x + 1, y);
            }
            if (position(x, y) == "left") {
                gyGreen = getGreen(x, y - 1) - getGreen(x, y + 1) + 2 * (getGreen(x, y - 1) - getGreen(x, y + 1)) +
                        getGreen(x + 1, y - 1) - getGreen(x + 1, y + 1);
            }
            if (position(x, y) == "right") {
                gyGreen = getGreen(x - 1, y - 1) - getGreen(x - 1, y + 1) + 2 * (getGreen(x, y - 1) - getGreen(x, y + 1)) +
                        getGreen(x, y - 1) - getGreen(x, y + 1);
            }
            if (position(x, y) == "topleft") {
                gyGreen = getGreen(x, y) - getGreen(x, y + 1) + 2 * (getGreen(x, y) - getGreen(x, y + 1)) +
                        getGreen(x + 1, y) - getGreen(x + 1, y + 1);
            }
            if (position(x, y) == "topright") {
                gyGreen = getGreen(x - 1, y) - getGreen(x - 1, y + 1) + 2 * (getGreen(x, y) - getGreen(x, y + 1)) +
                        getGreen(x, y) - getGreen(x, y + 1);
            }
            if (position(x, y) == "buttomleft") {
                gyGreen = getGreen(x, y - 1) - getGreen(x, y) + 2 * (getGreen(x, y - 1) - getGreen(x, y)) +
                        getGreen(x + 1, y - 1) - getGreen(x + 1, y);
            }
            if (position(x, y) == "buttomright") {
                gyGreen = getGreen(x - 1, y - 1) - getGreen(x - 1, y) + 2 * (getGreen(x, y - 1) - getGreen(x, y)) +
                        getGreen(x, y - 1) - getGreen(x, y);
            }
            gy = gyGreen;
        }
        if (c == 'b') {
            int gyBlue = 0;
            if (position(x, y) == "in") {
                gyBlue = getBlue(x - 1, y - 1) - getBlue(x - 1, y + 1) + 2 * (getBlue(x, y - 1) - getBlue(x, y + 1)) +
                        getBlue(x + 1, y - 1) - getBlue(x + 1, y + 1);
            }
            if (position(x, y) == "top") {
                gyBlue = getBlue(x - 1, y) - getBlue(x - 1, y + 1) + 2 * (getBlue(x, y) - getBlue(x, y + 1)) +
                        getBlue(x + 1, y) - getBlue(x + 1, y + 1);
            }
            if (position(x, y) == "buttom") {
                gyBlue = getBlue(x - 1, y - 1) - getBlue(x - 1, y) + 2 * (getBlue(x, y - 1) - getBlue(x, y)) +
                        getBlue(x + 1, y - 1) - getBlue(x + 1, y);
            }
            if (position(x, y) == "left") {
                gyBlue = getBlue(x, y - 1) - getBlue(x, y + 1) + 2 * (getBlue(x, y - 1) - getBlue(x, y + 1)) +
                        getBlue(x + 1, y - 1) - getBlue(x + 1, y + 1);
            }
            if (position(x, y) == "right") {
                gyBlue = getBlue(x - 1, y - 1) - getBlue(x - 1, y + 1) + 2 * (getBlue(x, y - 1) - getBlue(x, y + 1)) +
                        getBlue(x, y - 1) - getBlue(x, y + 1);
            }
            if (position(x, y) == "topleft") {
                gyBlue = getBlue(x, y) - getBlue(x, y + 1) + 2 * (getBlue(x, y) - getBlue(x, y + 1)) +
                        getBlue(x + 1, y) - getBlue(x + 1, y + 1);
            }
            if (position(x, y) == "topright") {
                gyBlue = getBlue(x - 1, y) - getBlue(x - 1, y + 1) + 2 * (getBlue(x, y) - getBlue(x, y + 1)) +
                        getBlue(x, y) - getBlue(x, y + 1);
            }
            if (position(x, y) == "buttomleft") {
                gyBlue = getBlue(x, y - 1) - getBlue(x, y) + 2 * (getBlue(x, y - 1) - getBlue(x, y)) +
                        getBlue(x + 1, y - 1) - getBlue(x + 1, y);
            }
            if (position(x, y) == "buttomright") {
                gyBlue = getBlue(x - 1, y - 1) - getBlue(x - 1, y) + 2 * (getBlue(x, y - 1) - getBlue(x, y)) +
                        getBlue(x, y - 1) - getBlue(x, y);
            }
            gy = gyBlue;
        }
        return gy;
    }

    /**
     * sobelEdges() applies the Sobel operator, identifying edges in "this"
     * image.  The Sobel operator computes a magnitude that represents how
     * strong the edge is.  We compute separate gradients for the red, blue, and
     * green components at each pixel, then sum the squares of the three
     * gradients at each pixel.  We convert the squared magnitude at each pixel
     * into a grayscale pixel intensity in the range 0...255 with the logarithmic
     * mapping encoded in mag2gray().  The output is a grayscale PixImage whose
     * pixel intensities reflect the strength of the edges.
     *
     * See http://en.wikipedia.org/wiki/Sobel_operator#Formulation for details.
     *
     * @return a grayscale PixImage representing the edges of the input image.
     * Whiter pixels represent stronger edges.
     */
    public PixImage sobelEdges() {
        // Don't forget to use the method mag2gray() above to convert energies to
        // pixel intensities.
        PixImage sobelImage = new PixImage(width, height);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++){
                long energy = gx(i, j, 'r') * gx(i, j, 'r') + gy(i, j, 'r') * gy(i, j, 'r') +
                        gx(i, j, 'g') * gx(i, j, 'g') + gy(i, j, 'g') * gy(i, j, 'g') +
                        gx(i, j, 'b') * gx(i, j, 'b') + gy(i, j, 'b') * gy(i, j, 'b');
                sobelImage.setPixel(i, j, mag2gray(energy), mag2gray(energy), mag2gray(energy));
            }
        }
        return sobelImage;
    }


    /**
     * TEST CODE:  YOU DO NOT NEED TO FILL IN ANY METHODS BELOW THIS POINT.
     * You are welcome to add tests, though.  Methods below this point will not
     * be tested.  This is not the autograder, which will be provided separately.
     */


    /**
     * doTest() checks whether the condition is true and prints the given error
     * message if it is not.
     *
     * @param b the condition to check.
     * @param msg the error message to print if the condition is false.
     */
    private static void doTest(boolean b, String msg) {
        if (b) {
            System.out.println("Good.");
        } else {
            System.err.println(msg);
        }
    }

    /**
     * array2PixImage() converts a 2D array of grayscale intensities to
     * a grayscale PixImage.
     *
     * @param pixels a 2D array of grayscale intensities in the range 0...255.
     * @return a new PixImage whose red, green, and blue values are equal to
     * the input grayscale intensities.
     */
    private static PixImage array2PixImage(int[][] pixels) {
        int width = pixels.length;
        int height = pixels[0].length;
        PixImage image = new PixImage(width, height);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setPixel(x, y, (short) pixels[x][y], (short) pixels[x][y],
                        (short) pixels[x][y]);
            }
        }

        return image;
    }

    /**
     * equals() checks whether two images are the same, i.e. have the same
     * dimensions and pixels.
     *
     * @param image a PixImage to compare with "this" PixImage.
     * @return true if the specified PixImage is identical to "this" PixImage.
     */
    public boolean equals(PixImage image) {
        int width = getWidth();
        int height = getHeight();

        if (image == null ||
                width != image.getWidth() || height != image.getHeight()) {
            return false;
        }

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (! (getRed(x, y) == image.getRed(x, y) &&
                        getGreen(x, y) == image.getGreen(x, y) &&
                        getBlue(x, y) == image.getBlue(x, y))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * main() runs a series of tests to ensure that the convolutions (box blur
     * and Sobel) are correct.
     */
    public static void main(String[] args) {
        // Be forwarned that when you write arrays directly in Java as below,
        // each "row" of text is a column of your image--the numbers get
        // transposed.
        PixImage image1 = array2PixImage(new int[][] { { 0, 10, 240 },
                { 30, 120, 250 },
                { 80, 250, 255 } });
        System.out.println("Testing getWidth/getHeight on a 3x3 image.  " +
                "Input image:");
        System.out.print(image1);
        doTest(image1.getWidth() == 3 && image1.getHeight() == 3,
                "Incorrect image width and height.");

        System.out.println("Testing blurring on a 3x3 image.");
        doTest(image1.boxBlur(1).equals(
                array2PixImage(new int[][] { { 40, 108, 155 },
                        { 81, 137, 187 },
                        { 120, 164, 218 } })),
                "Incorrect box blur (1 rep):\n" + image1.boxBlur(1));
        doTest(image1.boxBlur(2).equals(
                array2PixImage(new int[][] { { 91, 118, 146 },
                        { 108, 134, 161 },
                        { 125, 151, 176 } })),
                "Incorrect box blur (2 rep):\n" + image1.boxBlur(2));
        doTest(image1.boxBlur(2).equals(image1.boxBlur(1).boxBlur(1)),
                "Incorrect box blur (1 rep + 1 rep):\n" +
                        image1.boxBlur(2) + image1.boxBlur(1).boxBlur(1));

        System.out.println("Testing edge detection on a 3x3 image.");
        doTest(image1.sobelEdges().equals(
                array2PixImage(new int[][] { { 104, 189, 180 },
                        { 160, 193, 157 },
                        { 166, 178, 96 } })),
                "Incorrect Sobel:\n" + image1.sobelEdges());


        PixImage image2 = array2PixImage(new int[][] { { 0, 100, 100 },
                { 0, 0, 100 } });
        System.out.println("Testing getWidth/getHeight on a 2x3 image.  " +
                "Input image:");
        System.out.print(image2);
        doTest(image2.getWidth() == 2 && image2.getHeight() == 3,
                "Incorrect image width and height.");

        System.out.println("Testing blurring on a 2x3 image.");
        doTest(image2.boxBlur(1).equals(
                array2PixImage(new int[][] { { 25, 50, 75 },
                        { 25, 50, 75 } })),
                "Incorrect box blur (1 rep):\n" + image2.boxBlur(1));

        System.out.println("Testing edge detection on a 2x3 image.");
        doTest(image2.sobelEdges().equals(
                array2PixImage(new int[][] { { 122, 143, 74 },
                        { 74, 143, 122 } })),
                "Incorrect Sobel:\n" + image2.sobelEdges());
    }
}