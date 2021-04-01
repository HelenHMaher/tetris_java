/**************************************
 * File: Tetris1.java Author: helen maher Walk-through: http://java.macteki.com/
 * 
 * Description: RED CELL
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

    public void paint(java.awt.Graphics gr) {
        super.paint(gr);
        gr.setColor(java.awt.Color.BLACK);
        gr.fillRect(0, 0, 24, 24);
        gr.setColor(java.awt.Color.RED);
        gr.fillRect(1, 1, 22, 22);
    }

    public static void main(String[] args) throws Exception {
        javax.swing.JFrame window = new javax.swing.JFrame("Maher Tetris");
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

        Tetris2 tetris = new Tetris2();
        tetris.init();

        window.add(tetris);
        window.pack();
        window.setVisible(true);
    }

}