package jeopardy;

import controller.StartController;
import model.Category;
import view.StartView;

public class Jeopardy {

    static Category[] model = new Category[9];

    public static void main(String[] args) {

        model[0] = new Category("Arte");
        model[1] = new Category("Deporte");
        model[2] = new Category("Geografia");
        model[3] = new Category("Entretenimiento");
        model[4] = new Category("Historia");
        model[5] = new Category("Ciencia");
        model[6] = new Category("Literatura");
        model[7] = new Category("Cultura");
        model[8] = new Category("Actualidad");

        StartView sview = new StartView(model);
        StartController scontroller = new StartController(sview, model);
    }

}
