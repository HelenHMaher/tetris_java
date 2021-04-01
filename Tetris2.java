/**************************************
 * File: Tetris1.java Author: helen maher Walk-through: http://java.macteki.com/
 * 
 * Description: Step 10
 *************************************/

class Tetris2 extends javax.swing.JPanel {

    // private static final long serialVersionUID = 1L;

    int[][] occupied = new int[10][20];

    public void init() {
        this.setPreferredSize(new java.awt.Dimension(640, 480));
        this.setBackground(java.awt.Color.GREEN);

        // array of relative position
        int[] xArray = { 0, 0, 1, 2 };
        int[] yArray = { 0, 1, 0, 0 };

        drawToken(0, 0, xArray, yArray);

        // first rotation
        xArray = new int[] { 0, 0, 0, 1 };
        yArray = new int[] { 0, 1, 2, 2 };
        drawToken(0, 5, xArray, yArray);

        // second rotation
        xArray = new int[] { 2, 0, 1, 2 };
        yArray = new int[] { 0, 1, 1, 1 };
        drawToken(0, 10, xArray, yArray);

        // third rotation
        xArray = new int[] { 0, 1, 1, 1 };
        yArray = new int[] { 0, 0, 1, 2 };
        drawToken(0, 15, xArray, yArray);
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

    public static void main(String[] args) throws Exception {
        javax.swing.JFrame window = new javax.swing.JFrame("Tetris");
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

        Tetris2 tetris = new Tetris2();
        tetris.init();

        window.add(tetris);
        window.pack();
        window.setVisible(true);
    }

}