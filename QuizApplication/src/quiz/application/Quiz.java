package quiz.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class Quiz extends JFrame implements ActionListener {
    private String[][] questions = new String[10][5];
    private String[][] answers = new String[10][2];
    private String[][] userAnswers = new String[10][1];
    private JLabel qno, question, timerLabel;
    private JRadioButton opt1, opt2, opt3, opt4;
    private ButtonGroup groupOptions;
    private JButton next, submit, lifeline;

    private int timer = 15;
    private int count = 0;
    private int score = 0;
    private boolean lifelineUsed = false;

    private Timer timerObject;

    String name;

    public Quiz(String name) {
        this.name = name;
        setupFrame();
        setupComponents();
        initializeQuestions();
        start(count);
        setVisible(true);
    }

    private void setupFrame() {
        setTitle("Quiz Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE); // Updated background color
        setLayout(null);
    }

    private void setupComponents() {
        // Header Image
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/icons/Cover.jpg"));
        if (imageIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            System.out.println("Image could not be loaded.");
            return;
        }
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(700, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setBounds(50, 20, 700, 150);
        add(imageLabel);

        // Question Number
        qno = new JLabel();
        qno.setBounds(50, 200, 100, 80);
        qno.setFont(new Font("Sans Serif", Font.BOLD, 24));
        qno.setForeground(new Color(0, 51, 102));
        add(qno);

        // Question Text
        question = new JLabel();
        question.setBounds(160, 200, 600, 85);
        question.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        question.setForeground(new Color(0, 51, 102));
        question.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding added
        question.setOpaque(true);
        question.setBackground(Color.WHITE);
        add(question);

        // Option Buttons
        opt1 = createOptionButton();
        opt1.setBounds(160, 270, 600, 45);
        add(opt1);

        opt2 = createOptionButton();
        opt2.setBounds(160, 320, 600, 45);
        add(opt2);

        opt3 = createOptionButton();
        opt3.setBounds(160, 370, 600, 45);
        add(opt3);

        opt4 = createOptionButton();
        opt4.setBounds(160, 420, 600, 45);
        add(opt4);

        groupOptions = new ButtonGroup();
        groupOptions.add(opt1);
        groupOptions.add(opt2);
        groupOptions.add(opt3);
        groupOptions.add(opt4);

        // Buttons
        next = createButton("Next", this);
        next.setBounds(160, 480, 150, 50);
        add(next);

        lifeline = createButton("50-50 Lifeline", this);
        lifeline.setBounds(320, 480, 200, 50);
        add(lifeline);

        submit = createButton("Submit", this);
        submit.setBounds(540, 480, 150, 50);
        submit.setEnabled(false);
        add(submit);

        // Timer Label
        timerLabel = new JLabel("Time Left: " + timer + "s");
        timerLabel.setBounds(600, 180, 150, 50);
        timerLabel.setFont(new Font("Sans Serif", Font.BOLD, 22));
        timerLabel.setForeground(new Color(0, 51, 102));
        timerLabel.setOpaque(true);
        timerLabel.setBackground(Color.LIGHT_GRAY); // Light background for visibility
        timerLabel.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2)); // Border added
        add(timerLabel);
    }

    private JRadioButton createOptionButton() {
        JRadioButton option = new JRadioButton();
        option.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        option.setForeground(new Color(0, 51, 102));
        option.setBackground(Color.WHITE); // No background color for options
        option.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 1)); // Border added
        option.setFocusPainted(false);
        option.setPreferredSize(new Dimension(600, 45)); // Larger clickable area
        return option;
    }

    private JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Sans Serif", Font.BOLD, 18));
        button.setBackground(new Color(0, 51, 102));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
        button.setPreferredSize(new Dimension(150, 50)); // Button size
        button.addActionListener(listener);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 61, 115)); // Darker color on hover
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 51, 102)); // Original color
            }
        });
        return button;
    }

    private void initializeQuestions() {
        questions[0] = new String[]{"What is the size of an int in Java?", "16 bits", "32 bits", "64 bits", "8 bits"};
        questions[1] = new String[]{"What is the default value of a boolean variable in Java?", "true", "false", "null", "0"};
        questions[2] = new String[]{"Which package contains the Random class?", "java.util package", "java.lang package", "java.awt package", "java.io package"};
        questions[3] = new String[]{"Which of the keywords is used to prevent method overriding?", "static", "final", "private", "abstract"};
        questions[4] = new String[]{"Where is a string stored in memory when created with the new operator?", "Stack", "String memory", "Random storage space", "Heap memory"};
        questions[5] = new String[]{"Which of the following methods is used to start a thread in Java?", "run()", "start()", "init()", "execute()"};
        questions[6] = new String[]{"Which keyword is used for accessing the features of a package?", "import", "package", "extends", "export"};
        questions[7] = new String[]{"What is the result of the following expression in Java? - 'System.out.println(10 / 3);'", "3.0", "3", "3.333", "3.4"};
        questions[8] = new String[]{"Which exception is thrown for an invalid array index in Java?", "ArrayIndexOutOfBoundsException", "NullPointerException", "IndexOutOfBoundsException", "IllegalArgumentException"};
        questions[9] = new String[]{"What does the super keyword refer to in Java?", "The parent class of the current object", "The current class", "The sibling class", "The object created by the new keyword"};
       
        answers[0] = new String[]{"32 bits"};
        answers[1] = new String[]{"false"};
        answers[2] = new String[]{"java.util package"};
        answers[3] = new String[]{"final"};
        answers[4] = new String[]{"Heap memory"};
        answers[5] = new String[]{"start()"};
        answers[6] = new String[]{"import"};
        answers[7] = new String[]{"3"};
        answers[8] = new String[]{"ArrayIndexOutOfBoundsException"};
        answers[9] = new String[]{"The parent class of the current object"};
    }

    private void start(int count) {
        qno.setText((count + 1) + ". ");
        question.setText(questions[count][0]);
        opt1.setText(questions[count][1]);
        opt1.setActionCommand(questions[count][1]);
        opt2.setText(questions[count][2]);
        opt2.setActionCommand(questions[count][2]);
        opt3.setText(questions[count][3]);
        opt3.setActionCommand(questions[count][3]);
        opt4.setText(questions[count][4]);
        opt4.setActionCommand(questions[count][4]);
        groupOptions.clearSelection();

        if (count == questions.length - 1) {
            next.setEnabled(false);
            submit.setEnabled(true);
        }

        timer = 15;  // Reset the timer for each question
        timerLabel.setText("Time Left: " + timer + "s");
        initializeTimer();
        lifeline.setEnabled(true);  // Re-enable the lifeline button for the new question
        lifelineUsed = false;  // Reset lifeline usage for each question

        // Reset all options to enabled
        opt1.setEnabled(true);
        opt2.setEnabled(true);
        opt3.setEnabled(true);
        opt4.setEnabled(true);
    }

    private void initializeTimer() {
        if (timerObject != null) {
            timerObject.stop();
        }

        timerObject = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer--;
                timerLabel.setText("Time Left: " + timer + "s");
                if (timer <= 0) {
                    timerObject.stop();
                    recordAnswer();
                    nextQuestion();
                }
            }
        });
        timerObject.start();
    }

    private void recordAnswer() {
        if (groupOptions.getSelection() != null) {
            userAnswers[count][0] = groupOptions.getSelection().getActionCommand();
        } else {
            userAnswers[count][0] = "";  // No selection
        }
    }

    private void nextQuestion() {
        count++;
        if (count == questions.length) {
            showResult();
        } else {
            start(count);
        }
    }

    private void applyLifeline() {
        ArrayList<JRadioButton> options = new ArrayList<>();
        options.add(opt1);
        options.add(opt2);
        options.add(opt3);
        options.add(opt4);

        String correctAnswer = answers[count][0];
        ArrayList<JRadioButton> incorrectOptions = new ArrayList<>();

        for (JRadioButton option : options) {
            if (!option.getActionCommand().equals(correctAnswer)) {
                incorrectOptions.add(option);
            }
        }

        Collections.shuffle(incorrectOptions);
        for (int i = 0; i < 2 && i < incorrectOptions.size(); i++) {
            incorrectOptions.get(i).setEnabled(false);
        }

        lifeline.setEnabled(false);
        lifelineUsed = true;
    }

    private void showResult() {
        // Calculate the final score
        for (int i = 0; i < userAnswers.length; i++) {
            if (userAnswers[i][0].equals(answers[i][0])) {
                score++;
            }
        }
    
        // Hide the current frame
        setVisible(false);
    
        // Create and show the Score frame
        new Score(name, score);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next) {
            timerObject.stop();
            recordAnswer();
            nextQuestion();
        } else if (e.getSource() == lifeline) {
            if (!lifelineUsed) {
                applyLifeline();
            }
        } else if (e.getSource() == submit) {
            timerObject.stop();
            recordAnswer();
            showResult();
        }
    }

    public static void main(String[] args) {
        new Quiz("Player");
    }
}
