package com.april;

public class Game {

    public int newNapr;
    private int cellCount = 10;

    private int[][] mas; // -> null

    private int napr; // 0 - left, 1 - up, 2 - right, 3 - down
    private int gX, gY;
    private int kol;
    private int dlina;

    private boolean isEnd;

    public Game() {
        mas = new int[this.cellCount][this.cellCount];
    }

    public int getNewNapr() {
        return newNapr;
    }

    public void setNewNapr(int newNapr) {
        this.newNapr = newNapr;
    }

    public int[][] getMas() {
        return mas;
    }

    public void setMas(int[][] mas) {
        this.mas = mas;
    }

    public int getNapr() {
        return napr;
    }

    public void setNapr(int napr) {
        this.napr = napr;
    }

    public int getgX() {
        return gX;
    }

    public void setgX(int gX) {
        this.gX = gX;
    }

    public int getgY() {
        return gY;
    }

    public void setgY(int gY) {
        this.gY = gY;
    }

    public int getKol() {
        return kol;
    }

    public void setKol(int kol) {
        this.kol = kol;
    }

    public int getDlina() {
        return dlina;
    }

    public void setDlina(int dlina) {
        this.dlina = dlina;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }


    public void start() {
        for (int i = 0; i < this.cellCount; i++) {
            for (int k = 0; k < this.cellCount; k++) {
                mas[i][k] = 0;
            }
        }

        napr = 0;
        kol = 0;

        int zmeikaStart = this.cellCount / 2;
        mas[zmeikaStart][zmeikaStart] = 1;
//        mas[zmeikaStart][zmeikaStart + 1] = 2;
//        mas[zmeikaStart][zmeikaStart + 2] = 3;
//        dlina = 3;
        gX = gY = zmeikaStart;
        isEnd = false;

        makeNew();
    }

    private void makeNew() {
        while(true) {
            int x = (int) (Math.random() * this.cellCount);
            int y = (int) (Math.random() * this.cellCount);
            System.out.println("x: " + x + ", y: " + y);

            if (mas[y][x] == 0) {
                mas[y][x] = -1;
                break;
            }
        }
    }




}
