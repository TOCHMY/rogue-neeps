package util;

import player.Player;

public interface Killable {
    void takeDmg(Player by, double amount);

    void die();
    int getXP();
    double getHP();
}
