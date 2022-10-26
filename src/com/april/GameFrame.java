package com.april;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    public GameFrame() {
        GamePanel pan = new GamePanel();
        Container cont = getContentPane();
        cont.add(pan);

        setTitle("\"Snake Game\"");
        setBounds(0, 0, 815, 660);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);

        setVisible(true);
    }
}
