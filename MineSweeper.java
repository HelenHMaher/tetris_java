
public class MineSweeper extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;

    public void addBackground() {
        this.setPreferredSize(new java.awt.Dimension(15 * 24, 16 * 24));
        this.setBackground(java.awt.Color.GRAY);

        this.setLayout(null);
    }

public void pain(java.awt.Graphics gr) {
    super.paint(gr);

    
}

    public static void main(String[] args) {
        javax.swing.JFrame window = new javax.swing.JFrame("Mine Sweeper");
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

        MineSweeper mineSweeper = new MineSweeper();
        mineSweeper.addBackground();

        window.add(mineSweeper);
        window.pack();
        window.setVisible(true);

    }
}
