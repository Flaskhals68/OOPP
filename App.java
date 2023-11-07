import controller.HudController;
import controller.InventoryController;
import controller.WorldController;
import model.Model;
import view.GameWindow;
import view.HudView;
import view.InventoryView;
import view.WorldView;

public class App {
    public static void main(String[] args) {
        GameWindow gw = GameWindow.getInstance();
        Model model = new Model();

        WorldController worldController = new WorldController();
        WorldView worldView = new WorldView(model, worldController);

        InventoryController inventoryController = new InventoryController();
        InventoryView inventoryView = new InventoryView(model, inventoryController);
        
        HudController hudController = new HudController();
        HudView HudView = new HudView(model, hudController);
    }
}
