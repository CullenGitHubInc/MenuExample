import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class MenuExample {
    private JTextArea textBox;

    public MenuExample() {
        JFrame frame = new JFrame("Menu Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create the text area
        textBox = new JTextArea();
        textBox.setEditable(false);  // Set text box to non-editable
        frame.add(new JScrollPane(textBox), BorderLayout.CENTER);

        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create the menu
        JMenu menu = new JMenu("Options");
        menuBar.add(menu);

        // Add menu items
        JMenuItem dateTimeItem = new JMenuItem("Print Date and Time");
        JMenuItem saveToFileItem = new JMenuItem("Save to File");
        JMenuItem changeColorItem = new JMenuItem("Change Background Color");
        JMenuItem exitItem = new JMenuItem("Exit");

        menu.add(dateTimeItem);
        menu.add(saveToFileItem);
        menu.add(changeColorItem);
        menu.add(exitItem);

        // Add ActionListeners to the menu items
        dateTimeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printDateTime();
            }
        });

        saveToFileItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToFile();
            }
        });

        changeColorItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeBackgroundColor(frame);
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Set the menu bar for the frame
        frame.setJMenuBar(menuBar);

        // Make the frame visible
        frame.setVisible(true);
    }

    private void printDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        textBox.setText(now.format(formatter));
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
            writer.write(textBox.getText());
            writer.newLine();
            JOptionPane.showMessageDialog(null, "Text saved to log.txt");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to save to file");
        }
    }

    private void changeBackgroundColor(JFrame frame) {
        Random random = new Random();
        float hue = 0.33f + random.nextFloat() * 0.17f; // Random hue in the green range (120° to 180° in HSL)
        float saturation = 0.5f + random.nextFloat() * 0.5f; // Random saturation between 0.5 and 1
        float brightness = 0.5f + random.nextFloat() * 0.5f; // Random brightness between 0.5 and 1
        Color randomGreenColor = Color.getHSBColor(hue, saturation, brightness);

        // Set the background color of the text box
        textBox.setBackground(randomGreenColor);

        // Set the text color to contrast the background
        if (brightness < 0.5) {
            textBox.setForeground(Color.WHITE);
        } else {
            textBox.setForeground(Color.BLACK);
        }

        // Update the text box with the current HSL value
        textBox.setText(String.format("Background changed to HSL (Hue: %.2f, Saturation: %.2f, Lightness: %.2f)",
                hue * 360, saturation * 100, brightness * 100));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuExample::new);
    }
}
