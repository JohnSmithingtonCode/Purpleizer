import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Purpleizer {

    public static void main(String[] args) {


        try {
            // Load the image
            File inputFile = new File("images/input.jpg");
            if (!inputFile.exists()) {
                System.out.println("Input file not found: " + inputFile.getAbsolutePath());
                return;
            }
            BufferedImage image = ImageIO.read(inputFile);
            if (image == null) {
                System.out.println("Failed to load the image.");
                return;
            }

            System.out.println("Image loaded successfully.");

            // Apply purple filter to the image
            BufferedImage purpleImage = applyPurpleFilter(image);

            // Define the output file path
            File outputFile = new File("images/output_purple.jpg");

            // Convert to a compatible image type (if necessary)
            BufferedImage outputImage = convertToCompatibleImage(purpleImage);

            // Save the filtered image as a JPEG (or PNG)
            boolean isSaved = ImageIO.write(outputImage, "jpg", outputFile);
            if (isSaved) {
                System.out.println("The image has been saved with the purple filter at: " + outputFile.getAbsolutePath());
            } else {
                System.out.println("Failed to save the image.");
            }
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

    // Convert the image to a compatible image type (RGB) for saving
    private static BufferedImage convertToCompatibleImage(BufferedImage image) {
        // Convert to an image type that is compatible with JPEG (e.g., RGB)
        if (image.getType() != BufferedImage.TYPE_INT_RGB) {
            BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = newImage.createGraphics();
            g.drawImage(image, 0, 0, null);
            g.dispose();
            return newImage;
        }
        return image;
    }
}
