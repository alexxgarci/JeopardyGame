package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Question;
import view.ExitView;
import view.QuestionView;
import view.View;

public class QuestionController implements ActionListener {

    private QuestionView questionView;
    private Question model;
    private int answerValue;
    private View view;
    private static int cont = 0;

    public QuestionController(QuestionView questionView, Question model, View view) {

        this.questionView = questionView;
        this.model = model;
        this.view = view;
        questionView.addListeners(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        cont++;
        if (cont == 1) {
            int answer = questionView.getAnswerSelected();
            if (answer == 3) {
                answerValue = -model.getQuestionValue();
                questionView.changeBgColorToRed(0);
                questionView.changeBgColorToRed(1);
                questionView.changeBgColorToRed(2);
                questionView.changeBgColorToGreen(model.getCorrectIndexValue());
                view.changePlayerScore(answerValue);
                view.refreshScore();
                view.changeCurrentUser();
                view.addTurn();
                questionView.changeButtonTxt();
            } else {
                if (model.comprovarRespuesta(answer)) {
                    answerValue = model.getQuestionValue();
                    questionView.changeBgColorToGreen(answer);
                    view.changePlayerScore(answerValue);
                    view.refreshScore();
                    view.changeCurrentUser();
                    view.addTurn();
                    questionView.changeButtonTxt();
                } else {
                    answerValue = -model.getQuestionValue();
                    questionView.changeBgColorToRed(answer);
                    questionView.changeBgColorToGreen(model.getCorrectIndexValue());
                    view.changePlayerScore(answerValue);
                    view.refreshScore();
                    view.changeCurrentUser();
                    view.addTurn();
                    questionView.changeButtonTxt();
                }
            }
        } else {
            cont = 0;
            if ((view.getCurrentUser() == view.getUsers()[1]) && (view.getCurrentUser().getTurn() == 15)) {
                String frase = view.getWinner();
                view.dispose();
                ExitView exitView = new ExitView(frase);
                ExitController exitController = new ExitController(exitView, frase);
            }
            questionView.dispose();
        }

    }

    public int getAnswerValue() {
        return this.answerValue;
    }

}
