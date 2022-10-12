public class MagicSocket {

    private final MagicColor color;
    private GemStone gemStone;


    public MagicSocket(MagicColor color){
        this.color = color;
    }

    public void addStone(GemStone gemStone) {
        this.gemStone = gemStone;
    }

    public MagicColor getColor() {
        return color;
    }

    public GemStone getGemStone() {
        return this.gemStone;
    }
}
