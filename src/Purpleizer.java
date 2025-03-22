import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Purpleizer {

    public static void main(String[] args) {
        try {
            // Load the image
            File inputFile = new File("input.jpg");
            BufferedImage image = ImageIO.read(inputFile);

            // Apply purple filter to the image
            BufferedImage purpleImage = applyPurpleFilter(image);

            // Save the filtered image
            File outputFile = new File("output_purple.jpg");
            ImageIO.write(purpleImage, "jpg", outputFile);

            System.out.println("The image has been saved with the purple filter.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage applyPurpleFilter(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        // Loop through every pixel in the image
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // Get the color of the current pixel
                Color originalColor = new Color(image.getRGB(x, y));

                // Extract the RGB components
                int red = originalColor.getRed();
                int green = originalColor.getGreen();
                int blue = originalColor.getBlue();

                // Apply a purple filter: keep red and blue, modify green to give it a purplish tint
                int newRed = red;
                int newGreen = green / 2;  // Reduce green to make it more purple
                int newBlue = blue;

                // Create a new color with the modified RGB values
                Color newColor = new Color(newRed, newGreen, newBlue);

                // Set the new color to the pixel
                image.setRGB(x, y, newColor.getRGB());
            }
        }

        return image;
    }
}
