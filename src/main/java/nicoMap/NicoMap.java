package nicoMap;

import player.Player;
import util.Direction;
import util.Position;

public class NicoMap {
    public static int COL_SIZE = 20;
    public static int ROW_SIZE = 10;
    NicoTile[][] tiles = new NicoTile[ROW_SIZE][COL_SIZE];
    Player player;
    Position playerPosition;

    public NicoMap(){
        generateBaseMap();
    }

    private void generateBaseMap(){
        for(int row = 0; row < tiles.length; row++){

            for(int col = 0; col < tiles[row].length; col++){
                if(row == 0 || row == ROW_SIZE-1){
                    tiles[row][col] = new NicoWall(row, col);
                }
                else if(col == 0 || col == COL_SIZE-1){
                    tiles[row][col] = new NicoWall(row, col);

                }
                else{
                    tiles[row][col] = new NicoFloor(row, col);
                }


            }
        }
    }

    public void printMap(){
        for(int row = 0; row < tiles.length; row++){

            System.out.println();
            for(int col = 0; col < tiles[row].length; col++){
                System.out.print(tiles[row][col].getSymbol());


            }
        }
    }

    public void bindPlayer(Player p){
        this.player = p;
        p.bindMap(this);
    }

    public void placePlayer(Player p){
        playerPosition = new Position(1,1);
        tiles[1][1].setPlayer(p);

    }
    public void placePlayer(Player p, Position pos){
        NicoTile target = tiles[pos.row()][pos.col()];

        if(p.canMove(target)){
            target.setPlayer(p);
            playerPosition = new Position(pos.row(),pos.col());
        }

        else{
            throw new IllegalArgumentException("Cannot be placed here");
        }
    }
    public void move(Direction dir){
        if(dir == Direction.RIGHT){
            NicoTile target = tiles[playerPosition.row()][playerPosition.col()+1];
            if(player.canMove(target)){
                target.setPlayer(player);
                tiles[playerPosition.row()][playerPosition.col()].removePlayer();
                playerPosition = playerPosition.newPosition(Direction.RIGHT);
            }
        }
    }

    public void addWall(int length, Direction direction, Position pos){
        if(direction == Direction.DOWN){

                for(int row = pos.row(); row < pos.row() + length; row++){
                    tiles[row][pos.col()] = new NicoWall(row, pos.col());
                }
        }
    }
}
