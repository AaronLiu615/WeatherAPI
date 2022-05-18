import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class WeatherGUI implements ActionListener {
    private JTextField zipCodeEntryField;
    private WeatherNetworking client;

    public WeatherGUI() {
        zipCodeEntryField = new JTextField();
        client = new WeatherNetworking();

        setupGui();
    }

    private void setupGui()
    {
        JFrame frame = new JFrame("Current Weather");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel entryPanel = new JPanel();
        JLabel zip = new JLabel("Enter Zip Code: ");
        zipCodeEntryField = new JTextField(10);
        JButton submitButton = new JButton("Submit");
        JButton clearButton = new JButton("Clear");
        JCheckBox celsiusShow = new JCheckBox("Show Celsius");
        entryPanel.add(submitButton);
        entryPanel.add(clearButton);
        entryPanel.add(zipCodeEntryField);
        entryPanel.add(zip);
        entryPanel.add(celsiusShow);

        frame.add(entryPanel, BorderLayout.SOUTH);
        submitButton.addActionListener(this);
        clearButton.addActionListener(this);
        celsiusShow.addItemListener((ItemListener) this);

        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        zipCodeEntryField.setText("");
        JButton button = (JButton) (e.getSource());
        String text = button.getText();

        if (text.equals("Submit")) {
            String selectedZipCode = zipCodeEntryField.getText();

            String degrees = client.makeAPICallForCurrentWeather(selectedZipCode);

        }
        else if(text.equals("Show Celsius"))
        {

        }
        else if (text.equals("Clear"))
        {
            zipCodeEntryField.setText("");
        }
    }
}