
/**************************************
 * File: Snake.java Author: helen maher
 * 
 * Description: Snake Game
 *************************************/

import java.util.ArrayList;

public class Snake extends javax.swing.JPanel implements java.awt.event.KeyListener {

    private static final long serialVersionUID = 1L;

    int[][] occupiedBySnake = new int[15][15];

    ArrayList<Integer> snakeXCoords = new ArrayList<Integer>();
    ArrayList<Integer> snakeYCoords = new ArrayList<Integer>();
    int[] appleCoords = new int[2];
    int snakeLength = 1;

    public void addBackground() {
        this.setPreferredSize(new java.awt.Dimension(15 * 24, 16 * 24));
        this.setBackground(java.awt.Color.GRAY);

        this.setLayout(null);
    }

    public void drawSnakeCell(int x, int y) {
        occupiedBySnake[x][y] = 1;
    }

    public void eraseSnakeCell(int x, int y) {
        occupiedBySnake[x][y] = 0;
    }

    public boolean checkApplePosition(int x, int y) {
        for (int i = 0; i < snakeLength; i++) {
            if (appleCoords[0] == snakeXCoords.get(i) && appleCoords[1] == snakeYCoords.get(i)) {
                return false;
            }
        }
        return true;
    }

    public void newApple() {
        int x, y;
        do {
            x = (int) (15 * Math.random());
            y = (int) (15 * Math.random());

        } while (!checkApplePosition(x, y));
        appleCoords[0] = x;
        appleCoords[1] = y;

    }

    public void paint(java.awt.Graphics gr) {
        super.paint(gr);

        for (int x = 0; x < occupiedBySnake.length; x++) {
            for (int y = 0; y < occupiedBySnake[0].length; y++) {
                if (occupiedBySnake[x][y] == 1) {
                    gr.setColor(java.awt.Color.BLACK);
                    gr.fillRect(x * 24, y * 24, 24, 24);
                    gr.setColor(java.awt.Color.GREEN);
                    gr.fillRect(x * 24 + 1, y * 24 + 1, 22, 22);
                } else if (appleCoords[0] == x && appleCoords[1] == y) {
                    gr.setColor(java.awt.Color.BLACK);
                    gr.fillRect(x * 24, y * 24, 24, 24);
                    gr.setColor(java.awt.Color.RED);
                    gr.fillRect(x * 24 + 1, y * 24 + 1, 22, 22);
                } else {
                    gr.setColor(java.awt.Color.WHITE);
                    gr.fillRect(x * 24 + 1, y * 24 + 1, 22, 22);
                }
            }
        }
    }

    public boolean isValidPosition(int x, int y) {
        if (x < 0 || x >= 15 || y < 0 || y >= 15 || occupiedBySnake[x][y] == 1) {
            return false;
        } else {
            return true;
        }
    }

    boolean gameOver = false;

    public void addSnake() {
        int x = 7, y = 7;
        snakeXCoords.add(x);
        snakeYCoords.add(y);

        newApple();

        for (int i = 0; i < snakeLength; i++) {
            drawSnakeCell(snakeXCoords.get(i), snakeYCoords.get(i));
        }
        repaint();

        int delay = 50;
        int frame = 0;
        gameOver = false;
        while (!gameOver) {
            try {
                Thread.sleep(delay);
            } catch (Exception ignore) {
            }

            eraseSnakeCell(snakeXCoords.get(0), snakeYCoords.get(0));

            if (frame % 5 == 0 && leftPressed) {

                snakeYCoords.get(snakeLength) = snakeYCoords.get(snakeLength - 1);
                snakeXCoords[snakeLength] = snakeXCoords[snakeLength - 1] - 1;
            }
            if (frame % 5 == 0 && rightPressed) {

                snakeYCoords[snakeLength] = snakeYCoords[snakeLength - 1];
                snakeXCoords[snakeLength] = snakeXCoords[snakeLength - 1] + 1;
            }
            if (frame % 5 == 0 && upPressed) {

                snakeYCoords[snakeLength] = snakeYCoords[snakeLength - 1] - 1;
                snakeXCoords[snakeLength] = snakeXCoords[snakeLength - 1];

            }
            if (frame % 5 == 0 && downPressed) {

                snakeYCoords[snakeLength] = snakeYCoords[snakeLength - 1] + 1;
                snakeXCoords[snakeLength] = snakeXCoords[snakeLength - 1];

            }

            if (frame % 5 == 0 && !downPressed && !upPressed && !rightPressed && !leftPressed) {
                snakeYCoords[snakeLength] = snakeYCoords[snakeLength - 1];
                snakeXCoords[snakeLength] = snakeXCoords[snakeLength - 1] + 1;
            }

            if (!isValidPosition(snakeXCoords[snakeLength], snakeYCoords[snakeLength])) {
                gameOver = true;
                return;
            }

            // check for apple and if apple, add deleted cell back in and add length
            if (snakeXCoords[snakeLength] == appleCoords[0] && snakeYCoords[snakeLength] == appleCoords[1]) {
                snakeLength++;
                newApple();
            } else {
                for (int i = 0; i < snakeLength; i++) {
                    snakeYCoords[i] = snakeYCoords[i + 1];
                    snakeXCoords[i] = snakeXCoords[i + 1];
                }
                snakeYCoords.get(snakeLength) = 0;
                snakeXCoords.get(snakeLength) = 0;
            }

            for (int i = 0; i < snakeLength; i++) {
                drawSnakeCell(snakeXCoords.get(i), snakeYCoords.get(i));
            }

            repaint();
            frame++;
        }

    }

    public void printGameOver() {
        javax.swing.JLabel gameOverLabel = new javax.swing.JLabel("GAME OVER");
        gameOverLabel.setBounds(24, 24 * 15, 100, 30);
        add(gameOverLabel);
        repaint();
    }

    boolean leftPressed = false;
    boolean rightPressed = false;
    boolean downPressed = false;
    boolean upPressed = false;

    public void keyPressed(java.awt.event.KeyEvent event) {
        if (event.getKeyCode() == 37) {
            leftPressed = true;
            rightPressed = false;
            downPressed = false;
            upPressed = false;
        }
        if (event.getKeyCode() == 39) {
            leftPressed = false;
            rightPressed = true;
            downPressed = false;
            upPressed = false;
        }
        if (event.getKeyCode() == 40) {
            leftPressed = false;
            rightPressed = false;
            downPressed = true;
            upPressed = false;
        }
        if (event.getKeyCode() == 38) {
            leftPressed = false;
            rightPressed = false;
            downPressed = false;
            upPressed = true;
        }
    }

    public void keyReleased(java.awt.event.KeyEvent event) {
    }

    public void keyTyped(java.awt.event.KeyEvent event) {
    }

    public static void main(String[] args) throws Exception {
        javax.swing.JFrame window = new javax.swing.JFrame("Snake");
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

        Snake snake = new Snake();
        snake.addBackground();

        window.add(snake);
        window.pack();
        window.setVisible(true);

        try {
            Thread.sleep(1000);
        } catch (Exception ignore) {
        }

        window.addKeyListener(snake);

        snake.addSnake();

        snake.printGameOver();

    }
}