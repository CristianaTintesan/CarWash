package controller;

import javax.swing.*;
import java.awt.*;

public class ChooseTypeView {

    private JFrame frame;
    private JButton btnInterior;
    private JButton btnExterior;
    private JButton btnComplete;
    private JLabel chooseLabel;
    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    public ChooseTypeView() {
        frame = new JFrame("Washing types");
        initialize();
        frame.setVisible(true);
    }

    private void initialize() {

        chooseLabel = new JLabel("Choose washing type");
        btnInterior = new JButton("Interior washing");
        btnExterior = new JButton("Exterior washing");
        btnComplete = new JButton("Complete washing");

        frame.setBounds(500,500,550,350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame,BoxLayout.Y_AXIS));

        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

        btnInterior.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnExterior.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnComplete.setAlignmentX(Component.CENTER_ALIGNMENT);

        frame.getContentPane().add(btnInterior);
        frame.getContentPane().add(btnExterior);
        frame.getContentPane().add(btnComplete);

        frame.pack();

    }
}
