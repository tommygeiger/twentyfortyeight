import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Controller {

    private Board board;
    private View view;
    private JFrame frame;

    private static final String UP = "move up";
    private static final String DOWN = "move down";
    private static final String RIGHT = "move right";
    private static final String LEFT = "move left";
    private static final String QUIT = "quit";
    private static final String RESTART = "restart";

    private int moveCount;

    public Controller(Board b, View v, JFrame f) {
        board = b;
        view = v;
        frame = f;

        InputMap iMap = v.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);       //Only respond when window is selected
        ActionMap aMap = v.getActionMap();

        iMap.put(KeyStroke.getKeyStroke("W"), UP);                             //Assigning keybinds
        iMap.put(KeyStroke.getKeyStroke("S"), DOWN);
        iMap.put(KeyStroke.getKeyStroke("D"), RIGHT);
        iMap.put(KeyStroke.getKeyStroke("A"), LEFT);
        iMap.put(KeyStroke.getKeyStroke("Q"), QUIT);
        iMap.put(KeyStroke.getKeyStroke("R"), RESTART);

        aMap.put(UP, new MoveAction(1));                               //Assigning actions to keybinds
        aMap.put(DOWN, new MoveAction(2));
        aMap.put(RIGHT, new MoveAction(3));
        aMap.put(LEFT, new MoveAction(4));

        aMap.put(QUIT, new Quit());
        aMap.put(RESTART, new Restart());

    }

    /*
     *  1 = up
     *  2 = down
     *  3 = right
     *  4 = down
     */
    private class MoveAction extends AbstractAction {

        int dir;

        MoveAction(int direction) {
            dir = direction;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            switch (dir) {
                case 1:

                    if ( board.shiftUp() ) {            //Shift methods return true if the move is valid.
                        board.spawnRandomTile();
                        moveCount++;
                    }

                    break;
                case 2:

                    if ( board.shiftDown() ) {
                        board.spawnRandomTile();
                        moveCount++;
                    }

                    break;
                case 3:

                    if ( board.shiftRight() ) {
                        board.spawnRandomTile();
                        moveCount++;
                    }

                    break;
                case 4:

                    if ( board.shiftLeft() ) {
                        board.spawnRandomTile();
                        moveCount++;
                    }

                    break;
            }
            view.repaint();

            if (board.isGameOver())     //AFTER each move, check if it's game over.
                gameOver();
        }
    }


    private class Quit extends AbstractAction {

        Quit() { }

        @Override
        public void actionPerformed(ActionEvent e) {
            quitGame();
        }
    }

    private class Restart extends AbstractAction {

        Restart() { }

        @Override
        public void actionPerformed(ActionEvent e) {
            restartGame();
        }
    }


    private void quitGame () {

        JFrame menu = new JFrame();
        menu.setVisible(true);
        menu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        int n = JOptionPane.showConfirmDialog(menu, "Are you sure you want to quit?", null, JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {

            System.out.println(moveCount + " valid moves were made.");
            System.out.println("The highest tile on the board was " + board.getMax() + ".");

            System.exit(0);
        } else
            menu.dispose();

    }


    private void restartGame () {

        JFrame menu = new JFrame();
        menu.setVisible(true);
        menu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        int n = JOptionPane.showConfirmDialog(menu, "Are you sure you want to restart?", null, JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {

            frame.dispose();
            int h = view.getHighScore();

            frame = new JFrame("2048");                                 //Do everything that the main method did
            frame.setVisible(true);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setSize(500, 722);
            board = new Board();
            view = new View(board, h);
            view.setBackground(new Color(250, 248, 239));
            frame.add(view);
            Controller controller = new Controller(board,view,frame);

            menu.dispose();
        } else
            menu.dispose();

    }


    private void gameOver () {

            JFrame f = new JFrame();
            f.setVisible(true);
            f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            String[] options = new String[] {"Restart", "Quit"};

            int m = JOptionPane.showOptionDialog(f, "Game Over!", null, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            if (m == 0) {
                restartGame();
            } else {
                quitGame();
            }
    }



}
