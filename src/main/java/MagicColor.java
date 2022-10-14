public enum MagicColor {

    BLUE("Attack"),GREEN("Defense"),RED("Intelligence"),PURPLE("Special");

    private final String type;

    MagicColor(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
