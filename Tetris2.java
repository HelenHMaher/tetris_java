/**************************************
 * File: Tetris1.java Author: helen maher Walk-through: http://java.macteki.com/
 * 
 * Description: Step 14 random token test
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

    public void paint(java.awt.Graphics gr) {
        super.paint(gr);

        for (int x = 0; x < 10; x++)
            for (int y = 0; y < 20; y++)
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

    public void randomTokenTest() {
        try {
            Thread.sleep(1000);
        } catch (Exception ignore) {
        }
        int x = (int) (7 * Math.random());
        int y = (int) (15 * Math.random());

        int tokenNumber = (int) (7 * Math.random());
        int rotationNumber = (int) (4 * Math.random());

        int[] xArray = xRotationArray[tokenNumber][rotationNumber];
        int[] yArray = yRotationArray[tokenNumber][rotationNumber];

        drawToken(x, y, xArray, yArray);
        repaint();
    }

    public static void main(String[] args) throws Exception {
        javax.swing.JFrame window = new javax.swing.JFrame("Tetris");
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

        Tetris2 tetris = new Tetris2();
        tetris.init();

        window.add(tetris);
        window.pack();
        window.setVisible(true);

        // randomly draw 20 tokens, without overlap checking
        for (int i = 0; i < 20; i++)
            tetris.randomTokenTest();
    }

}