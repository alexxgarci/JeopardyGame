package view;

import controller.StartController;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Category;
import model.User;

public class StartView extends JFrame {

    private StartController startController;

    private Category[] categories = new Category[9];
    private JButton[] categoryButtons = new JButton[9];

    private JLabel labelPlayer1;
    private JLabel labelPlayer2;
    private JTextField textPlayer1;
    private JTextField textPlayer2;

    private User[] users = new User[2];

    public StartView(Category[] categories) {

        this.categories = categories;

        this.setTitle("Jeopardy");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        addComponentsToPane(this.getContentPane());
        
        this.setResizable(false);
        this.setVisible(true);
        this.pack();

    }

    private void addComponentsToPane(Container panel) {

        Dimension dButtons = new Dimension(90, 50);
        GridLayout gLayout = new GridLayout(2, 2);
        gLayout.setHgap(10);
        gLayout.setVgap(10);

        JPanel panelSuperior = new JPanel();
        panelSuperior.setSize(500, 60);

        panelSuperior.setLayout(gLayout);

        labelPlayer1 = new JLabel("Jugador 1: ");
        labelPlayer2 = new JLabel("Jugador 2: ");
        textPlayer1 = new JTextField();
        textPlayer2 = new JTextField();

        panelSuperior.add(labelPlayer1);
        panelSuperior.add(labelPlayer2);
        panelSuperior.add(textPlayer1);
        panelSuperior.add(textPlayer2);

        JLabel selectCategoryLabel = new JLabel("Selecciona 6 tematicas con las que quieres jugar:");
        selectCategoryLabel.setPreferredSize(new Dimension(500, 30));

        JPanel panelInferior = new JPanel();
        panelInferior.setSize(500, 200);

        panelInferior.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            categoryButtons[i] = new JButton(categories[i].getCategoryName());
            categoryButtons[i].setPreferredSize(dButtons);
            panelInferior.add(categoryButtons[i]);
        }

        this.add(panelSuperior, BorderLayout.PAGE_START);
        this.add(selectCategoryLabel, BorderLayout.CENTER);
        this.add(panelInferior, BorderLayout.PAGE_END);
    }

    public void addListeners(StartController startController) {
        for (int i = 0; i < categoryButtons.length; i++) {
            categoryButtons[i].addActionListener(startController);
        }
    }

    public JButton[] getCategoryButtons() {
        return this.categoryButtons;
    }

    public void disableButton(int i) {

        categoryButtons[i].setEnabled(false);

    }

    public String getPlayer1Name() {
        if (textPlayer1.getText().isEmpty()) {
            return "Player1";
        } else {
            return textPlayer1.getText();
        }
    }

    public String getPlayer2Name() {
        if (textPlayer2.getText().isEmpty()) {
            return "Player2";
        } else {
            return textPlayer2.getText();
        }
    }

}
