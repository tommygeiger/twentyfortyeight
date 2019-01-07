import java.util.Random;

public class Board {

    private Tile[][] tiles = new Tile[4][4];
    private int score;


    public Board () {           //Initialize new game board

        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < 4; j++) {
                tiles[i][j] = new Tile();
            }
        }

        spawnRandomTile();
        spawnRandomTile();

        score = 0;

    }

    public Tile[][] getTiles () {
        return tiles;
    }

    public int getScore () {
        return score;
    }


    public boolean isGameOver  () {

        if ( !isBoardFull() )        //If board isn't full, game isn't over
            return false;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {

                if (tiles[i][j].getValue() == tiles[i][j+1].getValue())           //if there are any identical adjacent tiles horizontally, it's not gridlocked.
                    return false;
            }
        }


        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 3; i++) {

                if (tiles[i][j].getValue() == tiles[i+1][j].getValue())           //if there are any identical adjacent tiles vertically, its not gridlocked.
                    return false;
            }
        }

        return true;    //If the board is full ad there are no adjacent pairs, the board is gridlocked and the game is over.

    }


    public boolean isBoardFull () {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tiles[i][j].getValue() == 0)
                    return false;
            }
        }
        return true;
    }


    public int getMax () {
        int max = tiles[0][0].getValue();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tiles[i][j].getValue() > max)
                    max = tiles[i][j].getValue();
            }
        }
        return max;
    }


    public boolean shiftUp () {                            //Start at the top, move down through tiles
        boolean valid = false;             //Return true if a valid move could be made

        for (int j = 0; j < 4; j++) {

            for (int i = 1; i < 4; i++) {

                if (tiles[i][j].getValue() != 0) {                                    //If tile isn't null...

                    int cur = i;    //cur means current index

                    while (cur >= 1 && tiles[cur-1][j].getValue() == 0) {                 //While above tile is null...
                        tiles[cur-1][j].setValue(tiles[cur][j].getValue());               //Move this tile up.
                        tiles[cur][j].setValue(0);
                        cur--;                                                            //Move up and repeat until at the top or above tile isn't null
                        valid = true;
                    }

                    if (cur >= 1 && tiles[cur-1][j].getValue() == tiles[cur][j].getValue()) {         //If this tile and above tile are the same...
                        tiles[cur-1][j].setValue(tiles[cur][j].getValue()*2);                       //Double the above tile and remove the below tile.
                        score += tiles[cur][j].getValue()*2;
                        tiles[cur][j].setValue(0);
                        valid = true;
                    }

                }
            }
        }
        return valid;
    }

    public boolean shiftDown () {              //Start at the bottom, move up through tiles
        boolean valid = false;

        for (int j = 0; j < 4; j++) {

            for (int i = 2; i >= 0; i--) {

                if (tiles[i][j].getValue() != 0) {

                    int cur = i;

                    while (cur <= 2 && tiles[cur+1][j].getValue() == 0) {                   //While below tile is null...
                        tiles[cur+1][j].setValue(tiles[cur][j].getValue());                 //Move this tile down.
                        tiles[cur][j].setValue(0);
                        cur++;
                        valid = true;
                    }

                    if (cur <= 2 && tiles[cur+1][j].getValue() == tiles[cur][j].getValue()) {        //If this tile and below tile are the same...
                        tiles[cur+1][j].setValue(tiles[cur][j].getValue()*2);                       //Double the below tile and remove the above tile.
                        score += tiles[cur][j].getValue()*2;
                        tiles[cur][j].setValue(0);
                        valid = true;
                    }

                }
            }
        }
        return valid;
    }

    public boolean shiftRight () {             //Start at right, move left through tiles
        boolean valid = false;

        for (int i = 0; i < 4; i++) {

            for (int j = 2; j >= 0; j--) {

                if (tiles[i][j].getValue() != 0) {                                      //If tile isn't null...

                    int cur = j;

                    while (cur <= 2 && tiles[i][cur+1].getValue() == 0) {                             //While right tile is null...
                        tiles[i][cur+1].setValue(tiles[i][cur].getValue());                           //Move this tile right.
                        tiles[i][cur].setValue(0);
                        cur++;
                        valid = true;
                    }

                    if (cur <= 2 && tiles[i][cur+1].getValue() == tiles[i][cur].getValue()) {           //If this tile and right tile are the same...
                        tiles[i][cur+1].setValue(tiles[i][cur].getValue()*2);                           //Double the right tile and remove the left tile.
                        score += tiles[i][cur].getValue()*2;
                        tiles[i][cur].setValue(0);
                        valid = true;
                    }

                }
            }
        }
        return valid;
    }

    public boolean shiftLeft () {          //Start at left, move right through tiles
        boolean valid = false;

        for (int i = 0; i < 4; i++) {

            for (int j = 1; j < 4; j++) {

                if (tiles[i][j].getValue() != 0) {                                      //If tile isn't null...

                    int cur = j;

                    while (cur >= 1 && tiles[i][cur-1].getValue() == 0) {                             //While left tile is null...
                        tiles[i][cur-1].setValue(tiles[i][cur].getValue());                           //Move this tile left.
                        tiles[i][cur].setValue(0);
                        cur--;
                        valid = true;
                    }

                    if (cur >= 1 && tiles[i][cur-1].getValue() == tiles[i][cur].getValue()) {           //If this tile and left tile are the same...
                        tiles[i][cur-1].setValue(tiles[i][cur].getValue()*2);                           //Double the left tile and remove the right tile.
                        score += tiles[i][cur].getValue()*2;
                        tiles[i][cur].setValue(0);
                        valid = true;
                    }

                }
            }
        }
        return valid;
    }



    public void spawnRandomTile () {
        int r1 = new Random().nextInt(4);
        int r2 = new Random().nextInt(4);
        if ( tiles[r1][r2].getValue() == 0 )
            tiles[r1][r2].setValue( Math.random() > 0.2 ? 2 : 4 );
        else spawnRandomTile();
    }


    // Old code used to print the game board. Useful for debugging.

//    public void print () {
//        for (int i = 0; i < 4 ; i++) {
//            for (int j = 0; j < 4; j++) {
//                System.out.print(tiles[i][j].getValue() + "\t");
//            }
//            System.out.println();
//        }
//        System.out.println();
//    }

}
