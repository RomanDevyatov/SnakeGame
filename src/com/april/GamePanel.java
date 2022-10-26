package com.april;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class GamePanel extends JPanel {

    private Game game;

    private Timer tmDraw, tmUpdate;

    private Image backgroundImage;
    private Image bodyImage;
    private Image headImage;
    private Image feedImage;
    private Image endGameImage;
    private JLabel label;
    private JButton btn1, btn2;
    private int cellCount;

    private class KeyListenerImpl implements KeyListener {

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT) {
                game.setNewDirection(0);
            } else if (key == KeyEvent.VK_UP) {
                game.setNewDirection(1);
            } else if (key == KeyEvent.VK_RIGHT) {
                game.setNewDirection(2);
            } else if (key == KeyEvent.VK_DOWN) {
                game.setNewDirection(3);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {}

        @Override
        public void keyTyped(KeyEvent e) {}

    }

    public GamePanel() {
        this.addKeyListener(new KeyListenerImpl());
        this.setFocusable(true);

        try {
            this.backgroundImage = ImageIO.read(new File(".\\pictures\\fon.jpg"));
            this.bodyImage = ImageIO.read(new File(".\\pictures\\telo.png"));
            this.headImage = ImageIO.read(new File(".\\pictures\\golova.png"));
            this.feedImage = ImageIO.read(new File(".\\pictures\\object.png"));
            this.endGameImage = ImageIO.read(new File(".\\pictures\\endg.jpg"));
        } catch (Exception e) {
            System.out.println("Image file cannot be read " + e.getMessage());
        }

        this.game = new Game();
        this.cellCount = game.getCellCount();
        this.game.start();

        this.tmDraw = new Timer(20, e -> repaint());
        this.tmDraw.start();

        this.tmUpdate = new Timer(100, e -> {
            if (!game.isEnd()) {
                game.move();
            }
            label.setText("Score: " + game.getScore());
        });
        this.tmUpdate.start();

        setLayout(null);

        this.label = new JLabel("Score: 0");
        this.label.setForeground(Color.BLACK);
        this.label.setFont(new Font("serif", 0, 30));
        this.label.setBounds(630, 200, 150, 50);
        add(this.label);

        this.btn1 = new JButton();
        this.btn1.setText("New Game");
        this.btn1.setForeground(Color.BLUE);
        this.btn1.setFont(new Font("serif", 0, 20));
        this.btn1.setBounds(630,30, 150, 50);
        this.btn1.addActionListener(e -> {
                game.start();
                btn1.setFocusable(false);
                btn2.setFocusable(false);
                this.setFocusable(true);
        });
        add(this.btn1);

        this.btn2 = new JButton();
        this.btn2.setText("Exit");
        this.btn2.setForeground(Color.BLUE);
        this.btn2.setFont(new Font("serif", 0, 20));
        this.btn2.setBounds(630,100, 150, 50);
        this.btn2.addActionListener(e -> System.exit(0));
        add(this.btn2);
    }

    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);

        gr.drawImage(backgroundImage, 0, 0, 800, 650, null);

        for (int i = 0; i < this.cellCount; i++) {
            for (int k = 0; k < this.cellCount; k++) {
                if (this.game.getFieldValueAt(i, k) != 0) {
                    if (this.game.getFieldValueAt(i, k) == 1) {
                        gr.drawImage(this.headImage, 10 + k * 20, 10 + i * 20, 20, 20, null);
                    } else if (this.game.getFieldValueAt(i, k) == -1) {
                        gr.drawImage(this.feedImage, 10 + k * 20, 10 + i * 20, 20, 20, null);
                    } else if (this.game.getFieldValueAt(i, k) >= 2) {
                        gr.drawImage(this.feedImage, 10 + k * 20, 10 + i * 20, 20, 20, null);
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
            gr.drawImage(this.endGameImage, 250, 200, 300, 200, null);
        }

    }
}
