
/**************************************
 * File: Tetris.java Author: helen maher
 * http://java.macteki.com/2011/06/tetris-from-scratch.html
 * 
 * Description: additions to the walk-through
 *************************************/

class Tetris extends javax.swing.JPanel implements java.awt.event.KeyListener {

    private static final long serialVersionUID = 1L;

    int[][] occupied = new int[10][20];
    int[][] colorCode = new int[10][20];
    boolean validPlayer = true;

    // [two tokens] [ four rotations ] [ four cells ]
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

    int score = 0; // score
    int lineCompleted = 0; // number of lines completed
    int level = 0;

    javax.swing.JLabel scoreLabel = new javax.swing.JLabel("SCORE : 0");
    javax.swing.JLabel levelLabel = new javax.swing.JLabel("LEVEL : 0");

    public void init() {
        this.setPreferredSize(new java.awt.Dimension(440, 480));
        this.setBackground(java.awt.Color.GRAY);

        this.setLayout(null); // absolute coordinate system

        scoreLabel.setBounds(250, 50, 100, 30); // x,y,w,h (in pixels)
        this.add(scoreLabel);

        levelLabel.setBounds(250, 100, 100, 30);
        this.add(levelLabel);
    }

    public void drawCell(int x, int y, int color) {
        occupied[x][y] = 1;
        colorCode[x][y] = color;
    }

    public void eraseCell(int x, int y) {
        occupied[x][y] = 0;
    }

    public void drawToken(int x, int y, int[] xArray, int[] yArray, int color) {
        for (int i = 0; i < 4; i++) {
            drawCell(x + xArray[i], y + yArray[i], color);
        }
    }

    public void eraseToken(int x, int y, int[] xArray, int[] yArray) {
        for (int i = 0; i < 4; i++) {
            eraseCell(x + xArray[i], y + yArray[i]);
        }
    }

    public void paint(java.awt.Graphics gr) {
        super.paint(gr);

        for (int x = 0; x < occupied.length; x++)
            for (int y = 0; y < occupied[0].length; y++)
                if (occupied[x][y] == 1) {
                    // draw cell
                    gr.setColor(java.awt.Color.BLACK);
                    gr.fillRect(x * 24, y * 24, 24, 24);
                    switch (colorCode[x][y]) {
                    case 0:
                        gr.setColor(java.awt.Color.RED);
                        break;
                    case 1:
                        gr.setColor(java.awt.Color.YELLOW);
                        break;
                    case 2:
                        gr.setColor(java.awt.Color.GREEN);
                        break;
                    case 3:
                        gr.setColor(java.awt.Color.PINK);
                        break;
                    case 4:
                        gr.setColor(java.awt.Color.ORANGE);
                        break;
                    case 5:
                        gr.setColor(java.awt.Color.BLUE);
                        break;
                    default:
                        gr.setColor(java.awt.Color.BLUE);
                    }
                    gr.fillRect(x * 24 + 1, y * 24 + 1, 22, 22);
                } else {
                    // erase cell
                    gr.setColor(java.awt.Color.WHITE);
                    gr.fillRect(x * 24, y * 24, 24, 24);
                }
    }

    public boolean isValidPosition(int x, int y, int tokenNumber, int rotationNumber) {
        int[] xArray = xRotationArray[tokenNumber][rotationNumber];
        int[] yArray = yRotationArray[tokenNumber][rotationNumber];

        for (int i = 0; i < 4; i++) // loop over the four cells
        {
            int xCell = x + xArray[i];
            int yCell = y + yArray[i];

            // range check
            if (xCell < 0)
                return false;
            if (xCell >= 10)
                return false;
            if (yCell < 0)
                return false;
            if (yCell >= 20)
                return false;

            // occupancy check
            if (occupied[xCell][yCell] == 1)
                return false;
        }
        return true;
    }

    public void clearCompleteRow(int[] completed) {
        // must loop for odd number of times
        // toggle sequence : 0,1,0,1,0
        for (int blinking = 0; blinking < 5; blinking++) {
            for (int i = 0; i < completed.length; i++) {
                if (completed[i] == 1) {
                    for (int x = 0; x < 10; x++) {
                        // toggle the occupancy array
                        occupied[x][i] = 1 - occupied[x][i];
                    }
                }
            }
            repaint();
            try {
                Thread.sleep(100);
            } catch (Exception ignore) {
            }
        }
    }

    public void shiftDown(int[] completed) {
        for (int row = 0; row < completed.length; row++) {
            if (completed[row] == 1) {
                for (int y = row; y >= 1; y--) {
                    for (int x = 0; x < 10; x++) {
                        occupied[x][y] = occupied[x][y - 1];
                    }
                }
            }
        }
    }

    public void checkRowCompletion() {
        int[] complete = new int[20];
        for (int y = 0; y < 20; y++) // 20 rows
        {
            int filledCell = 0;
            for (int x = 0; x < 10; x++) // 10 columns
            {
                if (occupied[x][y] == 1)
                    filledCell++;
                if (filledCell == 10) // row completed
                {
                    complete[y] = 1;
                }
            }
        }
        clearCompleteRow(complete);
        shiftDown(complete);
        addScore(complete);
    }

    void addScore(int[] complete) {
        int bonus = 10; // score for the first completed line
        for (int row = 0; row < complete.length; row++) {
            if (complete[row] == 1) {
                lineCompleted += 1;
                score += bonus;
                bonus *= 2; // double the bonus for every additional line
            }
        }

        // advance level for every 3 completed lines
        level = lineCompleted / 3;
        if (level > 30) {
            lineCompleted = 0;
            level = 0;
        } // MAX LEVEL
        scoreLabel.setText("SCORE : " + score);
        levelLabel.setText("LEVEL : " + level);
    }

    boolean gameOver = false;

    public void addFallingToken() {
        int x = (int) (Math.random() * 7), y = 0;
        int tokenNumber, rotationNumber, color;

        tokenNumber = (int) (7 * Math.random());
        rotationNumber = (int) (4 * Math.random());
        color = (int) (6 * Math.random());

        int[] xArray = xRotationArray[tokenNumber][rotationNumber];
        int[] yArray = yRotationArray[tokenNumber][rotationNumber];

        if (!isValidPosition(x, y, tokenNumber, rotationNumber)) {
            gameOver = true;
            drawToken(x, y, xArray, yArray, color);
            repaint();
            return;
        }

        drawToken(x, y, xArray, yArray, color);
        repaint();

        int delay = 50; // mini second
        int frame = 0;
        boolean reachFloor = false;
        while (!reachFloor) {
            try {
                Thread.sleep(delay);
            } catch (Exception ignore) {
            }
            eraseToken(x, y, xArray, yArray);

            // add keyboard control
            if (leftPressed && isValidPosition(x - 1, y, tokenNumber, rotationNumber))
                x -= 1;
            if (rightPressed && isValidPosition(x + 1, y, tokenNumber, rotationNumber))
                x += 1;
            if (downPressed && isValidPosition(x, y + 1, tokenNumber, rotationNumber))
                y += 1;
            if (spacePressed && isValidPosition(x, y, tokenNumber, (rotationNumber + 1) % 4)) {
                rotationNumber = (rotationNumber + 1) % 4;
                xArray = xRotationArray[tokenNumber][rotationNumber];
                yArray = yRotationArray[tokenNumber][rotationNumber];
                spacePressed = false;
            }

            int f = 30 - level; // fall for every 30 frames, this value is decreased when level up
            if (frame % f == 0)
                y += 1;

            if (!isValidPosition(x, y, tokenNumber, rotationNumber)) // reached floor
            {
                reachFloor = true;
                y -= 1; // restore position
            }
            drawToken(x, y, xArray, yArray, color);
            repaint();
            frame++;
        }
    }

    public void printGameOver() {
        javax.swing.JLabel gameOverLabel = new javax.swing.JLabel("GAME OVER");
        javax.swing.JLabel restartLabel = new javax.swing.JLabel("to RESTART");
        javax.swing.JLabel spaceLabel = new javax.swing.JLabel("PRESS the SPACEBAR");

        gameOverLabel.setBounds(250, 300, 100, 30);
        restartLabel.setBounds(250, 360, 200, 30);
        spaceLabel.setBounds(250, 340, 200, 30);

        add(gameOverLabel);
        add(restartLabel);
        add(spaceLabel);
        repaint();

        while (gameOver) {
            try {
                Thread.sleep(100);
            } catch (Exception ignore) {
            }
            if (spacePressed) {
                gameOver = false;
            }
        }

        remove(gameOverLabel);
        remove(restartLabel);
        remove(spaceLabel);
        repaint();
    }

    boolean leftPressed = false;
    boolean rightPressed = false;
    boolean downPressed = false;
    boolean spacePressed = false;

    // must implement this method for KeyListener
    public void keyPressed(java.awt.event.KeyEvent event) {
        // System.out.println(event);
        if (event.getKeyCode() == 37) // left arrow
        {
            leftPressed = true;
        }
        if (event.getKeyCode() == 39) // right arrow
        {
            rightPressed = true;
        }
        if (event.getKeyCode() == 40) // down arrow
        {
            downPressed = true;
        }
        if (event.getKeyCode() == 32) // space
        {
            spacePressed = true;
        }
    }

    // must implement this method for KeyListener
    public void keyReleased(java.awt.event.KeyEvent event) {
        // System.out.println(event);
        if (event.getKeyCode() == 37) // left arrow
        {
            leftPressed = false;
        }
        if (event.getKeyCode() == 39) // right arrow
        {
            rightPressed = false;
        }
        if (event.getKeyCode() == 40) // down arrow
        {
            downPressed = false;
        }
        if (event.getKeyCode() == 32) // space
        {
            spacePressed = false;
        }
    }

    // must implement this method for KeyListener
    public void keyTyped(java.awt.event.KeyEvent event) {
        // System.out.println(event);
    }

    public void playGame() {
        GAME: while (validPlayer) {

            while (!gameOver) {
                addFallingToken();
                checkRowCompletion();
            }

            printGameOver();

            // clear the matrix
            for (int i = 0; i < occupied.length; i++) {
                for (int j = 0; j < occupied[0].length; j++) {
                    occupied[i][j] = 0;
                    colorCode[i][j] = 0;
                }
            }
            repaint();
            continue GAME;

        }
    }

    public static void main(String[] args) throws Exception {
        javax.swing.JFrame window = new javax.swing.JFrame("Tetris Clone");
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

        Tetris tetris = new Tetris();
        tetris.init();

        window.add(tetris);
        window.pack();
        window.setVisible(true);

        try {
            Thread.sleep(1000);
        } catch (Exception ignore) {
        }

        window.addKeyListener(tetris); // listen to keyboard event

        tetris.gameOver = false;

        tetris.playGame();
    }

}