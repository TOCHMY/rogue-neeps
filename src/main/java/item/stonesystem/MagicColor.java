package item.stonesystem;

public enum MagicColor {

    BLUE("Attack"),GREEN("Intelligence"),RED("Defence"),PURPLE("Special");

    private final String type;

    MagicColor(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
