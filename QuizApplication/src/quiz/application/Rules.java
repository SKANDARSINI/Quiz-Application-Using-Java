package quiz.application;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Rules extends JFrame implements ActionListener {

    String name;
    JButton start, back;
    
    Rules(String name) {
        this.name = name;
        setTitle("Quiz Rules");
        setSize(800, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(5, 10, 48));
        setLayout(new BorderLayout());

        // Heading
        JLabel heading = new JLabel("Welcome " + name + " to QuiZeal");
        heading.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 32));
        heading.setForeground(new Color(255, 255, 255));
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        add(heading, BorderLayout.NORTH);
        
        // Rules Text
        JLabel rules = new JLabel();
        rules.setFont(new Font("Arial", Font.PLAIN, 18));
        rules.setForeground(new Color(50, 50, 50));
        rules.setText(
            "<html>" +
                "<div style='text-align: justify; margin: 20px;'>" +
                "<h2>Eligibility:</h2>" +
                "<p>Participants must be registered to take part in the quiz.</p><br>" +
                "<h2>Time Limit:</h2>" +
                "<p>Each quiz or question may have a time limit to ensure prompt responses.</p><br>" +
                "<h2>Question Format:</h2>" +
                "<p>Questions are of multiple-choice format.</p><br>" +
                "<h2>Answer Submission:</h2>" +
                "<p>Answers must be submitted within the allotted time. Once submitted, answers cannot be changed.</p><br>" +
                "<h2>Scoring:</h2>" +
                "<p>Correct answers earn points (1 point per correct answer).</p><br>" +
                "<h2>Conduct:</h2>" +
                "<p>Participants must adhere to a code of conduct, including no cheating or using unauthorized resources. Any form of cheating or dishonest behavior will result in disqualification.</p><br>" +
                "<h2>Technical Issues:</h2>" +
                "<p>In case of technical issues during the quiz, participants should report the problem to support immediately. A reattempt or extension may be provided at the discretion of the platform.</p>" +
                "</div>" +
            "</html>"
        );
        
        // Adding rules text to a scroll pane
        JScrollPane scrollPane = new JScrollPane(rules);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // No horizontal scroll
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);
        
        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);

        back = new JButton("Back");
        back.setFont(new Font("Arial", Font.BOLD, 16));
        back.setForeground(Color.WHITE);
        back.setBackground(new Color(5, 10, 48));
        back.setFocusPainted(false);
        back.setBorder(new RectangularBorder(10));
        back.setPreferredSize(new Dimension(120, 40));
        back.addActionListener(this);
        buttonPanel.add(back);
        
        start = new JButton("Start");
        start.setFont(new Font("Arial", Font.BOLD, 16));
        start.setForeground(Color.WHITE);
        start.setBackground(new Color(5, 10, 48));
        start.setFocusPainted(false);
        start.setBorder(new RectangularBorder(10));
        start.setPreferredSize(new Dimension(120, 40));
        start.addActionListener(this);
        buttonPanel.add(start);
        
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == start) {
            setVisible(false);
            new Quiz(name);
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Login();
        }
    }
    
    public static void main(String[] args) {
        new Rules("User");
    }

    // Custom border class for rectangular buttons
    class RectangularBorder extends AbstractBorder {
        private int radius;

        RectangularBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 10, this.radius + 10, this.radius + 10, this.radius + 10);
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(c.getForeground());
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
}
