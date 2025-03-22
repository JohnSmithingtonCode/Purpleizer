import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GUI extends JFrame {

    private JPanel imagePanel;

    public GUI() {
        // Set up the JFrame
        setTitle("Image Display");
        setSize(1000, 500);  // Initial size of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a JLabel for the heading
        JLabel headingLabel = new JLabel("PURPLEIZE YOUR IMAGE", JLabel.CENTER);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set the font and size
        headingLabel.setForeground(Color.MAGENTA); // Set the text color to purple
        add(headingLabel, BorderLayout.NORTH);

        // Create a JPanel to hold two images side by side
        imagePanel = new JPanel();
        imagePanel.setLayout(new FlowLayout(FlowLayout.CENTER));  // Horizontal layout to display images side by side
        add(imagePanel, BorderLayout.CENTER);

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

    // Method to display two copies of the image side by side in the JPanel
    private void displayImage(File file) {
        ImageIcon originalIcon = new ImageIcon(file.getAbsolutePath());
        Image originalImage = originalIcon.getImage();

        // Fixed size for each image
        int newWidth = 250;
        int newHeight = 250;

        // Scale the image to the fixed size (250x250 pixels)
        Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // Clear the imagePanel and add two copies of the scaled image
        imagePanel.removeAll();
        JLabel imageLabel1 = new JLabel(scaledIcon);
        JLabel imageLabel2 = new JLabel(scaledIcon);
        imagePanel.add(imageLabel1);
        imagePanel.add(imageLabel2);

        // Revalidate and repaint the image panel to display both images
        imagePanel.revalidate();
        imagePanel.repaint();
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
