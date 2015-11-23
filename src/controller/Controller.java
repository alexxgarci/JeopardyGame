package controller;

import auxiliar.FileOperations;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.swing.JButton;
import model.Question;
import view.ExitView;
import view.QuestionView;
import view.View;

public class Controller implements ActionListener, WindowListener {

    private View view;
    private QuestionView questionView;
    private QuestionController questionController;
    private Question question;
    LinkedHashMap<String, ArrayList<Question>> model;
    private JButton[][] buttonArray;

    public Controller(View view, LinkedHashMap<String, ArrayList<Question>> model) {

        this.view = view;
        this.model = model;
        view.addListeners(this);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        buttonArray = view.getButtonArray();
        for (int i = 0; i < buttonArray.length; i++) {
            for (int j = 0; j < buttonArray[i].length; j++) {
                if (buttonArray[i][j] == ae.getSource()) {
                    view.isDoubleRound();
                    question = model.get(view.getCategoryNames()[j].getText()).get(i);
                    questionView = new QuestionView(question);
                    questionController = new QuestionController(questionView, question, view);
                    view.disableButton(i, j);

                }
            }
        }
    }

    @Override
    public void windowOpened(WindowEvent we) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent we) {

        FileOperations.writeScore(view.getUsers());
        String frase = view.getWinner();
        ExitView exitView = new ExitView(frase);
        ExitController exitController = new ExitController(exitView, frase);
    }

    @Override
    public void windowClosed(WindowEvent we) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent we) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent we) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
