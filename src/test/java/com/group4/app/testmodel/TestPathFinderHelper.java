// package com.group4.app.testmodel;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import java.util.List;
// import java.util.Set;
// import java.awt.Point;
// import java.awt.geom.Point2D;
// import java.util.ArrayList;
// import java.util.HashSet;
// import java.util.LinkedList;

// import org.junit.jupiter.api.Test;

// import com.group4.app.model.Position;
// import com.group4.app.model.Model;
// import com.group4.app.model.PathfindingHelper;
// import com.group4.app.model.Tile;
// import com.group4.app.model.World;

// public class TestPathFinderHelper {
//     // Only for debugging purposes
//     private static Set<Position> debugAddBasicMap(int startX, int startY) {
//         Set<Position> positions = new HashSet<>();
//         for (int x = -1; x < 2; x++) {
//             for (int y = -1; y < 2; y++) {
//                 positions.add(new Position(startX + x, startY + y));
//             }
//         }
//         return positions;
//     }

//     private static void addBasicMap(World world, int size){
//         for (int x = 0; x<size; x++) {
//             for (int y = 0; y<size; y++) {
//                 world.addTile(new Tile("stone", world.getId(), x, y));
//             }
//         }
//     }

//     @Test
//     public void testGetSurrounding() {
//         int size = 10;
//         int startX = 3;
//         int startY = 3;
//         Model model = Model.getInstance();
//         World world = new World(10);
//         model.addWorld(world);
//         addBasicMap(world, size);
//         Set<Position> correctPositions = debugAddBasicMap(startX, startY);
//         Tile startingTile = world.getTile(startX, startY);
//         Set<Position> legalPositions = PathfindingHelper.getSurrounding(startingTile, 1);
//         assertTrue(legalPositions.containsAll(legalPositions) && legalPositions.size() == correctPositions.size());
//     }

//     @Test
//     public void testGetShortestPath() {
//         Model model = Model.getInstance();
//         World world = new World(5);
//         model.addWorld(world);
//         int size = 5;
//         addBasicMap(world, size);
//         Tile start = world.getTile(0, 3);
//         Tile end = world.getTile(4, 3);
//         List<Position> path = PathfindingHelper.getShortestPath(start, end);

//         LinkedList<Position> correctPath = new LinkedList<>();
//         correctPath.addFirst(new Position(4, 3));
//         correctPath.addFirst(new Position(3, 3));
//         correctPath.addFirst(new Position(2, 3));
//         correctPath.addFirst(new Position(1, 3));

//         assertEquals(correctPath, path);
//     }

//     @Test
//     public void testGetPathNextTo() {
//         Model model = Model.getInstance();
//         World world = new World(5);
//         model.addWorld(world);
//         int size = 5;
//         addBasicMap(world, size);
//         Tile start = world.getTile(0, 3);
//         Tile end = world.getTile(4, 3);
//         List<Position> path = PathfindingHelper.getPathNextTo(start, end);
//         LinkedList<Position> correctPath = new LinkedList<>();
        
//         correctPath.addFirst(new Position(3, 3));
//         correctPath.addFirst(new Position(2, 3));
//         correctPath.addFirst(new Position(1, 3));
        
//         assertEquals(correctPath, path);
//     }
// }
