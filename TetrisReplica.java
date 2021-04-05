/**************************************
 * File: TetrisReplica.java Author: helen maher
 * http://java.macteki.com/2011/06/tetris-from-scratch.html
 * 
 * Description: based on walk-through
 *************************************/

public class TetrisReplica extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;

    public void addBackground() {
        this.setPreferredSize(new java.awt.Dimension(450, 450));
        this.setBackground(java.awt.Color.GRAY);

        this.setLayout(null); // absolute coordiante system
    }

    public static void main(String[] args) {
        javax.swing.JFrame window = new javax.swing.JFrame("My Tetris");
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

        TetrisReplica tetris = new TetrisReplica();
        tetris.addBackground();

        window.add(tetris);
        window.pack();
        window.setVisible(true);

    }
}
