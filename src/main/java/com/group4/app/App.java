package src.main.java.com.group4.app;
import src.main.java.com.group4.app.controller.HudController;
import src.main.java.com.group4.app.controller.InventoryController;
import src.main.java.com.group4.app.controller.WorldController;
import src.main.java.com.group4.app.model.Model;
import src.main.java.com.group4.app.view.HudView;
import src.main.java.com.group4.app.view.InventoryView;
import src.main.java.com.group4.app.view.WorldView;

public class App {
    public static void main(String[] args) {
        Model model = new Model();

        WorldController worldController = new WorldController();
        WorldView worldView = new WorldView(model, worldController);

        InventoryController inventoryController = new InventoryController();
        InventoryView inventoryView = new InventoryView(model, inventoryController);
        
        HudController hudController = new HudController();
        HudView HudView = new HudView(model, hudController);
    }
}
