package quiz.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {

    JButton rules, back;
    JTextField tfname;

    Login() {
        setupFrame();
        setupBackground();
        setupHeading();
        setupNameField();
        setupButtons();
        
        setVisible(true);
    }

    private void setupFrame() {
        setTitle("QuiZeal - Login");
        getContentPane().setBackground(new Color(240, 240, 240));
        setLayout(null);
        setSize(1200, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setupBackground() {
        // Load and scale image to fit the frame
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/Login.jpg"));
        Image image = imageIcon.getImage().getScaledInstance(600, 500, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setBounds(0, 0, 600, 500);
        add(imageLabel);
    }

    private void setupHeading() {
        JLabel heading = new JLabel("QuiZeal");
        heading.setBounds(650, 60, 400, 60);
        heading.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 48));
        heading.setForeground(new Color(5, 10, 48));
        add(heading);
    }

    private void setupNameField() {
        JLabel name = new JLabel("Enter the name of the player");
        name.setBounds(650, 150, 400, 30);
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setForeground(new Color(5, 10, 48));
        add(name);

        tfname = new JTextField();
        tfname.setBounds(650, 200, 350, 35);
        tfname.setFont(new Font("Arial", Font.PLAIN, 18));
        tfname.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(5, 10, 48), 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        add(tfname);
    }

    private void setupButtons() {
        rules = createButton("Go to Rules", 650, 270, this);
        add(rules);

        back = createButton("Back", 820, 270, this);
        add(back);
    }

    private JButton createButton(String text, int x, int y, ActionListener listener) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 150, 40);
        button.setBackground(new Color(5, 10, 48));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(5, 10, 48), 2));
        button.addActionListener(listener);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor on hover
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == rules) {
            String name = tfname.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            setVisible(false);
            new Rules(name);  // Pass the name to Rules class
        } else if (ae.getSource() == back) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
