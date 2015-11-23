package view;

import controller.ExitController;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ExitView extends JFrame {

    private String info;
    private JLabel frase;
    private JPanel exitPanel;
    private JButton exit;

    public ExitView(String info) {
        this.info = info;

        this.setTitle("Ha terminado el juego");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentsToPane(this);
        this.setVisible(true);
        this.pack();
        this.setResizable(false);
    }

    private void addComponentsToPane(Container panel) {

        frase = new JLabel(info);
        exitPanel = new JPanel();
        exit = new JButton("Cerrar");
        exit.setPreferredSize(new Dimension(90, 30));
        exitPanel.add(exit);

        panel.add(frase, BorderLayout.PAGE_START);
        panel.add(exitPanel, BorderLayout.PAGE_END);
    }

    public void addListeners(ExitController exitController) {
        exit.addActionListener(exitController);
    }
}
