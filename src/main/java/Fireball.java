import map.Map;
import map.Tile;
import util.FacingDirection;
/*

public class Fireball extends Map {
    int damage = 10;
    int range = 4;
    Tile[] explosionRadius = new Tile[9];

    public Tile setTargetTile(Tile targetTile) {
        targetTile = getMap2dArray()[targetTile.getRow()][targetTile.getColumn()];
        if (targetTile.getRow() - range < 0) {
            targetTile = getMap2dArray()[0][targetTile.getColumn()];
        }
        if (targetTile.getRow() + range > 39) {
            targetTile = getMap2dArray()[39][targetTile.getColumn()];
        }
        if (targetTile.getColumn() - range < 0) {
            targetTile = getMap2dArray()[targetTile.getRow()][1];
        }
        if (targetTile.getColumn() + range > 99) {
            targetTile = getMap2dArray()[targetTile.getRow()][99];
        }
        return targetTile;
    }

    public Tile cast(Tile playerTile, FacingDirection playerFacingDirection) {
        Tile targetTile = setTargetTile(playerTile);
        switch (playerFacingDirection) {
            case UP:
                checkCollisionWithWall(FacingDirection.UP, playerTile, targetTile);
                break;
            case RIGHT:
                checkCollisionWithWall(FacingDirection.RIGHT, playerTile, targetTile);
                break;
            case DOWN:
                checkCollisionWithWall(FacingDirection.DOWN, playerTile, targetTile);
                break;
            case LEFT:
                checkCollisionWithWall(FacingDirection.LEFT, playerTile, targetTile);
            default:
                break;
        }
        return targetTile;
    }

    private Tile checkCollisionWithWall(FacingDirection direction, Tile currentTile, Tile targetTile) {
        Tile nextTile;
        switch (direction) {
            case UP:
                for (int i = targetTile.getRow(); i < currentTile.getRow(); i++) {
                    nextTile = getMap2dArray()[currentTile.getRow() - 1][currentTile.getColumn()];
                    if (nextTile.isVerticalWallTile() || nextTile.isHorizontalWallTile()) {
                        return currentTile;
                    } else
                    if (nextTile.getRow() == targetTile.getRow()) {
                        return nextTile;
                    }
                    currentTile = nextTile;
                }
            default:

                break;
        }
        return targetTile;
    }

}
*/