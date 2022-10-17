public class Room {
    private String name;
    private int width;
    private int height;
    private Tile startingTile;


    public Room(String name, int w, int h, Tile startingTile) {
        this.name = name;
        width = w;
        height = h;
        this.startingTile = startingTile;
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }


    public int getHeight() {
        return height;
    }

    public Tile getStartingTile() {
        return startingTile;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", startingTile=" + startingTile +
                '}';
    }
}
