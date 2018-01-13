/**
 * How to use JSesh to create bitmaps in Java.
 * compile: javac -cp .:/FOLDER_CONTAINING/jsesh.jar TestJSeshBitmap.java
 * run: java -cp .:/FOLDER_CONTAINING/jsesh.jar TestJSeshBitmap
 * 
 * jseshGlyphs.jar and jvectClipboard-1.0.jar should be in the same folder as jsesh.jar.
 * (normally, there is no need to add them explicitely to the class path , as jsesh.jar contains the necessary 
 * information in its manifest.
 */
 
import javax.imageio.ImageIO;
import java.util.Base64;
import java.io.*;
import java.awt.*;
import java.awt.image.* ;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import jsesh.mdcDisplayer.preferences.*;
import jsesh.mdcDisplayer.draw.*;
import jsesh.mdc.*;
 
public class JTestTransp {
    public static BufferedImage buildImage(String mdcText) throws MDCSyntaxError {
        // Create the drawing system:                
        MDCDrawingFacade drawing = new MDCDrawingFacade();
        // Change the scale, choosing the cadrat height in pixels.
        drawing.setCadratHeight(90);
        // Change a number of parameters 
        DrawingSpecification drawingSpecifications = new DrawingSpecificationsImplementation();
        PageLayout pageLayout= new PageLayout();
       // pageLayout.setLeftMargin(5);
       // pageLayout.setTopMargin(5);
	    pageLayout.setLeftMargin(0);
        pageLayout.setTopMargin(0);
        drawingSpecifications.setPageLayout(pageLayout);
        drawing.setDrawingSpecifications(drawingSpecifications);
        // Create the picture 
       BufferedImage result = drawing.createImage(mdcText);
       return result;
    }

//Source: https://stackoverflow.com/questions/665406/how-to-make-a-color-transparent-in-a-bufferedimage-and-save-as-png
    public static Image makeColorTransparent(BufferedImage im, final Color color) {
        ImageFilter filter = new RGBImageFilter() {

            // the color we are looking for... Alpha bits are set to opaque
            public int markerRGB = color.getRGB() | 0xFF000000;

            public final int filterRGB(int x, int y, int rgb) {
                if ((rgb | 0xFF000000) == markerRGB) {
                    // Mark the alpha bits as zero - transparent
                    return 0x00FFFFFF & rgb;
                } else {
                    // nothing to do
                    return rgb;
                }
            }
        };

        ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
        return Toolkit.getDefaultToolkit().createImage(ip);
    }
    
    private static BufferedImage imageToBufferedImage(Image image) {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        return bufferedImage;

    }

    private static String convertToBase64(BufferedImage image) throws IOException {
    	ByteArrayOutputStream bos = new ByteArrayOutputStream();
    	ImageIO.write(image, "png", bos);
    	bos.flush();
    	byte[] imgBytes = bos.toByteArray();
    	bos.close();
    	return Base64.getEncoder().encodeToString(imgBytes);
    }

    public static String pipeline(String mdc) throws MDCSyntaxError, IOException {
        BufferedImage img = buildImage(mdc);
        Image image = makeColorTransparent(img, new Color(255, 255, 255));
        BufferedImage transparent = imageToBufferedImage(image);
        // File f = new File(args[0]);
        return convertToBase64(transparent);
    }
	
	public static void main(String[] args) throws MDCSyntaxError, IOException {
		// Create the picture, convert it to base64 and print to Stdout
		String imageString = pipeline(args[0]);
		System.out.println(imageString);
  }
}