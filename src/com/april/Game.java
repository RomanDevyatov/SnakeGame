package com.april;

public class Game {

    public int newDirection;
    private final int cellCount = 30;

    private final int LEFT_DIRECTION = 0;
    private final int UP_DIRECTION = 1;
    private final int RIGHT_DIRECTION = 2;
    private final int DOWN_DIRECTION = 3;
    private final int FEED_VALUE = 1;
    private final int NO_FEED_VALUE = 2;
    private final int BODY_VALUE = 3;

    private int[][] field;

    private int direction;
    private int gX, gY;
    private int score;
    private int length;

    private boolean isEnd;

    public Game() {
        field = new int[cellCount][cellCount];
    }

    public int getCellCount() {
        return cellCount;
    }

    public int getNewDirection() {
        return newDirection;
    }

    public void setNewDirection(int newDirection) {
        this.newDirection = newDirection;
    }

    public int[][] getField() {
        return field;
    }

    private void increaseField(int x, int y) {
        field[y][x]++;
    }

    public int getFieldValueAt(int x, int y) {
        return field[y][x];
    }

    public void setField(int[][] field) {
        this.field = field;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public void start() {
        for (int y = 0; y < this.cellCount; y++) {
            for (int x = 0; x < this.cellCount; x++) {
                field[y][x] = 0;
            }
        }

        direction = LEFT_DIRECTION;
        score = 0;

        int zmeikaStart = this.cellCount / 2;
        field[zmeikaStart][zmeikaStart] = 1;
        field[zmeikaStart + 1][zmeikaStart ] = 2;
        field[zmeikaStart + 2][zmeikaStart] = 3;

        length = 3;
        gX = gY = zmeikaStart;
        isEnd = false;

        makeNew();
    }

    private void makeNew() {
        while(true) {
            int xRandom = (int) (Math.random() * this.cellCount);
            int yRandom = (int) (Math.random() * this.cellCount);
            System.out.println("xRandom: " + xRandom + ", yRandom: " + yRandom);

            if (field[yRandom][xRandom] == 0) {
                field[yRandom][xRandom] = -1;
                break;
            }
        }
    }

    public void move() {
        int moveResult = moveHead();

        if (moveResult == BODY_VALUE) {
            setEnd(true);
        } else {
            for (int y = 0; y < cellCount; y++) {
                for (int x = 0; x < cellCount; x++) {
                    if (getFieldValueAt(x, y) > 0) {
                        increaseField(x, y);
                    } else if (getFieldValueAt(x, y) == -2) {
                        field[y][x] = 1;
                    }

                    if (moveResult != 1) {
                        if (field[y][x] == (length + 1)) {
                            field[y][x] = 0;
                        }
                    }
                }
            }

            if (moveResult == FEED_VALUE) {
                length++;
                makeNew();
                score += 10;
            }
        }
    }

    public int moveHead() {
        turn();

        if (direction == LEFT_DIRECTION) {
            if (this.gX - 1 >= 0) {
                this.gX--;
            } else {
                this.gX = cellCount - 1;
            }
        } else if (direction == UP_DIRECTION) {
            if (this.gY - 1 >= 0) {
                this.gY--;
            } else {
                this.gY = cellCount - 1;
            }
        } else if (direction == RIGHT_DIRECTION) {
            if (this.gX < cellCount - 1) {
                this.gX++;
            } else {
                this.gX = 0;
            }
        } else if (direction == DOWN_DIRECTION) {
            if (this.gY < cellCount - 1) {
                this.gY++;
            } else {
                this.gY = 0;
            }
        }

        int result = 0;
        if (this.field[gX][gY] == -1) {
            result = FEED_VALUE;
        } else if (this.field[gX][gY] == 0) {
            result = NO_FEED_VALUE;
        } else if (this.field[gX][gY] > 0) {
            result = BODY_VALUE;
        }

        field[gX][gY] = -2;

        return result;
    }

    private void turn() {
        if (Math.abs(this.direction - newDirection) != 2) {
            this.direction = newDirection;
        }
    }
}
