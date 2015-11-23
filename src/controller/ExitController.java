package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.ExitView;

public class ExitController implements ActionListener {

    private ExitView exitView;
    private String model;

    public ExitController(ExitView exitView, String model) {
        this.exitView = exitView;
        this.model = model;
        exitView.addListeners(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        exitView.dispose();
        System.exit(0);
    }

}
