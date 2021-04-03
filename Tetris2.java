/**************************************
 * File: Tetris2.java Author: helen maher
 * http://java.macteki.com/2011/06/tetris-from-scratch.html
 * 
 * Description: Step 18
 *************************************/

class Tetris2 extends javax.swing.JPanel {

    // private static final long serialVersionUID = 1L;

    int[][] occupied = new int[10][20];

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

    public void init() {
        this.setPreferredSize(new java.awt.Dimension(640, 480));
        this.setBackground(java.awt.Color.GREEN);
    }

    public void drawCell(int x, int y) {
        occupied[x][y] = 1;
    }

    public void eraseCell(int x, int y) {
        occupied[x][y] = 0;
    }

    public void drawToken(int x, int y, int[] xArray, int[] yArray) {
        for (int i = 0; i < 4; i++) {
            drawCell(x + xArray[i], y + yArray[i]);
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
                    gr.setColor(java.awt.Color.RED);
                    gr.fillRect(x * 24 + 1, y * 24 + 1, 22, 22);
                } else {
                    // erase cell
                    gr.setColor(java.awt.Color.BLACK);
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

    public void randomTokenTest() {
        try {
            Thread.sleep(1000);
        } catch (Exception ignore) {
        }

        int x, y, tokenNumber, rotationNumber;

        while (true) {

            x = (int) (10 * Math.random());
            y = (int) (20 * Math.random());

            tokenNumber = (int) (7 * Math.random());
            rotationNumber = (int) (4 * Math.random());

            if (isValidPosition(x, y, tokenNumber, rotationNumber))
                break;

        }

        int[] xArray = xRotationArray[tokenNumber][rotationNumber];
        int[] yArray = yRotationArray[tokenNumber][rotationNumber];

        drawToken(x, y, xArray, yArray);
        repaint();
    }

    boolean gameOver = false;

    public void addFallingToken() {
        int x = 5, y = 0;
        int tokenNumber, rotationNumber;

        tokenNumber = (int) (7 * Math.random());
        rotationNumber = (int) (4 * Math.random());

        int[] xArray = xRotationArray[tokenNumber][rotationNumber];
        int[] yArray = yRotationArray[tokenNumber][rotationNumber];

        if (!isValidPosition(x, y, tokenNumber, rotationNumber)) {
            gameOver = true;
            drawToken(x, y, xArray, yArray);
            repaint();
            return;
        }

        drawToken(x, y, xArray, yArray);
        repaint();

        int delay = 100; // mini second
        boolean reachFloor = false;
        while (!reachFloor) {
            try {
                Thread.sleep(delay);
            } catch (Exception ignore) {
            }
            eraseToken(x, y, xArray, yArray);
            y += 1; // falling
            if (!isValidPosition(x, y, tokenNumber, rotationNumber)) // reach floor
            {
                reachFloor = true;
                y -= 1; // restore position
            }
            drawToken(x, y, xArray, yArray);
            repaint();
        }
    }

    public void printGameOver() {
        javax.swing.JLabel gameOverLabel = new javax.swing.JLabel("GAME OVER");
        gameOverLabel.setBounds(300, 300, 100, 30);
        add(gameOverLabel);
        repaint();
    }

    public static void main(String[] args) throws Exception {
        javax.swing.JFrame window = new javax.swing.JFrame("Tetris Clone");
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

        Tetris2 tetris = new Tetris2();
        tetris.init();

        window.add(tetris);
        window.pack();
        window.setVisible(true);

        try {
            Thread.sleep(1000);
        } catch (Exception ignore) {
        }

        tetris.gameOver = false;
        while (!tetris.gameOver)
            tetris.addFallingToken();

        tetris.printGameOver();
    }

}