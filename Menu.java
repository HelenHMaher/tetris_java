import minesweeper.MineSweeperTutorial;

public class Menu extends javax.swing.JPanel implements java.awt.event.KeyListener {

    private static final long serialVersionUID = 1L;

    boolean validUser = true;

    javax.swing.JLabel menuLabel = new javax.swing.JLabel("TYPE the NUMBER to start PLAYING!!");
    javax.swing.JLabel snakeLabel = new javax.swing.JLabel("1.  SNAKE");
    javax.swing.JLabel tetrisLabel = new javax.swing.JLabel("2. TETRIS");
    javax.swing.JLabel minesweeperLabel = new javax.swing.JLabel("3. MINESWEEPER");

    public void addBackground() {
        this.setPreferredSize(new java.awt.Dimension(300, 200));
        this.setBackground(java.awt.Color.GRAY);
        this.setLayout(null);

        menuLabel.setBounds(20, 20, 300, 30);
        snakeLabel.setBounds(25, 80, 100, 30);
        tetrisLabel.setBounds(25, 110, 100, 30);
        minesweeperLabel.setBounds(25, 140, 130, 30);

        this.add(menuLabel);
        this.add(snakeLabel);
        this.add(tetrisLabel);
        this.add(minesweeperLabel);
    }

    boolean onePressed = false;
    boolean twoPressed = false;
    boolean threePressed = false;

    public void keyPressed(java.awt.event.KeyEvent event) {
        if (event.getKeyCode() == 49) {
            onePressed = true;
        }
        if (event.getKeyCode() == 50) {
            twoPressed = true;
        }
        if (event.getKeyCode() == 51) {
            threePressed = true;
        }
    }

    public void keyReleased(java.awt.event.KeyEvent event) {
        if (event.getKeyCode() == 49) {
            onePressed = false;
        }
        if (event.getKeyCode() == 50) {
            twoPressed = false;
        }
        if (event.getKeyCode() == 51) {
            threePressed = false;
        }
    }

    public void keyTyped(java.awt.event.KeyEvent event) {
    }

    public static void main(String[] args) throws Exception {
        javax.swing.JFrame window = new javax.swing.JFrame("Game Menu");
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

        Menu menu = new Menu();
        menu.addBackground();

        window.add(menu);
        window.pack();
        window.setVisible(true);

        window.addKeyListener(menu);

        while (menu.validUser) {
            try {
                Thread.sleep(50);
            } catch (Exception ignore) {

            }
            if (menu.onePressed) {
                Snake.main(args);
            }
            if (menu.twoPressed) {
                Tetris.main(args);
            }
            if (menu.threePressed) {
                MineSweeperTutorial.main(args);
            }
        }
    }
}
