package view;

import controller.QuestionController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import model.Question;

public class QuestionView extends JFrame {

    private Question model;
    private String[] answers;
    private JRadioButton answer1;
    private JRadioButton answer2;
    private JRadioButton answer3;
    private JButton tryButton;

    public QuestionView(Question model) {

        this.model = model;

        answers = model.getAnswers();

        this.setTitle("Jeopardy - Question");

        addComponentsToPane(this.getContentPane());

        this.setResizable(false);
        this.setVisible(true);
        this.pack();

    }

    private void addComponentsToPane(Container panel) {

        JLabel questionStatement = new JLabel(model.getStatement());
        questionStatement.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel answerPanel = new JPanel();

        answerPanel.setLayout(new GridLayout(3, 1));

        answer1 = new JRadioButton(answers[0]);
        answer2 = new JRadioButton(answers[1]);
        answer3 = new JRadioButton(answers[2]);

        answer1.setOpaque(true);
        answer2.setOpaque(true);
        answer3.setOpaque(true);

        answerPanel.add(answer1);
        answerPanel.add(answer2);
        answerPanel.add(answer3);

        ButtonGroup radioButtonGroup = new ButtonGroup();

        radioButtonGroup.add(answer1);
        radioButtonGroup.add(answer2);
        radioButtonGroup.add(answer3);

        JPanel buttonPanel = new JPanel();
        tryButton = new JButton("Comprovar");
        tryButton.setPreferredSize(new Dimension(120, 30));
        buttonPanel.add(tryButton);

        this.add(questionStatement, BorderLayout.PAGE_START);
        this.add(answerPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.PAGE_END);

    }

    public void addListeners(QuestionController questionController) {

        tryButton.addActionListener(questionController);

    }

    public int getAnswerSelected() {
        if (answer1.isSelected()) {
            return 0;
        } else {
            if (answer2.isSelected()) {
                return 1;
            } else {
                if (answer3.isSelected()) {
                    return 2;
                } else {
                    return 3;
                }
            }
        }
    }

    public void changeBgColorToGreen(int answer) {
        if (answer == 0) {
            answer1.setBackground(Color.GREEN);
        }
        if (answer == 1) {
            answer2.setBackground(Color.GREEN);
        }
        if (answer == 2) {
            answer3.setBackground(Color.GREEN);
        }
    }

    public void changeBgColorToRed(int answer) {
        if (answer == 0) {
            answer1.setBackground(Color.red);
        }
        if (answer == 1) {
            answer2.setBackground(Color.red);
        }
        if (answer == 2) {
            answer3.setBackground(Color.red);
        }
    }

    public void changeButtonTxt() {
        tryButton.setText("Siguiente");
    }
}
