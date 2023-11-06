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
