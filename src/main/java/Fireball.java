public class Fireball extends Map {
    int damage = 10;
    int range = 6;
    Tile[] explosionRadius = new Tile[9];
    Tile targetTile;
    public Tile cast(Tile playerTile, int playerFacingDirection) {
        targetTile = playerTile;
        switch (playerFacingDirection) {
            case 1:
                targetTile = getTile(playerTile.getX(), playerTile.getY() + 6); //UP
                break;
            case 2:
                targetTile = getTile(playerTile.getX() + 6, playerTile.getY()); //RIGHT
                break;
            case 3:
                targetTile = getTile(playerTile.getX(), playerTile.getY() - 6); //DOWN
                break;
            case 4:
                targetTile = getTile(playerTile.getX() - 6, playerTile.getY()); //LEFT
            default:
                break;
        }
        return targetTile;
    }
}
