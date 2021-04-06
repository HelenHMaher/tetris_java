/**************************************
 * File: Snake.java Author: helen maher
 * 
 * Description: Snake Game
 *************************************/

public class Snake extends javax.swing.JPanel implements java.awt.event.KeyListener {

    private static final long serialVersionUID = 1L;

    int[][] occupiedBySnake = new int[15][15];
    int[][] occupiedByApple = new int[15][15];
    int[] snakeXCoords = new int[30];
    int[] snakeYCoords = new int[30];
    int snakeLength = 1;

    public void addBackground() {
        this.setPreferredSize(new java.awt.Dimension(15 * 24, 15 * 24));
        this.setBackground(java.awt.Color.GRAY);

        this.setLayout(null);
    }

    public void drawSnakeCell(int x, int y) {
        occupiedBySnake[x][y] = 1;
    }

    public void eraseSnakeCell(int x, int y) {
        occupiedBySnake[x][y] = 0;
    }

    public void drawAppleCell(int x, int y) {
        occupiedByApple[x][y] = 1;
    }

    public void eraseAppleCell(int x, int y) {
        occupiedByApple[x][y] = 0;
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
                } else if (occupiedByApple[x][y] == 1) {
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
        if (x < 0) {
            return false;
        }
        if (x >= 15) {
            return false;
        }
        if (y < 0) {
            return false;
        }
        if (y >= 15) {
            return false;
        }
        if (occupiedBySnake[x][y] == 1) {
            return false;
        }
        return true;
    }

    boolean gameOver = false;

    public void addSnake() {
        int x = 7, y = 7;
        snakeXCoords[0] = x;
        snakeYCoords[0] = y;

        for (int i = 0; i < snakeLength; i++) {
            drawSnakeCell(snakeXCoords[i], snakeYCoords[i]);
        }
        repaint();
    }

    boolean leftPressed = false;
    boolean rightPressed = false;
    boolean downPressed = false;
    boolean upPressed = false;

    public void keyPressed(java.awt.event.KeyEvent event) {
        if (event.getKeyCode() == 37) {
            leftPressed = true;
        }
        if (event.getKeyCode() == 39) {
            rightPressed = true;
        }
        if (event.getKeyCode() == 40) {
            downPressed = true;
        }
        if (event.getKeyCode() == 38) {
            upPressed = true;
        }
    }

    public void keyReleased(java.awt.event.KeyEvent event) {
        if (event.getKeyCode() == 37) {
            leftPressed = false;
        }
        if (event.getKeyCode() == 39) {
            rightPressed = false;
        }
        if (event.getKeyCode() == 40) {
            downPressed = false;
        }
        if (event.getKeyCode() == 38) {
            upPressed = false;
        }
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

        snake.gameOver = false;
        while (!snake.gameOver) {
            snake.addSnake();
        }

    }
}
