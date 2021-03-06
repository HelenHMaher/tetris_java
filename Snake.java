
/**************************************
 * File: Snake.java Author: helen maher
 * 
 * Description: Snake Game
 * Improvements: make a 'start' happen on request
 *************************************/

import java.util.ArrayList;

public class Snake extends javax.swing.JPanel implements java.awt.event.KeyListener {

    private static final long serialVersionUID = 1L;

    int[][] occupiedBySnake = new int[15][15];

    ArrayList<Integer> snakeXCoords = new ArrayList<Integer>();
    ArrayList<Integer> snakeYCoords = new ArrayList<Integer>();

    int[] appleCoords = new int[2];
    boolean initializeApple = false;
    boolean validApple;

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

    public void checkApplePosition(int x, int y) {
        for (int i = 0; i < snakeXCoords.size(); i++) {
            if (x == snakeXCoords.get(i) && y == snakeYCoords.get(i)) {
                return;
            }
        }
        validApple = true;
    }

    public void newApple() {
        initializeApple = true;
        validApple = false;
        int x, y;
        do {
            x = (int) (15 * Math.random());
            y = (int) (15 * Math.random());
            // System.out.println("x : " + x + " y : " + y);
            checkApplePosition(x, y);
        } while (!validApple);
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
                } else if (appleCoords[0] == x && appleCoords[1] == y && initializeApple) {
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

        drawSnakeCell(snakeXCoords.get(0), snakeYCoords.get(0));

        repaint();

        int delay = 50;
        int frame = 0;
        gameOver = false;
        GAME: while (!gameOver) {
            try {
                Thread.sleep(delay);
            } catch (Exception ignore) {
            }
            if (frame % 5 == 0) {
                if (leftPressed) {

                    snakeYCoords.add(snakeYCoords.get(snakeYCoords.size() - 1));
                    snakeXCoords.add(snakeXCoords.get(snakeXCoords.size() - 1) - 1);
                }
                if (rightPressed) {

                    snakeYCoords.add(snakeYCoords.get(snakeYCoords.size() - 1));
                    snakeXCoords.add(snakeXCoords.get(snakeXCoords.size() - 1) + 1);
                }
                if (upPressed) {

                    snakeYCoords.add(snakeYCoords.get(snakeYCoords.size() - 1) - 1);
                    snakeXCoords.add(snakeXCoords.get(snakeXCoords.size() - 1));

                }
                if (downPressed) {

                    snakeYCoords.add(snakeYCoords.get(snakeYCoords.size() - 1) + 1);
                    snakeXCoords.add(snakeXCoords.get(snakeXCoords.size() - 1));

                }

                if (!downPressed && !upPressed && !rightPressed && !leftPressed) {
                    snakeYCoords.add(snakeYCoords.get(snakeYCoords.size() - 1));
                    snakeXCoords.add(snakeXCoords.get(snakeXCoords.size() - 1) + 1);
                }

                if (!isValidPosition(snakeXCoords.get(snakeXCoords.size() - 1),
                        snakeYCoords.get(snakeYCoords.size() - 1))) {

                    // System.out.println("GAME OVER: X = " + snakeXCoords + " Y = " +
                    // snakeYCoords);

                    gameOver = true;
                    spacebarPressed = false;
                    printGameOver();

                    for (int i = 0; i < snakeXCoords.size() - 1; i++) {
                        eraseSnakeCell(snakeXCoords.get(i), snakeYCoords.get(i));
                    }

                    newApple();

                    snakeXCoords.clear();
                    snakeYCoords.clear();
                    snakeXCoords.add(x);
                    snakeYCoords.add(y);

                    drawSnakeCell(snakeXCoords.get(0), snakeYCoords.get(0));

                    repaint();

                    continue GAME;

                }

                // check for apple
                if (snakeXCoords.get(snakeXCoords.size() - 1) == appleCoords[0]
                        && snakeYCoords.get(snakeYCoords.size() - 1) == appleCoords[1]) {
                    drawSnakeCell(snakeXCoords.get(snakeXCoords.size() - 1), snakeYCoords.get(snakeYCoords.size() - 1));
                    newApple();
                } else {
                    drawSnakeCell(snakeXCoords.get(snakeXCoords.size() - 1), snakeYCoords.get(snakeYCoords.size() - 1));
                    eraseSnakeCell(snakeXCoords.get(0), snakeYCoords.get(0));
                    snakeXCoords.remove(0);
                    snakeYCoords.remove(0);
                }

                repaint();
            }
            frame++;
        }

    }

    public void printGameOver() {
        javax.swing.JLabel gameOverLabel = new javax.swing.JLabel("GAME OVER     PRESS the SPACEBAR to RESTART");
        gameOverLabel.setBounds(10, 24 * 15 - 4, 300, 30);
        add(gameOverLabel);
        repaint();
        while (gameOver) {
            try {
                Thread.sleep(500);
            } catch (Exception ignore) {
            }
            if (spacebarPressed) {
                gameOver = false;
            }

        }
        remove(gameOverLabel);
        repaint();
    }

    boolean leftPressed = false;
    boolean rightPressed = false;
    boolean downPressed = false;
    boolean upPressed = false;
    boolean spacebarPressed = false;

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
        if (event.getKeyCode() == 32) {
            spacebarPressed = true;
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

    }
}
