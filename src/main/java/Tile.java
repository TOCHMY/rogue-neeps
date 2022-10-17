public class Tile {
    private boolean walkable = false;
    private boolean roomTile = false;
    private boolean hasPlayer = false;
    private boolean hasEnemyNPC = false;
    private boolean horizontalWall = false;
    private boolean verticalWall = false;
    private Room room;
    private int x;
    private int y;


    // +1 för att börja räkna spelplanen från X1Y1 istället för X0Y0.
    Tile(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void makeRoomTile(Room room){
        makeWalkable();
        roomTile = true;
        this.room = room;
    }

    public void makeWalkable() {
        walkable = true;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public boolean isRoomTile(){
        return roomTile;
    }

    public Room getRoom() {
        return room;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPlayerOnTile() {
        hasPlayer = true;
    }

    public boolean hasPlayer() {
        return hasPlayer;
    }


    public void setEnemyNpcOnTile() {
        hasEnemyNPC = true;
        walkable = false;
        //enemyNPC = null;
    }

    public boolean hasEnemyNPC() {
        return hasEnemyNPC;
    }

    public void makeVerticalWallTile() {
        verticalWall = true;
        walkable = false;
    }

    public void makeHorizontalWallTile() {
        horizontalWall = true;
        walkable = false;
    }

    public boolean isVerticalWallTile() {
        return verticalWall;
    }

    public boolean isHorizontalWallTile() {
        return horizontalWall;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "x" + x +
                ", y" + y +
                ", walkable=" + walkable +
                ", roomTile=" + roomTile +
                ", hasPlayer=" + hasPlayer +
                ", hasEnemyNPC=" + hasEnemyNPC +
                ", horizontalWall=" + horizontalWall +
                ", verticalWall=" + verticalWall +
                ", room=" + room +
                '}';
    }



    // använd denna när vi har en klass för enemyNPC
    /*public void setEnemyNpcOnTile(Enemy enemyNPC) {
        hasEnemyNPC = true;
        this.enemyNPC = enemyNPC;
    }*/
}
