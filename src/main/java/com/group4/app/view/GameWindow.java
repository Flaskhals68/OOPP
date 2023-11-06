package src.main.java.com.group4.app.view;

public class GameWindow {
    private static GameWindow instance = null;
    private GameWindow() {}

    public GameWindow getInstance() {
        if (instance == null) {
            return new GameWindow();
        }
        else {
            return GameWindow.instance;
        }
    }
}
