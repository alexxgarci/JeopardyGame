package controller;

import auxiliar.FileOperations;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.swing.JButton;
import model.Category;
import model.Question;
import model.User;
import view.StartView;
import view.View;

public class StartController implements ActionListener {

    private static int count = 0;

    private JButton[] categoryButtons;

    private StartView sview;
    private Category[] model;
    private Category[] selectedCategories = new Category[6];
    private User[] users = new User[2];

    private LinkedHashMap<String, ArrayList<Question>> mainModel = new LinkedHashMap<>();
    private ArrayList<Question> questions = new ArrayList<>();

    public StartController(StartView sview, Category[] model) {

        this.sview = sview;
        this.model = model;
        sview.addListeners(this);

    }

    public void addCategoryToMainModel(String categoryName) {
        try {
            questions = FileOperations.readQuestions(categoryName);
        } catch (IOException ex) {
            ex.getMessage();
        }
        mainModel.put(categoryName, questions);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        categoryButtons = sview.getCategoryButtons();
        for (int i = 0; i < model.length; i++) {
            if (ae.getSource() == categoryButtons[i]) {
                sview.disableButton(i);
                count++;
                this.addCategoryToMainModel(model[i].getCategoryName());
                if (count == 6) {
                    users[0] = new User(sview.getPlayer1Name());
                    users[1] = new User(sview.getPlayer2Name());
                    View view = new View(mainModel, users);
                    Controller controller = new Controller(view, mainModel);
                    sview.dispose();
                }
            }
        }
    }

}
