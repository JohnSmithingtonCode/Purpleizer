import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class GUI extends JFrame {

    private JPanel imagePanel;
    private Purpleizer purpleizer = new Purpleizer();

    public GUI() {
        // Set up the JFrame
        setTitle("Purpleize Your Image");
        setSize(1000, 600);  // Adjusted size for better layout
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set a gradient background for the JFrame
        JPanel contentPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, new Color(255, 182, 193), getWidth(), getHeight(), new Color(147, 112, 219));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        contentPanel.setLayout(new BorderLayout());
        setContentPane(contentPanel);

        // Create a JLabel for the heading
        JLabel headingLabel = new JLabel("PURPLEIZE YOUR IMAGE", JLabel.CENTER);
        headingLabel.setFont(new Font("Verdana", Font.BOLD, 28));  // More modern font and larger size
        headingLabel.setForeground(new Color(128, 0, 128)); // Purple color for the text
        headingLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Add spacing around the label
        contentPanel.add(headingLabel, BorderLayout.NORTH);

        // Create a JPanel to hold two images side by side with padding and spacing
        imagePanel = new JPanel();
        imagePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30));  // Horizontal layout with custom padding
        contentPanel.add(imagePanel, BorderLayout.CENTER);

        // Create an open file button with improved styling
        JButton openButton = new JButton("Open Image");
        openButton.setFont(new Font("Arial", Font.PLAIN, 18));
        openButton.setForeground(Color.WHITE);  // White text
        openButton.setBackground(new Color(128, 0, 128));  // Purple background
        openButton.setFocusPainted(false);  // Remove border when clicked
        openButton.setPreferredSize(new Dimension(250, 50));  // Set a custom size for the button
        openButton.setBorder(BorderFactory.createLineBorder(new Color(128, 0, 128), 2, true)); // Rounded button border
        openButton.setBorderPainted(true);

        // Add hover effect for the button
        openButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                openButton.setBackground(new Color(153, 50, 204));  // Darker purple when hovered
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                openButton.setBackground(new Color(128, 0, 128));  // Original purple color when not hovered
            }
        });

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
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 255, 255));  // Set button area background to white
        buttonPanel.add(openButton);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    // Method to display two copies of the image side by side in the JPanel
    private void displayImage(File file) {
        ImageIcon originalIcon = new ImageIcon(file.getAbsolutePath());
        Image originalImage = originalIcon.getImage();
        purpleizer.Purpleize(file.getAbsolutePath());
        ImageIcon purpleizedIcon = new ImageIcon("images/output_purple.jpg");
        Image purplizedImage = purpleizedIcon.getImage();

        // Fixed size for each image
        int newWidth = 250;
        int newHeight = 250;

        // Scale the images to the fixed size (250x250 pixels)
        Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        Image scaledPurpleImage = purplizedImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledPurpleIcon = new ImageIcon(scaledPurpleImage);

        // Clear the imagePanel and add two copies of the scaled image
        imagePanel.removeAll();
        JLabel imageLabel1 = new JLabel(scaledIcon);
        JLabel imageLabel2 = new JLabel(scaledPurpleIcon);

        // Add rounded corners for the images
        imageLabel1.setIcon(new ImageIcon(createRoundedImage(originalIcon.getImage(), 250, 250)));
        imageLabel2.setIcon(new ImageIcon(createRoundedImage(purpleizedIcon.getImage(), 250, 250)));

        imagePanel.add(imageLabel1);
        imagePanel.add(imageLabel2);

        // Revalidate and repaint the image panel to display both images
        imagePanel.revalidate();
        imagePanel.repaint();
    }

    // Helper method to create rounded corners for images
    private Image createRoundedImage(Image src, int width, int height) {
        BufferedImage buffered = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = buffered.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(0, 0, width, height, 50, 50);  // Set corner radius
        g2d.setComposite(AlphaComposite.SrcAtop);
        g2d.drawImage(src, 0, 0, width, height, null);
        g2d.dispose();
        return buffered;
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
