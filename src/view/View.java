package view;

import controller.Controller;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import model.Question;
import model.User;

public class View extends JFrame {

    private Controller controller;

    LinkedHashMap<String, ArrayList<Question>> model;    // SE ALMAZENAN TODAS LAS PREGUNTAS Y SU CATEGORIA.
    User[] users = new User[2];  // SE ALMACENARAN LOS USUARIOS PARA SACARLOS POR EL USERPANEL.

    private JLabel[] categoryNames = new JLabel[6];
    private JButton[][] buttonArray = new JButton[5][6];

    private User currentUser;

    private String p1;
    private String currentTurnInf;
    private String p2;

    private JLabel player1;
    private JLabel currentTurnLabel;
    private JLabel player2;

    public View(LinkedHashMap<String, ArrayList<Question>> model, User[] users) {

        this.model = model;
        this.users = users;

        this.setTitle("JEOPARDY");
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addComponentsToPane(this.getContentPane());
        this.setResizable(false);
        this.setSize(new Dimension(900, 500));

        this.setVisible(true);
    }

    private void addComponentsToPane(Container panel) {

        // INICIALIZAMOS EL JUGADOR ACTUAL
        currentUser = users[0];

        p1 = "<html><body> Jugador : " + users[0].getName() + " <br> Puntuacion actual : " + users[0].getScore() + "</body></html>";
        currentTurnInf = " Le toca a : " + currentUser.getName();
        p2 = "<html><body> Jugador : " + users[1].getName() + " <br> Puntuacion actual : " + users[1].getScore() + "</body></html>";

        Set<String> names = model.keySet();

        // CREACION DE LOS 6 LABELS CON LAS CATEGORIAS
        Iterator<String> iterator = names.iterator();

        int it = 0;
        while (iterator.hasNext()) {
            categoryNames[it] = new JLabel(iterator.next(), SwingConstants.CENTER);
            categoryNames[it].setBackground(java.awt.Color.white);
            categoryNames[it].setOpaque(true);
            Border b = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
            Dimension d = categoryNames[it].getPreferredSize();
            d.height = 60;
            categoryNames[it].setBorder(b);
            categoryNames[it].setPreferredSize(d);
            it++;
        }

        // CREACION DEL PANEL CON LAS CATEGORIAS
        JPanel categoryPanel = new JPanel();

        categoryPanel.setLayout(new GridLayout(1, 6));

        for (int i = 0; i < categoryNames.length; i++) {
            categoryPanel.add(categoryNames[i]);
        }

        // CREACION DEL PANEL CON LOS BOTONES DE LAS PREGUNTAS
        JPanel questionPanel = new JPanel();

        questionPanel.setLayout(new GridLayout(5, 6));

        String[] normalButtonName = {"100 puntos", "200 puntos", "300 puntos", "400 puntos", "500 puntos"};

        for (int i = 0; i < normalButtonName.length; i++) {
            for (int j = 0; j < 6; j++) {
                buttonArray[i][j] = new JButton(normalButtonName[i]);
                questionPanel.add(buttonArray[i][j]);
            }
        }

        // CREACION PANEL DE INFORMACION DE LOS JUADORES
        JPanel userPanel = new JPanel();

        userPanel.setLayout(new GridLayout(1, 3));

        player1 = new JLabel(p1, SwingConstants.CENTER);
        currentTurnLabel = new JLabel(currentTurnInf, SwingConstants.CENTER);
        player2 = new JLabel(p2, SwingConstants.CENTER);

        Border b = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        Dimension d = player1.getPreferredSize();
        d.height = 60;

        player1.setBackground(java.awt.Color.white);
        player1.setOpaque(true);
        currentTurnLabel.setBackground(java.awt.Color.white);
        currentTurnLabel.setOpaque(true);
        player2.setBackground(java.awt.Color.white);
        player2.setOpaque(true);

        player1.setBorder(b);
        currentTurnLabel.setBorder(b);
        player2.setBorder(b);

        player1.setPreferredSize(d);
        currentTurnLabel.setPreferredSize(d);
        player2.setPreferredSize(d);

        userPanel.add(player1);
        userPanel.add(currentTurnLabel);
        userPanel.add(player2);

        panel.add(categoryPanel, BorderLayout.PAGE_START);
        panel.add(questionPanel, BorderLayout.CENTER);
        panel.add(userPanel, BorderLayout.PAGE_END);

    }

    public void addListeners(Controller controller) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                buttonArray[i][j].addActionListener(controller);
            }
        }
        this.addWindowListener(controller);
    }

    public void changeCurrentUser() {
        if (currentUser.equals(users[0])) {
            currentUser = users[1];
        } else {
            currentUser = users[0];
        }
        currentTurnInf = " Le toca a : " + currentUser.getName();
        currentTurnLabel.setText(currentTurnInf);
    }

    public void addTurn() {
        if (currentUser.equals(users[0])) {
            users[0].increaseTurn();
        } else {
            users[1].increaseTurn();
        }
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public User[] getUsers() {
        return this.users;
    }

    public JLabel[] getCategoryNames() {
        return this.categoryNames;
    }

    public JButton[][] getButtonArray() {
        return this.buttonArray;
    }

    public void disableButton(int i, int j) {

        buttonArray[i][j].setEnabled(false);

    }

    public void changePlayerScore(int answerValue) {
        currentUser.changeScore(answerValue);
    }

    public void refreshScore() {
        p1 = "<html><body> Jugador : " + users[0].getName() + " <br> Puntuacion actual : " + users[0].getScore() + "</body></html>";
        p2 = "<html><body> Jugador : " + users[1].getName() + " <br> Puntuacion actual : " + users[1].getScore() + "</body></html>";
        player1.setText(p1);
        player2.setText(p2);
    }

    public void doubleRound() {
        String[] doubleRoundButtonName = {"200", "400", "600", "800", "1000"};
        for (int i = 0; i < doubleRoundButtonName.length; i++) {
            for (int j = 0; j < 6; j++) {
                buttonArray[i][j].setText(doubleRoundButtonName[i]);
            }
        }
    }

    public void isDoubleRound() {
        if (currentUser.getTurn() == 14) {
            this.doubleRound();
        }
    }

    public String getWinner() {
        if (users[0].getScore() > users[1].getScore()) {
            return "El ganador es " + users[0].getName() + " con una puntación de " + users[0].getScore() + " puntos";
        } else {
            if (users[1].getScore() > users[0].getScore()) {
                return "El ganador es " + users[1].getName() + " con una puntación de " + users[1].getScore() + " puntos";
            } else {
                return "Es un empate !";
            }
        }
    }
}
