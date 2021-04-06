/**************************************
 * File: TetrisReplica.java Author: helen maher
 * http://java.macteki.com/2011/06/tetris-from-scratch.html
 * 
 * Description: based on walk-through
 *************************************/

public class TetrisReplica extends javax.swing.JPanel implements java.awt.event.KeyListener {

    private static final long serialVersionUID = 1L;

    static int[][][] xRotationArray = { { { 0, 0, 1, 2 }, { 0, 0, 0, 1 }, { 2, 0, 1, 2 }, { 0, 1, 1, 1 } }, // token
            // number 0
            { { 0, 0, 1, 1 }, { 1, 2, 0, 1 }, { 0, 0, 1, 1 }, { 1, 2, 0, 1 } }, // token number 1
            { { 1, 1, 0, 0 }, { 0, 1, 1, 2 }, { 1, 1, 0, 0 }, { 0, 1, 1, 2 } }, // token number 2
            { { 0, 1, 2, 2 }, { 0, 1, 0, 0 }, { 0, 0, 1, 2 }, { 1, 1, 0, 1 } }, // token number 3
            { { 1, 0, 1, 2 }, { 1, 0, 1, 1 }, { 0, 1, 1, 2 }, { 0, 0, 1, 0 } }, // token number 4
            { { 0, 1, 0, 1 }, { 0, 1, 0, 1 }, { 0, 1, 0, 1 }, { 0, 1, 0, 1 } }, // token number 5
            { { 0, 1, 2, 3 }, { 0, 0, 0, 0 }, { 0, 1, 2, 3 }, { 0, 0, 0, 0 } } // token number 6
    };

    static int[][][] yRotationArray = { { { 0, 1, 0, 0 }, { 0, 1, 2, 2 }, { 0, 1, 1, 1 }, { 0, 0, 1, 2 } }, // token
            // number 0
            { { 0, 1, 1, 2 }, { 0, 0, 1, 1 }, { 0, 1, 1, 2 }, { 0, 0, 1, 1 } }, // token number 1
            { { 0, 1, 1, 2 }, { 0, 0, 1, 1 }, { 0, 1, 1, 2 }, { 0, 0, 1, 1 } }, // token number 2
            { { 0, 0, 0, 1 }, { 0, 0, 1, 2 }, { 0, 1, 1, 1 }, { 0, 1, 2, 2 } }, // token number 3
            { { 0, 1, 1, 1 }, { 0, 1, 1, 2 }, { 0, 0, 1, 0 }, { 0, 1, 1, 2 } }, // token number 4
            { { 0, 0, 1, 1 }, { 0, 0, 1, 1 }, { 0, 0, 1, 1 }, { 0, 0, 1, 1 } }, // token number 5
            { { 0, 0, 0, 0 }, { 0, 1, 2, 3 }, { 0, 0, 0, 0 }, { 0, 1, 2, 3 } } // token number 6
    };

    int[][] occupied = new int[15][30];
    int[][] color = new int[15][30];

    boolean gameOver = false;
    int score = 0;
    int lineCompleted = 0;
    int level = 0;

    javax.swing.JLabel scoreLabel = new javax.swing.JLabel("SCORE : 0");
    javax.swing.JLabel levelLabel = new javax.swing.JLabel("LEVEL : 0");

    public void addBackground() {
        this.setPreferredSize(new java.awt.Dimension(24 * 15, 24 * 33));
        this.setBackground(java.awt.Color.GRAY);

        this.setLayout(null); // absolute coordiante system

        scoreLabel.setBounds(24, 24 * 30, 100, 30);
        this.add(scoreLabel);

        levelLabel.setBounds(24, 24 * 31, 100, 30);
        this.add(levelLabel);

    }

    public void drawCell(int x, int y, int tokenColor) {
        occupied[x][y] = 1;
        color[x][y] = tokenColor;
    }

    public void eraseCell(int x, int y) {
        occupied[x][y] = 0;
    }

    public void drawToken(int x, int y, int[] xArray, int[] yArray, int tokenColor) {
        for (int i = 0; i < 4; i++) {
            drawCell(x + xArray[i], y + yArray[i], tokenColor);
        }
    }

    public void eraseToken(int x, int y, int[] xArray, int[] yArray) {
        for (int i = 0; i < 4; i++) {
            eraseCell(x + xArray[i], y + yArray[i]);
        }
    }

    public void paint(java.awt.Graphics gr) {
        super.paint(gr);

        for (int x = 0; x < occupied.length; x++) {
            for (int y = 0; y < occupied[0].length; y++) {
                if (occupied[x][y] == 1) {
                    gr.setColor(java.awt.Color.BLACK);
                    gr.fillRect(x * 24, y * 24, 24, 24);
                    switch (color[x][y]) {
                    case 0:
                        gr.setColor(java.awt.Color.RED);
                        break;
                    case 1:
                        gr.setColor(java.awt.Color.YELLOW);
                        break;
                    case 2:
                        gr.setColor(java.awt.Color.PINK);
                        break;
                    case 3:
                        gr.setColor(java.awt.Color.ORANGE);
                        break;
                    default:
                        gr.setColor(java.awt.Color.ORANGE);
                    }
                    gr.fillRect(x * 24 + 2, y * 24 + 2, 20, 20);
                } else {
                    gr.setColor(java.awt.Color.WHITE);
                    gr.fillRect(x * 24 + 1, y * 24 + 1, 22, 22);
                }

            }
        }
    }

    public boolean isValidPosition(int x, int y, int tokenNumber, int rotationNumber) {
        int[] xArray = xRotationArray[tokenNumber][rotationNumber];
        int[] yArray = yRotationArray[tokenNumber][rotationNumber];

        for (int i = 0; i < 4; i++) {
            int xCell = x + xArray[i];
            int yCell = y + yArray[i];

            if (xCell < 0) {
                return false;
            }
            if (xCell >= 15) {
                return false;
            }
            if (yCell < 0) {
                return false;
            }
            if (yCell >= 30) {
                return false;
            }

            if (occupied[xCell][yCell] == 1) {
                return false;
            }
        }
        return true;
    }

    void addScore(int[] complete) {
        int bonus = 10;
        for (int row = 0; row < complete.length; row++) {
            if (complete[row] == 1) {
                lineCompleted += 1;
                score += bonus;
                bonus *= 2;
            }
        }
        level = lineCompleted / 3;
        if (level > 30) {
            lineCompleted = 0;
            level = 0;
        }
        scoreLabel.setText("SCORE : " + score);
        levelLabel.setText("LEVEL : " + level);
    }

    public void printGameOver() {
        javax.swing.JLabel gameOverLabel = new javax.swing.JLabel("GAME OVER");
        gameOverLabel.setBounds(150, 24 * 30, 100, 30);
        add(gameOverLabel);
        repaint();
    }

    boolean leftPressed = false;
    boolean rightPressed = false;
    boolean downPressed = false;
    boolean spacePressed = false;

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
        if (event.getKeyCode() == 32) {
            spacePressed = true;
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
        if (event.getKeyCode() == 32) {
            spacePressed = false;
        }
    }

    public void keyTyped(java.awt.event.KeyEvent event) {
    };

    public void addFallingToken() {
        int x = (int) (11 * Math.random() + 2), y = 0;
        int tokenNumber, rotationNumber, tokenColor;

        tokenNumber = (int) (7 * Math.random());
        rotationNumber = (int) (4 * Math.random());
        tokenColor = (int) (4 * Math.random());

        int[] xArray = xRotationArray[tokenNumber][rotationNumber];
        int[] yArray = yRotationArray[tokenNumber][rotationNumber];

        if (!isValidPosition(x, y, tokenNumber, rotationNumber)) {
            gameOver = true;
            drawToken(x, y, xArray, yArray, tokenColor);
            repaint();
            return;
        }

        drawToken(x, y, xArray, yArray, tokenColor);
        repaint();

        int delay = 50;
        int frame = 0;
        boolean reachFloor = false;
        while (!reachFloor) {
            try {
                Thread.sleep(delay);
            } catch (Exception ignore) {
            }

            eraseToken(x, y, xArray, yArray);

            // keyboard control
            if (leftPressed && isValidPosition(x - 1, y, tokenNumber, rotationNumber)) {
                x -= 1;
            }
            if (rightPressed && isValidPosition(x + 1, y, tokenNumber, rotationNumber)) {
                x += 1;
            }
            if (downPressed && isValidPosition(x, y + 1, tokenNumber, rotationNumber)) {
                y += 1;
            }
            if (spacePressed && isValidPosition(x, y, tokenNumber, (rotationNumber + 1) % 4)) {
                rotationNumber = (rotationNumber + 1) % 4;
                xArray = xRotationArray[tokenNumber][rotationNumber];
                yArray = yRotationArray[tokenColor][rotationNumber];
                spacePressed = false;
            }

            int f = 30 - level;
            if (frame % f == 0) {
                y += 1;
            }

            if (!isValidPosition(x, y, tokenNumber, rotationNumber)) {
                reachFloor = true;
                y -= 1;
            }

            drawToken(x, y, xArray, yArray, tokenColor);
            repaint();
            frame++;
        }
    }

    public static void main(String[] args) {
        javax.swing.JFrame window = new javax.swing.JFrame("My Tetris");
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

        TetrisReplica tetris = new TetrisReplica();
        tetris.addBackground();

        window.add(tetris);
        window.pack();
        window.setVisible(true);

        window.addKeyListener(tetris);

        tetris.gameOver = false;
        while (!tetris.gameOver) {
            tetris.addFallingToken();
        }
        tetris.printGameOver();
    }
}
