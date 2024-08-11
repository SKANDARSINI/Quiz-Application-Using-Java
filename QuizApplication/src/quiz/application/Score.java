package quiz.application;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Score extends JFrame implements ActionListener {
    
    private final int FRAME_WIDTH = 750;
    private final int FRAME_HEIGHT = 550;
    private final int IMAGE_WIDTH = 1587;
    private final int IMAGE_HEIGHT = 2245;

    Score(String name, int score) {
        setTitle("Quiz Result");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

       
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/final_score.jpg"));
        Image image = imageIcon.getImage();

        
        double scaleFactorWidth = (double) FRAME_WIDTH / IMAGE_WIDTH;
        double scaleFactorHeight = (double) FRAME_HEIGHT / IMAGE_HEIGHT;
        double scaleFactor = Math.min(scaleFactorWidth, scaleFactorHeight);

        
        int newImageWidth = (int) (IMAGE_WIDTH * scaleFactor);
        int newImageHeight = (int) (IMAGE_HEIGHT * scaleFactor);

       
        Image scaledImage = image.getScaledInstance(newImageWidth, newImageHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);

        
        imageLabel.setBounds(0, 0, newImageWidth, newImageHeight);
        add(imageLabel);

       
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

       
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel heading = new JLabel("Thank you " + name + "! ");
        heading.setFont(new Font("Sans Serif", Font.BOLD, 24));
        heading.setForeground(new Color(8,13,58));
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        rightPanel.add(heading, gbc);

        gbc.gridy = 1;
        JLabel lblscore = new JLabel("Your score is " + score);
        lblscore.setFont(new Font("Sans Serif", Font.BOLD, 20));
        lblscore.setForeground(new Color(8,13,58));
        lblscore.setHorizontalAlignment(SwingConstants.CENTER);
        rightPanel.add(lblscore, gbc);

        gbc.gridy = 2;
        JButton submit = new JButton("Play Again");
        submit.setFont(new Font("Sans Serif", Font.BOLD, 18));
        submit.setBackground(new Color(8,13,58));
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        rightPanel.add(submit, gbc);

       
        rightPanel.setBounds(newImageWidth, 0, FRAME_WIDTH - newImageWidth, FRAME_HEIGHT);
        add(rightPanel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Login();
    }

    public static void main(String[] args) {
        new Score("User", 0);
    }
}
