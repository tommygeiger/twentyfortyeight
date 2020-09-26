import javax.swing.*;
import java.awt.*;

/**
 *  http://gabrielecirulli.github.io/2048/
 *  Tommy Geiger
 *  30611610
 *  tgeiger3@u.rochester.edu
 */

public class Main {

    public static void main(String[] args) {

        /*
                This project follows an MVC (Model-View-Controller) class layout.
                The Models are the game objects (Board and Tiles), and contain the game logic.
                The View handles graphical output.
                The Controller handles user input, and updates Model and View accordingly.
         */
        Board board = new Board();
        View view = new View(board);
        view.setBackground(new Color(250, 248, 239));

        //Initializing graphics
        JFrame frame = new JFrame("2048");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 722);
        frame.add(view);

        Controller controller = new Controller(board, view, frame);

    }
}

