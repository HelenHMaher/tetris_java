/**************************************
 * File: TetrisReplica.java Author: helen maher
 * http://java.macteki.com/2011/06/tetris-from-scratch.html
 * 
 * Description: based on walk-through
 *************************************/

public class TetrisReplica extends javax.swing.JPanel {

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

    public void addBackground() {
        this.setPreferredSize(new java.awt.Dimension(450, 450));
        this.setBackground(java.awt.Color.GRAY);

        this.setLayout(null); // absolute coordiante system
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
                }
            }
        }
    }

    public void addFallingToken() {
        int x = 5, y = 0;
        int tokenNumber, rotationNumber, tokenColor;

        tokenNumber = (int) (7 * Math.random());
        rotationNumber = (int) (4 * Math.random());
        tokenColor = (int) (6 * Math.random());

        int[] xArray = xRotationArray[tokenNumber][rotationNumber];
        int[] yArray = yRotationArray[tokenNumber][rotationNumber];

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

            if (frame % 30 == 0) {
                y += 1;
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

        tetris.addFallingToken();

    }
}
