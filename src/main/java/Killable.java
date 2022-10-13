public interface Killable {
    void takeDmg(Player by, int amount);

    void die();
    int getXP();
    int getHP();
}
