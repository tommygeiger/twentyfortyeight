import javax.swing.*;
import java.awt.*;

public class View extends JPanel {

    private Font small = new Font("Helvetica Neue", 0, 15);
    private Font smallBold = new Font("Helvetica Neue", 1, 11);
    private Font medBold = new Font("Helvetica Neue", 1, 13);
    private Font bigBold = new Font("Helvetica Neue", 1, 70);
    private Font score = new Font("Helvetica Neue", 1, 25);
    private Font num1 = new Font("Helvetica Neue", 1, 50);
    private Font num2 = new Font("Helvetica Neue", 1, 45);
    private Font num3 = new Font("Helvetica Neue", 1, 40);
    private Font num4 = new Font("Helvetica Neue", 1, 35);

    private Color darkFont = new Color(119, 110, 101);
    private Color lightFont = new Color(249, 246, 242);
    private Color whiteFont = new Color(255, 255, 255);
    private Color boxColor = new Color(187, 173, 160);
    private Color gridBoxColor = new Color(204, 193, 180);
    private Color newGameBoxColor = new Color(143, 122, 102);

    private Board board;
    private Tile[][] tiles;

    private int highScore;

    public View(Board b) {
        board = b;
        tiles = b.getTiles();

        highScore = 0;
    }

    public View (Board b, int h) {
        board = b;
        tiles = b.getTiles();

        highScore = h;
    }

    public int getHighScore () {
        return highScore;
    }


    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;

        //drawing boxes
        g.setColor(boxColor);
        g.fillRoundRect(50, 250, 400, 400, 15, 15);   //board box
        g.fillRoundRect(250, 50, 95, 60, 5, 5);       //score box
        g.fillRoundRect(355, 50, 95, 60, 5, 5);       //high score box


        //drawing grid
        g.setColor(gridBoxColor);
        g.fillRoundRect(58, 258, 90, 90, 5, 5);
        g.fillRoundRect(156, 258, 90, 90, 5, 5);
        g.fillRoundRect(254, 258, 90, 90, 5, 5);
        g.fillRoundRect(352, 258, 90, 90, 5, 5);

        g.fillRoundRect(58, 356, 90, 90, 5, 5);
        g.fillRoundRect(156, 356, 90, 90, 5, 5);
        g.fillRoundRect(254, 356, 90, 90, 5, 5);
        g.fillRoundRect(352, 356, 90, 90, 5, 5);

        g.fillRoundRect(58, 454, 90, 90, 5, 5);
        g.fillRoundRect(156, 454, 90, 90, 5, 5);
        g.fillRoundRect(254, 454, 90, 90, 5, 5);
        g.fillRoundRect(352, 454, 90, 90, 5, 5);

        g.fillRoundRect(58, 552, 90, 90, 5, 5);
        g.fillRoundRect(156, 552, 90, 90, 5, 5);
        g.fillRoundRect(254, 552, 90, 90, 5, 5);
        g.fillRoundRect(352, 552, 90, 90, 5, 5);

        //drawing text
        g.setFont(bigBold);
        g.setColor(darkFont);
        g.drawString("2048", 50, 150);
        g.setFont(smallBold);
        g.drawString("2048 tile!", 268, 200);
        g.setFont(small);
        g.drawString("Join the numbers and get to the ", 50, 200);

        g.setFont(smallBold);
        g.setColor(lightFont);
        g.drawString("SCORE", 277, 68);
        g.drawString("BEST", 387, 68);

        //drawing score
        g.setFont(score);
        g.setColor(whiteFont);
        g.drawString("" + board.getScore(), 261, 95);

        if (board.getScore() > highScore)
            highScore = board.getScore();

        g.drawString("" + highScore, 366, 95);


        //new game drawing
        g.setColor(newGameBoxColor);
        g.fillRoundRect(350, 179, 100, 32, 5, 5);     //new game box
        g.setFont(medBold);
        g.setColor(whiteFont);
        g.drawString("New Game", 365, 200);


        //drawing tiles
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tiles[i][j].getValue() != 0) {

                    g.setColor(tiles[i][j].getColor());
                    g.fillRoundRect(58 + (j * 98), 258 + (i * 98), 90, 90, 5, 5);

                    if (tiles[i][j].getValue() == 2 || tiles[i][j].getValue() == 4)            //2 and 4 uses dark font, all others use light font
                        g.setColor(darkFont);
                    else
                        g.setColor(lightFont);

                    if (tiles[i][j].getValue() < 10) {
                        g.setFont(num1);
                        g.drawString("" + tiles[i][j].getValue(), 85 + (j * 98), 320 + (i * 98));
                    } else if (tiles[i][j].getValue() < 100) {
                        g.setFont(num2);
                        g.drawString("" + tiles[i][j].getValue(), 75 + (j * 98), 320 + (i * 98));
                    } else if (tiles[i][j].getValue() < 1000) {
                        g.setFont(num3);
                        g.drawString("" + tiles[i][j].getValue(), 69 + (j * 98), 320 + (i * 98));
                    } else {
                        g.setFont(num4);
                        g.drawString("" + tiles[i][j].getValue(), 65 + (j * 98), 318 + (i * 98));
                    }

                }

            }
        }

    }

}


