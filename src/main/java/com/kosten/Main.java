package com.kosten;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Main  {
    JFrame frame;


    static String st[] = new String[4];
    Main() {
        frame = new JFrame("MCH");
        frame.setSize(new Dimension(600, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        DataPanel dataPanel = new DataPanel();
        dataPanel.init();

        frame.add(dataPanel, BorderLayout.CENTER);

        frame.setVisible(true);
        frame.pack();
    }


    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();


    }

}
