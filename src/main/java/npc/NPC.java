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
    public double hp;

    public NPC(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }

    public void setMap(Map m){
        this.map = m;
        m.addNPC(this);
        //moveTo(RandomPos);
    }
    abstract public boolean canMove(Tile tile);

    @Override
    public void move(Direction dir) {
        if(map == null){
            throw new IllegalStateException("Cannot move without a map");
        }
        Tile current = map.getTile(position);
        Tile tile = map.getTile(dir);
        if(!tile.isOccupied()){
            move(tile, current);
        }
        setFacingDirection(dir);
    }

    @Override
    public void move(Tile target, Tile current) {
        if(map == null){
            throw new IllegalStateException("Cannot move without a map");
        }
        if(canMove(target)){
            position = target.getPosition();
            target.setOccupied(true);
            current.setOccupied(false);
        }
    }
    @Override
    public void move(Tile target) {
        if(map == null){
            throw new IllegalStateException("Cannot move without a map");
        }
        if(canMove(target)){
            position = target.getPosition();
            target.setOccupied(true);
        }
    }

    @Override
    public void moveTo(Position pos) {
        if(position == null){
            Tile target = map.getTile(pos);
            move(target);
        }
        else{
            Tile current = map.getTile(position);
            Tile target = map.getTile(pos);
            move(target, current);
        }

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