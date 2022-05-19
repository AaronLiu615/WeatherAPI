import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class WeatherGUI implements ActionListener {
    private JTextField zipCodeEntryField;
    private WeatherNetworking client;

    public WeatherGUI() {
        zipCodeEntryField = new JTextField();
        client = new WeatherNetworking();

        setupGui();
    }

    private void setupGui() {
        JFrame frame = new JFrame("Current Weather");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel entryPanel = new JPanel();
        JLabel zip = new JLabel("Enter Zip Code: ");
        zipCodeEntryField = new JTextField(10);
        JButton submitButton = new JButton("Submit");
        JButton clearButton = new JButton("Clear");
        JCheckBox celsiusShow = new JCheckBox("Show Celsius");

        entryPanel.add(zip);
        entryPanel.add(zipCodeEntryField);
        entryPanel.add(submitButton);
        entryPanel.add(clearButton);

        entryPanel.add(celsiusShow);

        JPanel printedInfo = new JPanel();
        temperature = new JLabel();
        condition = new JLabel();
        imageLabel = new JLabel();
        printedInfo.add(temperature);
        printedInfo.add(condition);
        printedInfo.add(imageLabel);

        frame.add(info, BorderLayout.NORTH);
        frame.add(entryPanel, BorderLayout.CENTER);
        frame.add(printedInfo, BorderLayout.SOUTH);
        submitButton.addActionListener(this);
        clearButton.addActionListener(this);
        celsiusShow.addActionListener(this);

        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) (e.getSource());
            String text = button.getText();
            String selectedZipCode = zipCodeEntryField.getText();
            current = client.parseCurrent(client.makeAPICallForCurrentWeather(selectedZipCode));

            if (text.equals("Submit")) {
                temperature.setText("Temperature: " + current.getTempF());
                condition.setText("Current Conditions: " + current.getCurrentCondition());
                try {
                    URL imageURL = new URL("https:" + current.getCurrentIcon());
                    BufferedImage image = ImageIO.read(imageURL);
                    imageLabel.setIcon(new ImageIcon(image));
                } catch (IOException x) {
                    System.out.println(x.getMessage());
                }
            } else if (text.equals("Clear")) {
                zipCodeEntryField.setText("");
                temperature.setText("");
                condition.setText("");
                imageLabel.setIcon(new ImageIcon());
            }
        } else if (e.getSource() instanceof JCheckBox) {
            JCheckBox check = (JCheckBox) e.getSource();
            if(check.isSelected())
            {
                temperature.setText("Temperature: " + current.getTempC());
            }
            else
            {
                temperature.setText("Temperature: " + current.getTempF());
            }
        }
    }
}