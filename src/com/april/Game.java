package com.april;

public class Game {

    public int newNapr;
    private int cellCount = 30;

    private int[][] mas; // -> null

    private int napr; // 0 - left, 1 - up, 2 - right, 3 - down
    private int gX, gY; // координаты головы змейки
    private int kol;
    private int dlina;

    private boolean isEnd;

    public Game() {
        mas = new int[this.cellCount][this.cellCount];
    }

    public int getCellCount() {
        return this.cellCount;
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

    public int getMasVal(int x, int y) {
        return this.mas[y][x];
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

        napr = 0; // изначально влево
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

    public void peremGolova() {
        povorot();

        if (napr == 0) {
            if (this.gX - 1 >= 0) {
                this.gX--;
            } else {
                this.gX = 29;
            }
        } else if (napr == 1) {
            if (this.gY - 1 >= 0) {
                this.gY--;
            } else {
                this.gY = 29;
            }
        } else if (napr == 2) {
            if (this.gX < 29) {
                this.gX++;
            } else {
                this.gX = 0;
            }
        } else if (napr == 3) {
            if (this.gY < 29) {
                this.gY++;
            } else {
                this.gY = 0;
            }
        }

        if (this.mas[this.gX][this.gY] == -1) {
            makeNew();
            kol += 10;
        }

        this.mas[this.gX][this.gY] = 1;

//        int rez = 0;
//        if (this.mas[this.gX][this.gY] == -1) {
//            rez = 1; // попали туда, где еда
//        } else if (this.mas[this.gX][this.gY] == 0) {
//            rez = 2; // попали в пустое поле
//        } else if (this.mas[this.gX][this.gY] > 0) {
//            rez = 3; // попали в туловище змейки
//        }
    }

    private void povorot() {
        this.napr = newNapr;
    }




}
