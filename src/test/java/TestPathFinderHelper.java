import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import com.group4.app.model.Model;
import com.group4.app.model.PathfindingHelper;
import com.group4.app.model.Tile;
import com.group4.app.model.World;

public class TestPathFinderHelper {
    // Only for debugging purposes
    private static Set<Point2D> debugAddBasicMap(int startX, int startY) {
        Set<Point2D> positions = new HashSet<>();
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                positions.add(new Point(startX + x, startY + y));
            }
        }
        return positions;
    }

    private static void addBasicMap(World world, int size){
        for (int x = 0; x<size; x++) {
            for (int y = 0; y<size; y++) {
                world.addTile(new Tile(world, x, y));
            }
        }
    }

    @Test
    public void testGetSurrounding() {
        int startX = 3;
        int startY = 3;
        Model model = Model.getInstance();
        World world = new World(100);
        addBasicMap(world, 10);
        Set<Point2D> testSet = debugAddBasicMap(startX, startY);
        Tile startingTile = world.getTile(startX, startY);
        Set<Point2D> legalPositions = PathfindingHelper.getSurrounding(startingTile, 1);
        assertTrue(testSet.equals(legalPositions));
    }   
}
