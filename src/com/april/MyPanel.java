package com.april;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

// рисует поле, кнопки и тд.
// вставляет изображения змейки в ячейки поля
// + таймеры для передвижения
public class MyPanel extends JPanel {

    private Game game;

    private Timer tmDraw;

    private Image
                fon,
                telo,
                golova,
                ob,
                endg;
    private JLabel label;
    private JButton btn1, btn2;

    public MyPanel() {
        try {
            this.fon = ImageIO.read(new File(""));
            this.telo = ImageIO.read(new File(""));
            this.golova = ImageIO.read(new File(""));
            this.ob = ImageIO.read(new File(""));
            this.endg = ImageIO.read(new File(""));
        } catch (Exception e) {
            System.out.println("Image file cannot be red " + e.getMessage());
        }

        this.game = new Game();
        this.game.start();

        this.tmDraw = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        this.tmDraw.start();

        setLayout(null);

        this.label = new JLabel("Счёт: 0");
        this.label.setForeground(Color.WHITE);
        this.label.setFont(new Font("serif", 0, 30));
        this.label.setBounds(630, 200, 150, 50);
        add(this.label);

        this.btn1 = new JButton();
        this.btn1.setText("Новая игра");
        this.btn1.setForeground(Color.BLUE);
        this.btn1.setFont(new Font("serif", 0, 20));
        this.btn1.setBounds(630,30, 150, 50);
        this.btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                game.start();
            }
        });
        add(this.btn1);

        this.btn2 = new JButton();
        this.btn2.setText("Выход");
        this.btn2.setForeground(Color.BLUE);
        this.btn2.setFont(new Font("serif", 0, 20));
        this.btn2.setBounds(630,30, 150, 50);
        this.btn2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(this.btn2);
    }

}
