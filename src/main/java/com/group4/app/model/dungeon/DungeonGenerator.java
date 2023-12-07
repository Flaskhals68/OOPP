package com.group4.app.model.dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.group4.app.model.Model;
import com.group4.app.model.Position;

public class DungeonGenerator {

    private static final int ROOM_SIZE = 8;
    private static final int HALLWAY_SIZE = 2;

    public static World generate(int size, IWorldContainer model) {
        World dungeon = new World(size * 8);
        model.add(dungeon);

        int[][] structureMatrix = generateStructureMatrix(dungeon);

        generateRooms(dungeon, structureMatrix);

        return dungeon;
    }

    private enum Direction {
        NORTH,
        SOUTH,
        EAST,
        WEST,
        NORTH_EAST,
        NORTH_WEST,
        SOUTH_EAST,
        SOUTH_WEST
    }

    private static int[][] generateStructureMatrix(World dungeon) {
        int[][] structureMatrix = new int[dungeon.getWorldWidth()/8][dungeon.getWorldHeight()/8];

        for (int x = 0; x < structureMatrix.length; x++) {
            for (int y = 0; y < structureMatrix[0].length; y++) {
                Random rand = new Random();
                int r = rand.nextInt(1);
                structureMatrix[x][y] = r;
            }
        }

        return structureMatrix;
    }

    private static void generateRooms(World dungeon, int[][] structureMatrix) {
        ArrayList<Room> rooms = new ArrayList<DungeonGenerator.Room>() {
  
        };
        for (int x = 0; x < structureMatrix.length; x++) {
            for (int y = 0; y < structureMatrix[0].length; y++) {
                switch (structureMatrix[x][y]) {
                    case 0:
                        rooms.add(generateRoom(x*ROOM_SIZE, y*ROOM_SIZE, new ArrayList<>() {{
                            add(Direction.NORTH);
                            add(Direction.SOUTH);
                            add(Direction.EAST);
                            add(Direction.WEST);
                            add(Direction.NORTH_EAST);
                            add(Direction.NORTH_WEST);
                            add(Direction.SOUTH_EAST);
                            add(Direction.SOUTH_WEST);
                        }}));
                        break;
                }
            }
        }
        for (Room room : rooms) {
            room.addToWorld(dungeon);
        }
    }

    private static void addRoom(World dungeon, int x, int y) {
        for (int i = 0; i < ROOM_SIZE; i++) {
            for (int j = 0; j < ROOM_SIZE; j++) {
                if (x + i < dungeon.getWorldWidth() && y + j < dungeon.getWorldHeight()) {
                    dungeon.add(new Tile("stone", new Position(x + i, y + j, dungeon.getId())));
                }
            }
        }
    }

    private static void add4WayIntersection(World dungeon, int x, int y) {
        for (int i = 0; i < ROOM_SIZE; i++) {
            for (int j = 0; j < ROOM_SIZE; j++) {
                if (x + i < dungeon.getWorldWidth() && y + j < dungeon.getWorldHeight()) {
                    if (((i >= ROOM_SIZE / 2 - HALLWAY_SIZE / 2) && (i < ROOM_SIZE / 2 + HALLWAY_SIZE / 2)) || ((j >= ROOM_SIZE / 2 - HALLWAY_SIZE / 2) && (j < ROOM_SIZE / 2 + HALLWAY_SIZE / 2))) {
                        dungeon.add(new Tile("stone", new Position(x + i, y + j, dungeon.getId())));
                    }
                }
            }
        }
    }

    private static void addCorridor(World dungeon, int x, int y, Direction direction) {
        for (int i = 0; i < ROOM_SIZE; i++) {
            for (int j = 0; j < ROOM_SIZE; j++) {
                if (x + i < dungeon.getWorldWidth() && y + j < dungeon.getWorldHeight()) {
                    if (direction == Direction.WEST || direction == Direction.EAST) {
                        if (j >= ROOM_SIZE / 2 - HALLWAY_SIZE / 2 && j < ROOM_SIZE / 2 + HALLWAY_SIZE / 2) {
                            dungeon.add(new Tile("stone", new Position(x + i, y + j, dungeon.getId())));
                        }
                    } else {
                        if (i >= ROOM_SIZE / 2 - HALLWAY_SIZE / 2 && i < ROOM_SIZE / 2 + HALLWAY_SIZE / 2) {
                            dungeon.add(new Tile("stone", new Position(x + i, y + j, dungeon.getId())));
                        }
                    }
                }
            }
        }
    }

    private static void addLeftTurn(World dungeon, int x, int y, Direction direction) {
        for (int i = 0; i < ROOM_SIZE; i++) {
            for (int j = 0; j < ROOM_SIZE; j++) {
                if (x + i < dungeon.getWorldWidth() && y + j < dungeon.getWorldHeight()) {
                    if ((((i >= ROOM_SIZE / 2 - HALLWAY_SIZE / 2) && (i < ROOM_SIZE / 2 + HALLWAY_SIZE / 2)) && (j < ROOM_SIZE / 2 + HALLWAY_SIZE / 2)) || ((j >= ROOM_SIZE / 2 - HALLWAY_SIZE / 2) && (j < ROOM_SIZE / 2 + HALLWAY_SIZE / 2) && (i >= ROOM_SIZE / 2 - HALLWAY_SIZE / 2))) {
                        dungeon.add(new Tile("stone", new Position(x + i, y + j, dungeon.getId())));
                    }
                }
            }
        }
    }

    private static void addRightTurn(World dungeon, int x, int y, Direction direction) {
        for (int i = 0; i < ROOM_SIZE; i++) {
            for (int j = 0; j < ROOM_SIZE; j++) {
                if (x + i < dungeon.getWorldWidth() && y + j < dungeon.getWorldHeight()) {
                    if ((((i >= ROOM_SIZE / 2 - HALLWAY_SIZE / 2) && (i < ROOM_SIZE / 2 + HALLWAY_SIZE / 2)) && (j < ROOM_SIZE / 2 + HALLWAY_SIZE / 2)) || ((j >= ROOM_SIZE / 2 - HALLWAY_SIZE / 2) && (j < ROOM_SIZE / 2 + HALLWAY_SIZE / 2) && (i < ROOM_SIZE / 2 + HALLWAY_SIZE / 2))) {
                        dungeon.add(new Tile("stone", new Position(x + i, y + j, dungeon.getId())));
                    }
                }
            }
        }
    }

    private static class Room {
        private int x;
        private int y;

        private List<Direction> parts;

        public Room(int x, int y, List<Direction> parts) {
            this.x = x;
            this.y = y;
            this.parts = parts;
        }

        public void addToWorld(World dungeon) {
            addCenter(dungeon);
            for (Direction part : parts) {
                switch (part) {
                    case NORTH:
                        addNorth(dungeon);
                        break;
                    case SOUTH:
                        addSouth(dungeon);
                        break;
                    case EAST:
                        addEast(dungeon);
                        break;
                    case WEST:
                        addWest(dungeon);
                        break;
                    case NORTH_EAST:
                        addNorthEast(dungeon);
                        break;
                    case NORTH_WEST:
                        addNorthWest(dungeon);
                        break;
                    case SOUTH_EAST:
                        addSouthEast(dungeon);
                        break;
                    case SOUTH_WEST:
                        addSouthWest(dungeon);
                        break;
                }
            }
        }

        private void addCenter(World dungeon) {
            for (int i = 0; i < ROOM_SIZE; i++) {
                for (int j = 0; j < ROOM_SIZE; j++) {
                    if (x + i < dungeon.getWorldWidth() && y + j < dungeon.getWorldHeight()) {
                        if (i >= ROOM_SIZE / 2 - HALLWAY_SIZE / 2 && i < ROOM_SIZE / 2 + HALLWAY_SIZE / 2 && j >= ROOM_SIZE / 2 - HALLWAY_SIZE / 2 && j < ROOM_SIZE / 2 + HALLWAY_SIZE / 2) {
                            dungeon.add(new Tile("stone", new Position(x + i, y + j, dungeon.getId())));
                        }
                    }
                }
            }
        }

        private void addNorth(World dungeon) {
            for (int i = 0; i < ROOM_SIZE; i++) {
                for (int j = 0; j < ROOM_SIZE; j++) {
                    if (x + i < dungeon.getWorldWidth() && y + j < dungeon.getWorldHeight()) {
                        if (i >= ROOM_SIZE / 2 - HALLWAY_SIZE / 2 && i < ROOM_SIZE / 2 + HALLWAY_SIZE / 2 && j < ROOM_SIZE / 2 - HALLWAY_SIZE / 2) {
                            dungeon.add(new Tile("stone", new Position(x + i, y + j, dungeon.getId())));
                        }
                    }
                }
            }
        }

        private void addSouth(World dungeon) {
            for (int i = 0; i < ROOM_SIZE; i++) {
                for (int j = 0; j < ROOM_SIZE; j++) {
                    if (x + i < dungeon.getWorldWidth() && y + j < dungeon.getWorldHeight()) {
                        if (i >= ROOM_SIZE / 2 - HALLWAY_SIZE / 2 && i < ROOM_SIZE / 2 + HALLWAY_SIZE / 2 && j >= ROOM_SIZE / 2 + HALLWAY_SIZE / 2) {
                            dungeon.add(new Tile("stone", new Position(x + i, y + j, dungeon.getId())));
                        }
                    }
                }
            }
        }

        private void addEast(World dungeon) {
            for (int i = 0; i < ROOM_SIZE; i++) {
                for (int j = 0; j < ROOM_SIZE; j++) {
                    if (x + i < dungeon.getWorldWidth() && y + j < dungeon.getWorldHeight()) {
                        if (j >= ROOM_SIZE / 2 - HALLWAY_SIZE / 2 && j < ROOM_SIZE / 2 + HALLWAY_SIZE / 2 && i >= ROOM_SIZE / 2 + HALLWAY_SIZE / 2) {
                            dungeon.add(new Tile("stone", new Position(x + i, y + j, dungeon.getId())));
                        }
                    }
                }
            }
        }

        private void addWest(World dungeon) {
            for (int i = 0; i < ROOM_SIZE; i++) {
                for (int j = 0; j < ROOM_SIZE; j++) {
                    if (x + i < dungeon.getWorldWidth() && y + j < dungeon.getWorldHeight()) {
                        if (j >= ROOM_SIZE / 2 - HALLWAY_SIZE / 2 && j < ROOM_SIZE / 2 + HALLWAY_SIZE / 2 && i < ROOM_SIZE / 2 - HALLWAY_SIZE / 2) {
                            dungeon.add(new Tile("stone", new Position(x + i, y + j, dungeon.getId())));
                        }
                    }
                }
            }
        }

        private void addNorthEast(World dungeon) {
            for (int i = 0; i < ROOM_SIZE; i++) {
                for (int j = 0; j < ROOM_SIZE; j++) {
                    if (x + i < dungeon.getWorldWidth() && y + j < dungeon.getWorldHeight()) {
                        if (((i >= ROOM_SIZE / 2 + HALLWAY_SIZE / 2) && (j < ROOM_SIZE / 2 - HALLWAY_SIZE / 2))) {
                            dungeon.add(new Tile("stone", new Position(x + i, y + j, dungeon.getId())));
                        }
                    }
                }
            }
        }

        private void addSouthEast(World dungeon) {
            for (int i = 0; i < ROOM_SIZE; i++) {
                for (int j = 0; j < ROOM_SIZE; j++) {
                    if (x + i < dungeon.getWorldWidth() && y + j < dungeon.getWorldHeight()) {
                        if (((i >= ROOM_SIZE / 2 + HALLWAY_SIZE / 2) && (j >= ROOM_SIZE / 2 + HALLWAY_SIZE / 2))) {
                            dungeon.add(new Tile("stone", new Position(x + i, y + j, dungeon.getId())));
                        }
                    }
                }
            }
        }

        private void addSouthWest(World dungeon) {
            for (int i = 0; i < ROOM_SIZE; i++) {
                for (int j = 0; j < ROOM_SIZE; j++) {
                    if (x + i < dungeon.getWorldWidth() && y + j < dungeon.getWorldHeight()) {
                        if (((i < ROOM_SIZE / 2 - HALLWAY_SIZE / 2) && (j >= ROOM_SIZE / 2 + HALLWAY_SIZE / 2))) {
                            dungeon.add(new Tile("stone", new Position(x + i, y + j, dungeon.getId())));
                        }
                    }
                }
            }
        }

        private void addNorthWest(World dungeon) {
            for (int i = 0; i < ROOM_SIZE; i++) {
                for (int j = 0; j < ROOM_SIZE; j++) { 
                    if (x + i < dungeon.getWorldWidth() && y + j < dungeon.getWorldHeight()) {
                        if (((i < ROOM_SIZE / 2 - HALLWAY_SIZE / 2) && (j < ROOM_SIZE / 2 - HALLWAY_SIZE / 2))) {
                            dungeon.add(new Tile("stone", new Position(x + i, y + j, dungeon.getId())));
                        }
                    }
                }
            }
        }
    }

    private static Room generateRoom(int x, int y, List<Direction> directions) {
        List<Direction> parts = new ArrayList<Direction>();
        for (Direction direction : directions) {
                    parts.add(direction);
        }
        return new Room(x, y, parts);
    }
}