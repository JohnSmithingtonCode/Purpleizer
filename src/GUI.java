import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GUI extends JFrame {

    private JLabel imageLabel;

    public GUI() {
        // Set up the JFrame
        setTitle("Image Display");
        setSize(500, 500);  // Initial size of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a JLabel to hold the image
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        add(imageLabel, BorderLayout.CENTER);

        // Create an open file button
        JButton openButton = new JButton("Open Image");
        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open a file chooser dialog
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Select an Image File");

                // Set file filter for image types
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "bmp"));

                int result = fileChooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    displayImage(selectedFile);
                }
            }
        });

        // Add the button to the bottom of the frame
        add(openButton, BorderLayout.SOUTH);
    }

    // Method to display the image in the JLabel, scaled to fit within the window
    private void displayImage(File file) {
        ImageIcon originalIcon = new ImageIcon(file.getAbsolutePath());
        Image originalImage = originalIcon.getImage();

        // Get the current size of the window
        int width = getWidth();
        int height = getHeight();

        // Calculate the new width and height to maintain aspect ratio and fit within the window
        int newWidth = width;
        int newHeight = (int) (originalImage.getHeight(null) * ((double) newWidth / originalImage.getWidth(null)));

        // If the new height exceeds the window height, adjust based on the height
        if (newHeight > height) {
            newHeight = height;
            newWidth = (int) (originalImage.getWidth(null) * ((double) newHeight / originalImage.getHeight(null)));
        }

        // Scale the image
        Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // Set the scaled image icon to the JLabel
        imageLabel.setIcon(scaledIcon);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        // Create and show the GUI
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }
}
