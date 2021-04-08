import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MineSweeper extends javax.swing.JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    int[][] revealed = new int[15][15];
    int[][] bomb = new int[15][15];

    public void addBackground() {
        this.setPreferredSize(new java.awt.Dimension(15 * 24, 16 * 24));
        this.setBackground(java.awt.Color.darkGray);

        this.setLayout(null);
    }

    public void paint(java.awt.Graphics gr) {
        super.paint(gr);
        for (int x = 0; x < revealed.length; x++) {
            for (int y = 0; y < revealed[0].length; y++) {
                if (revealed[x][y] == 1 && bomb[x][y] == 1) {
                    gr.setColor(java.awt.Color.darkGray);
                    gr.fillRect(x * 24, y * 24, 24, 24);
                    gr.setColor(java.awt.Color.GRAY);
                    gr.fillRect(x * 24 + 1, y * 24 + 1, 22, 22);
                    gr.setColor(java.awt.Color.RED);
                    gr.fillRect(x * 24 + 4, y * 24 + 4, 18, 18);
                } else if (revealed[x][y] == 1) {
                    gr.setColor(java.awt.Color.darkGray);
                    gr.fillRect(x * 24, y * 24, 24, 24);
                    gr.setColor(java.awt.Color.GRAY);
                    gr.fillRect(x * 24 + 1, y * 24 + 1, 22, 22);
                } else {
                    JButton button = new JButton();
                    button.setBounds(x * 24, y * 24, 24, 24);
                    this.add(button);
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            revealed[0][0] = 1;
                            repaint();

                        }
                    });
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }

    // public void addRestart() {
    // JButton button = new JButton("restart");
    // button.setBounds(6 * 24, 15 * 24, 50, 24);
    // this.add(button);
    // button.addActionListener(this);
    // }

    // @Override
    // public void actionPreformed(ActionEvent e) {
    // revealed[0][0] = 1;
    // }

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
