package com.tharindu.smartWatch;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;

// Smartwatch Builder Pattern
class Smartwatch {
    String brand, model, strap;
    boolean hasHeartRate, hasGPS;
    double screenSize, price;

    public Smartwatch(String brand, String model, boolean hasHeartRate, boolean hasGPS,
                      double screenSize, String strap, double price) {
        this.brand = brand;
        this.model = model;
        this.hasHeartRate = hasHeartRate;
        this.hasGPS = hasGPS;
        this.screenSize = screenSize;
        this.strap = strap;
        this.price = price;
    }

    public String toString() {
        return "⚙️ Smartwatch Configuration:\n" +
                "----------------------------\n" +
                "Brand      : " + brand + "\n" +
                "Model      : " + model + "\n" +
                "Heart-Rate : " + (hasHeartRate ? "Yes" : "No") + "\n" +
                "GPS        : " + (hasGPS ? "Yes" : "No") + "\n" +
                "Screen     : " + String.format("%.1f", screenSize) + " in\n" +
                "Strap      : " + strap + "\n" +
                "Price      : Rs. " + String.format("%.2f", price) + "\n";
    }
}

class SmartwatchBuilder {
    private String brand = "Unknown", model = "Basic", strap = "Silicone";
    private boolean hasHeartRate = false, hasGPS = false;
    private double screenSize = 1.5, price = 5000;

    public SmartwatchBuilder setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public SmartwatchBuilder setModel(String model) {
        this.model = model;
        return this;
    }

    public SmartwatchBuilder setHasHeartRate(boolean hasHeartRate) {
        this.hasHeartRate = hasHeartRate;
        return this;
    }

    public SmartwatchBuilder setHasGPS(boolean hasGPS) {
        this.hasGPS = hasGPS;
        return this;
    }

    public SmartwatchBuilder setScreenSize(double screenSize) {
        this.screenSize = screenSize;
        return this;
    }

    public SmartwatchBuilder setStrap(String strap) {
        this.strap = strap;
        return this;
    }

    public SmartwatchBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public Smartwatch build() {
        return new Smartwatch(brand, model, hasHeartRate, hasGPS, screenSize, strap, price);
    }
}

public class SmartwatchBuilderApp extends JFrame {
    private JComboBox<String> brandCombo, modelCombo, strapCombo;
    private JCheckBox hrCheck, gpsCheck;
    private JSpinner screenSpinner, priceSpinner;
    private JTextArea outputArea;

    public SmartwatchBuilderApp() {
        // Set Nimbus Look & Feel
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Fallback silently
        }

        setTitle("🕹️ Smartwatch Builder");
        setSize(950, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        URL bgUrl = getClass().getClassLoader().getResource("tech_bg.jpg");
        Image bgImg = bgUrl != null ? new ImageIcon(bgUrl).getImage() : null;
        BackgroundPanel bgPanel = new BackgroundPanel(bgImg);
        bgPanel.setLayout(new BorderLayout());
        setContentPane(bgPanel);

        // Input Panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setPreferredSize(new Dimension(360, 0));
        inputPanel.setBorder(new EmptyBorder(25, 25, 25, 25));
        inputPanel.setBackground(new Color(25, 25, 25, 230));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        int y = 0;
        // Title
        gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 2;
        JLabel title = new JLabel("⌚ Customize Smartwatch");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(new Color(0, 255, 255));
        inputPanel.add(title, gbc);

        // Brand
        y++; gbc.gridy = y; gbc.gridwidth = 1; gbc.gridx = 0;
        inputPanel.add(styledLabel("Brand:"), gbc);
        brandCombo = new JComboBox<>(new String[]{"ChronosTech", "PulsePro", "NeoWatch"});
        styleCombo(brandCombo);
        gbc.gridx = 1;
        inputPanel.add(brandCombo, gbc);

        // Model
        y++; gbc.gridy = y; gbc.gridx = 0;
        inputPanel.add(styledLabel("Model:"), gbc);
        modelCombo = new JComboBox<>(new String[]{"Standard", "Sport", "Elite"});
        styleCombo(modelCombo);
        gbc.gridx = 1;
        inputPanel.add(modelCombo, gbc);

        // Sensors
        y++; gbc.gridy = y; gbc.gridx = 0; gbc.gridwidth = 2;
        hrCheck = styledCheckBox("Heart-Rate Sensor");
        inputPanel.add(hrCheck, gbc);
        y++; gbc.gridy = y;
        gpsCheck = styledCheckBox("GPS");
        inputPanel.add(gpsCheck, gbc);

        // Screen size
        y++; gbc.gridy = y; gbc.gridwidth = 1; gbc.gridx = 0;
        inputPanel.add(styledLabel("Screen (in):"), gbc);
        screenSpinner = new JSpinner(new SpinnerNumberModel(1.8, 1.0, 2.5, 0.1));
        styleSpinner(screenSpinner);
        gbc.gridx = 1;
        inputPanel.add(screenSpinner, gbc);

        // Strap type
        y++; gbc.gridy = y; gbc.gridx = 0;
        inputPanel.add(styledLabel("Strap:"), gbc);
        strapCombo = new JComboBox<>(new String[]{"Silicone", "Leather", "Metal"});
        styleCombo(strapCombo);
        gbc.gridx = 1;
        inputPanel.add(strapCombo, gbc);

        // Price
        y++; gbc.gridy = y; gbc.gridx = 0;
        inputPanel.add(styledLabel("Price (Rs):"), gbc);
        priceSpinner = new JSpinner(new SpinnerNumberModel(5000, 1000, 20000, 500));
        styleSpinner(priceSpinner);
        gbc.gridx = 1;
        inputPanel.add(priceSpinner, gbc);

        // Build button
        y++; gbc.gridy = y; gbc.gridx = 0; gbc.gridwidth = 2;
        JButton buildBtn = new JButton("⚙️ Build Watch");
        buildBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
        buildBtn.setBackground(new Color(0, 200, 255));
        buildBtn.setForeground(Color.BLACK);
        buildBtn.addActionListener(this::onBuild);
        inputPanel.add(buildBtn, gbc);

        bgPanel.add(inputPanel, BorderLayout.WEST);

        // Output area
        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        outputPanel.setBackground(new Color(0, 0, 0, 160));

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 26));
        outputArea.setBackground(Color.BLACK);
        outputArea.setForeground(new Color(0, 255, 180));
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        outputPanel.add(scrollPane, BorderLayout.CENTER);

        bgPanel.add(outputPanel, BorderLayout.CENTER);
    }

    private void onBuild(ActionEvent e) {
        Smartwatch watch = new SmartwatchBuilder()
                .setBrand((String) brandCombo.getSelectedItem())
                .setModel((String) modelCombo.getSelectedItem())
                .setHasHeartRate(hrCheck.isSelected())
                .setHasGPS(gpsCheck.isSelected())
                .setScreenSize(((Number) screenSpinner.getValue()).doubleValue())
                .setStrap((String) strapCombo.getSelectedItem())
                .setPrice(((Number) priceSpinner.getValue()).doubleValue())
                .build();

        outputArea.setText(watch.toString());
    }

    // GUI Utility methods
    private JLabel styledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.PLAIN, 16));
        label.setForeground(Color.WHITE);
        return label;
    }

    private JCheckBox styledCheckBox(String text) {
        JCheckBox cb = new JCheckBox(text);
        cb.setOpaque(false);
        cb.setFont(new Font("SansSerif", Font.PLAIN, 15));
        cb.setForeground(new Color(0, 255, 255));
        return cb;
    }

    private void styleCombo(JComboBox<?> combo) {
        combo.setFont(new Font("SansSerif", Font.PLAIN, 15));
        combo.setBackground(Color.BLACK);
        combo.setForeground(Color.CYAN);
        combo.setRenderer(new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                c.setBackground(isSelected ? new Color(0, 255, 255) : Color.BLACK);
                c.setForeground(isSelected ? Color.BLACK : Color.CYAN);
                return c;
            }
        });
    }

    private void styleSpinner(JSpinner spinner) {
        spinner.setFont(new Font("SansSerif", Font.PLAIN, 15));
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            JTextField tf = ((JSpinner.DefaultEditor) editor).getTextField();
            tf.setForeground(Color.BLACK);
            tf.setCaretColor(Color.CYAN);
        }
    }

    // Background Panel
    static class BackgroundPanel extends JPanel {
        private final Image bg;

        BackgroundPanel(Image bg) {
            this.bg = bg;
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (bg != null) {
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SmartwatchBuilderApp().setVisible(true));
    }
}