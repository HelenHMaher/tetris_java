/**************************************
 * File: Tetris1.java Author: helen maher Walk-through: http://java.macteki.com/
 * 
 * Description: GREEN BACKGROUND
 *************************************/

class Tetris1 extends javax.swing.JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public void init() {
        this.setPreferredSize(new java.awt.Dimension(640, 480));
        this.setBackground(java.awt.Color.GREEN);
    }

    public static void main(String[] args) throws Exception {
        javax.swing.JFrame window = new javax.swing.JFrame("Macteki Tetris");
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

        Tetris1 tetris = new Tetris1();
        tetris.init();

        window.add(tetris);
        window.pack();
        window.setVisible(true);
    }
}