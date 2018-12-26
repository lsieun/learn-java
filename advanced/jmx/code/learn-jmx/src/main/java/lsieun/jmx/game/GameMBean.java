package lsieun.jmx.game;

public interface GameMBean {
    void playFootball(String clubName);

    String getPlayerName();

    void setPlayerName(String playerName);
}
