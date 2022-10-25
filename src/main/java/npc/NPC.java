package npc;

import map.Map;
import map.Tile;
import util.Direction;
import util.Movable;
import util.Position;

public abstract class NPC implements Movable {
    Position position;
    Direction facingDirection;
    Map map;
    public final String name;
    public int hp;

    public NPC(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }

    public void setMap(Map m){
        this.map = m;
        m.addNPC(this);
        //moveTo(RandomPos);
    }
    abstract boolean canMove(Tile tile);

    @Override
    public void move(Direction dir) {
        if(map == null){
            throw new IllegalStateException("Cannot move without a map");
        }
        Tile tile = map.getTile(dir);
        if(!tile.isOccupied()){
            move(tile);
        }
        setFacingDirection(dir);
    }

    @Override
    public void move(Tile tile) {
        if(map == null){
            throw new IllegalStateException("Cannot move without a map");
        }
        if(canMove(tile)){
            position = tile.getPosition();
        }
    }

    @Override
    public void moveTo(Position pos) {

    }
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Direction getFacingDirection() {
        return facingDirection;
    }

    public void setFacingDirection(Direction facingDirection) {
        this.facingDirection = facingDirection;
    }
    public String getName() {
        return name;
    }

}