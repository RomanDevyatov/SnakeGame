package com.april;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

// рисует поле, кнопки и тд.
// вставляет изображения змейки в ячейки поля
// + таймеры для передвижения
public class MyPanel extends JPanel {

    private Game game;

    private Timer tmDraw, tmUpdate;

    private Image
                fon,
                telo,
                golova,
                ob,
                endg;
    private JLabel label;
    private JButton btn1, btn2;
    private int cellCount;
    private MyPanel pan;

    private class MyKey implements KeyListener {

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT) {
                game.setNewNapr(0);
            } else if (key == KeyEvent.VK_UP) {
                game.setNewNapr(1);
            } else if (key == KeyEvent.VK_RIGHT) {
                game.setNewNapr(2);
            } else if (key == KeyEvent.VK_DOWN) {
                game.setNewNapr(3);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {}

        @Override
        public void keyTyped(KeyEvent e) {}

    }

    public MyPanel() {
        pan = this;
        this.addKeyListener(new MyKey());
        this.setFocusable(true);

        try {
            this.fon = ImageIO.read(new File("C:\\Users\\Roman_Devyatov\\IdeaProjects\\Zmeika\\pictures\\fon.jpg"));
            this.telo = ImageIO.read(new File("C:\\Users\\Roman_Devyatov\\IdeaProjects\\Zmeika\\pictures\\telo.png"));
            this.golova = ImageIO.read(new File("C:\\Users\\Roman_Devyatov\\IdeaProjects\\Zmeika\\pictures\\golova.png"));
            this.ob = ImageIO.read(new File("C:\\Users\\Roman_Devyatov\\IdeaProjects\\Zmeika\\pictures\\object.png"));
            this.endg = ImageIO.read(new File("C:\\Users\\Roman_Devyatov\\IdeaProjects\\Zmeika\\pictures\\endg.jpg"));
        } catch (Exception e) {
            System.out.println("Image file cannot be read " + e.getMessage());
        }

        this.game = new Game();
        this.cellCount = this.game.getCellCount();
        this.game.start();

        this.tmDraw = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        this.tmDraw.start();

        this.tmUpdate = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!game.isEnd()) {
                    game.perem();
                }
                label.setText("Счёт: " + game.getKol());
            }
        });
        this.tmUpdate.start();

        setLayout(null);

        this.label = new JLabel("Счёт: 0");
        this.label.setForeground(Color.BLACK);
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
                btn1.setFocusable(false);
                btn2.setFocusable(false);
                pan.setFocusable(true);
            }
        });
        add(this.btn1);

        this.btn2 = new JButton();
        this.btn2.setText("Выход");
        this.btn2.setForeground(Color.BLUE);
        this.btn2.setFont(new Font("serif", 0, 20));
        this.btn2.setBounds(630,100, 150, 50);
        this.btn2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(this.btn2);
    }

    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);

        gr.drawImage(fon, 0, 0, 800, 650, null);

        for (int i = 0; i < this.cellCount; i++) {
            for (int k = 0; k < this.cellCount; k++) {
                if (this.game.getMasVal(i, k) != 0) {
                    if (this.game.getMasVal(i, k) == 1) {
                        gr.drawImage(this.golova, 10 + k * 20, 10 + i * 20, 20, 20, null);
                    } else if (this.game.getMasVal(i, k) == -1) {
                        gr.drawImage(this.ob, 10 + k * 20, 10 + i * 20, 20, 20, null);
                    } else if (this.game.getMasVal(i, k) >= 2) { // на будущее
                        gr.drawImage(this.ob, 10 + k * 20, 10 + i * 20, 20, 20, null);
                    }
                }
            }
        }

        gr.setColor(Color.BLUE);
        for (int i = 0; i <= this.cellCount; ++i) {
            gr.drawLine(10 + i * 20, 10, 10 + i * 20, 610);
            gr.drawLine(10, 10 + i * 20, 610, 10 + i * 20);
        }

        if (game.isEnd()) {
            gr.drawImage(this.endg, 250, 200, 300, 200, null);
        }

    }

}
