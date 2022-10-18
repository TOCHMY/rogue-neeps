public class Fireball extends Map {
    int damage = 10;
    int range = 4;
    Tile[] explosionRadius = new Tile[9];

    public Tile cast(Tile playerTile, FacingDirection playerFacingDirection) {
        Tile targetTile = playerTile;
        switch (playerFacingDirection) {
            case UP:
                targetTile = getPerimeterArray()[playerTile.getX()][playerTile.getY() + range];
                checkCollisionWithWall(FacingDirection.UP, playerTile, targetTile);
                break;
            case RIGHT:
                targetTile = getTile(playerTile.getX() + range, playerTile.getY());
                break;
            case DOWN:
                targetTile = getTile(playerTile.getX(), playerTile.getY() - range);
                break;
            case LEFT:
                targetTile = getTile(playerTile.getX() - range, playerTile.getY());
            default:
                break;
        }
        return targetTile;
    }

    private Tile checkCollisionWithWall(FacingDirection direction, Tile currentTile, Tile targetTile) {
        Tile nextTile;
        switch (direction) {
            case UP:
                System.out.println("Target Tile: " + targetTile);
                for (int i = 0; i < targetTile.getY(); i++) {
                    System.out.println("Current Tile: " + currentTile);
                    if (currentTile.getY() == targetTile.getY()) {
                        return currentTile;
                    } else {
                        nextTile = getPerimeterArray()[currentTile.getX()][currentTile.getY() + 1];
                        System.out.println(currentTile.getY());
                        if (nextTile.isVerticalWallTile() || nextTile.isHorizontalWallTile()) {
                            return currentTile;
                        }
                        currentTile = nextTile;
                    }
                }
                break;

            default:

                break;
        }
        return targetTile;
    }
}
/*
case DOWN:
        while (currentTile.getY() != targetTile.getY()) {
        nextTile = getTile(currentTile.getX(), currentTile.getY() - 1);
        if (nextTile.isHorizontalWallTile() || nextTile.isVerticalWallTile()) {
        return currentTile;
        }
        }
        break;
        case LEFT:
        while (currentTile.getX() != targetTile.getX()) {
        nextTile = getTile(currentTile.getX() - 1, currentTile.getY());
        if (nextTile.isHorizontalWallTile() || nextTile.isVerticalWallTile()) {
        return currentTile;
        }
        }
        break;
        case RIGHT:
        while (currentTile.getY() != targetTile.getY()) {
        nextTile = getTile(currentTile.getX() + 1, currentTile.getY());
        if (nextTile.isHorizontalWallTile() || nextTile.isVerticalWallTile()) {
        return currentTile;
        }
        }
        break;*/
