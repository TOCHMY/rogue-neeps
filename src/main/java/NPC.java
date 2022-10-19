import java.util.Random;
abstract class NPC implements Movement {

    private Map map;
    private Direction npcFacingDirection;

    public NPC() {
    }

    //IMplement movement
    @Override
    public void moveUp() {
        setNPCFacingDirection(Direction.UP);
    }

    @Override
    public void moveDown() {
        setNPCFacingDirection(Direction.DOWN);
    }

    @Override
    public void moveRight() {
        setNPCFacingDirection(Direction.RIGHT);
    }

    @Override
    public void moveLeft() {
        setNPCFacingDirection(Direction.LEFT);
    }

    private void setNPCFacingDirection(Direction direction) {
        npcFacingDirection = direction;
    }

}