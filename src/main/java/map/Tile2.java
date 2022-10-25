package map;

import npc.NPC;
import player.Player;
import util.Position;

public abstract class Tile2 {

    private Position position;
    private Player player;
    private NPC npc;

    Tile2(Position p){
        position = p;
    }


    public NPC getNpc() {
        return npc;
    }

    public void setNpc(NPC npc) {
        this.npc = npc;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isOccupied(){
        return npc != null | player != null;
    }

    public Player getPlayer(){
        return player;
    }



    public NPC getNPC(){
        return npc;
    }
    abstract String getSymbol();

    public Position getPosition(){
        return position;
    }
}
