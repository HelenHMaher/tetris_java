/**************************************
 * File: Tetris1.java Author: helen maher Walk-through: http://java.macteki.com/
 * 
 * Description: Step 4 draw a token
 *************************************/

class Tetris2 extends javax.swing.JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public void init() {
        this.setPreferredSize(new java.awt.Dimension(640, 480));
        this.setBackground(java.awt.Color.GREEN);
    }

    public void drawCell(java.awt.Graphics gr, int x, int y) {
        gr.setColor(java.awt.Color.BLACK);
        gr.fillRect(0, 0, 24, 24);
        gr.setColor(java.awt.Color.RED);
        gr.fillRect(1, 1, 22, 22);
    }

    public void paint(java.awt.Graphics gr) {
        super.paint(gr);
        drawCell(gr, 0, 0);
        drawCell(gr, 0, 1 * 24);
        drawCell(gr, 1 * 24, 0);
        drawCell(gr, 2 * 24, 0);
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