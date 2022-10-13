public class MagicSocket {

    private final MagicColor color;
    private GemStone gemStone;

    public MagicSocket(MagicColor color){
        this.color = color;
    }

    public void addStone(GemStone gemStone) {
        if (!gemStone.getColor().equals(this.color))
            throw new IllegalArgumentException("Gemstone must be of the same color as the socket");
        this.gemStone = gemStone;
    }

    public MagicColor getColor() {
        return color;
    }

    public GemStone getGemStone() {
        return this.gemStone;
    }
}
