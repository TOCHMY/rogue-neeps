package npc;

import map.Map;
import map.Tile;
import util.Direction;
import util.Position;

public abstract class NPC {
    private Position position;
    private Direction facingDirection;

    private Map map;
    protected final String name;
    protected double hp;

    public NPC(String name, int hp) {
        this.name = name;
        this.hp = hp;
        this.facingDirection = Direction.UP;
    }

    public void setMap(Map m){
        this.map = m;
        m.addNPC(this);
        //moveTo(RandomPos);
    }
    public Map getMap() {
        return map;
    }
    abstract public boolean canMove(Tile tile);


    public void move(Direction dir) {
        if(map == null){
            throw new IllegalStateException("Cannot move without a map");
        }
        Tile current = map.getTile(position);
        Tile tile = map.getTile(dir, position);
        if(!tile.isOccupied()){
            move(tile, current);
        }
        setFacingDirection(dir);
    }


    private void move(Tile target, Tile current) {

        if(canMove(target)){
            position = target.getPosition();
            target.setOccupied(true);
            current.setOccupied(false);
        }
    }

    private void move(Tile target) {

        if(canMove(target)){
            position = target.getPosition();
            target.setOccupied(true);
        }
    }


    public void moveTo(Position pos) {
        if(map == null){
            throw new IllegalStateException("Cannot move without a map");
        }
        else if(position == null){
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
        if(position == null){
            throw new IllegalStateException("Player doesnt have a position");
        }
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Direction getFacingDirection() {
        return facingDirection;
    }

    private void setFacingDirection(Direction facingDirection) {
        this.facingDirection = facingDirection;
    }
    public String getName() {
        return name;
    }

}