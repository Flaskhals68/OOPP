package com.group4.app.model.dungeon;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import com.group4.app.model.PathfindingHelper;
import com.group4.app.model.Position;

public class DungeonGenerator {

    private static final int ROOM_SIZE = 6;
    private static final int HALLWAY_SIZE = 2;

    public static World generate(int size, IWorldContainer model) {
        World dungeon = new World(size * ROOM_SIZE);
        model.add(dungeon);

        Room[][] roomMatrix = generateStructures(dungeon);

        addRoomsToWorld(roomMatrix, dungeon);
                
        ensureWorldIsConnected(roomMatrix, dungeon);

        return dungeon;
    }

    private static void addRoomsToWorld(Room[][] roomMatrix, World dungeon){
        for (int x = 0; x < roomMatrix.length; x++) {
            for (int y = 0; y < roomMatrix[0].length; y++) {
                Room room = roomMatrix[x][y];
                if (room != null) {
                    room.addToWorld(dungeon);
                }
            }
        }
    }

    private static void ensureWorldIsConnected(Room[][] roomMatrix, World dungeon) {
        for (int x = 0; x < roomMatrix.length; x++) {
            for (int y = 0; y < roomMatrix[0].length; y++) {
                Room room = roomMatrix[x][y];
                if (room == null) {
                    continue;
                }
                while (true) {
                    try {
                        PathfindingHelper.getShortestPath(new Position(ROOM_SIZE/2, ROOM_SIZE/2, dungeon.getId()), new Position(x*ROOM_SIZE+ROOM_SIZE/2, y*ROOM_SIZE+ROOM_SIZE/2, dungeon.getId()), dungeon);
                        break;
                    } catch (IllegalArgumentException e) {
                        addRandomCorridor(room, roomMatrix, 1);
                        room.addToWorld(dungeon);
                        updateSurroundingRooms(room, roomMatrix, dungeon);
                    }
                }
                
            }
        }
    }

    private static void updateSurroundingRooms(Room room, Room[][] roomMatrix, World dungeon) {
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y ++) {
                if (!(x != 0 && y != 0) || !(Math.abs(x) == 1 && Math.abs(y) == 1)) {
                    if (room.x + x >= 0 && room.x + x < roomMatrix.length && room.y + y >= 0 && room.y + y < roomMatrix[0].length) {
                        roomMatrix[room.x + x][room.y + y].addToWorld(dungeon);
                    }
                }
            }
        }
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

    private static Room[][] generateStructures(World dungeon) {
        Room[][] roomMatrix = new Room[dungeon.getWorldWidth()/ROOM_SIZE][dungeon.getWorldHeight()/ROOM_SIZE];

        addRoomsToRoomMatrix(roomMatrix);

        addPartsToRoomsInRoomMatrix(roomMatrix);

        return roomMatrix;
    }

    private static void addRoomsToRoomMatrix(Room[][] roomMatrix) {
        for (int x = 0; x < roomMatrix.length; x++) {
            for (int y = 0; y < roomMatrix[0].length; y++) {
                roomMatrix[x][y] = new Room(x, y, new HashSet<>());
            }
        }
    }

    private static void addPartsToRoomsInRoomMatrix(Room[][] roomMatrix) {

        Queue<Room> rooms = new LinkedList<Room>();
        Set<Room> visited = new HashSet<>();

        rooms.add(new Room(0, 0, new HashSet<>() {{
            add(Direction.NORTH);
            add(Direction.SOUTH);
            add(Direction.EAST);
            add(Direction.WEST);
            add(Direction.NORTH_EAST);
            add(Direction.NORTH_WEST);
            add(Direction.SOUTH_EAST);
            add(Direction.SOUTH_WEST);
        }}));

        while (!rooms.isEmpty()) {
            Room room = rooms.poll();
            visited.add(room);
            roomMatrix[room.x][room.y] = room;

            for (int x = -1; x < 2; x++) {
                for (int y = -1; y < 2; y++) {
                    if (!(!(x != 0 && y != 0) || !(Math.abs(x) == 1 && Math.abs(y) == 1))) {
                        continue;
                    }
                    if (!(room.x + x >= 0 && room.x + x < roomMatrix.length && room.y + y >= 0 && room.y + y < roomMatrix[0].length)) {
                        continue;
                    }
                    if (!rooms.contains(roomMatrix[room.x + x][room.y + y]) && !visited.contains(roomMatrix[room.x + x][room.y + y])) {
                        rooms.add(roomMatrix[room.x + x][room.y + y]);
                    }
                    for (int i = 0; i < 1; i++) {
                        addRandomCorridor(room, roomMatrix, 0.1);
                        addRandomCorner(room, roomMatrix, 0.1);
                    }
                }
            }
        }
    }

    private static void addRandomCorridor(Room room, Room[][] roomMatrix, double chance) {
        Random rand = new Random();
        int r = rand.nextInt((int)(4/chance));
        switch (r) {
            case 0:
                room.addPart(Direction.NORTH);
                if (room.y - 1 >= 0) {
                    roomMatrix[room.x][room.y - 1].addPart(Direction.SOUTH);
                }
                break;
            case 1:
                room.addPart(Direction.SOUTH);
                if (room.y + 1 < roomMatrix[0].length) {
                    roomMatrix[room.x][room.y + 1].addPart(Direction.NORTH);
                }
                break;
            case 2:
                room.addPart(Direction.EAST);
                if (room.x + 1 < roomMatrix.length) {
                    roomMatrix[room.x + 1][room.y].addPart(Direction.WEST);
                }
                break;
            case 3:
                room.addPart(Direction.WEST);
                if (room.x - 1 >= 0) {
                    roomMatrix[room.x - 1][room.y].addPart(Direction.EAST);
                }
                break;
    }}
    
    private static void addRandomCorner(Room room, Room[][] roomMatrix, double chance) {
        Random rand = new Random();
        int r = rand.nextInt((int)(5/chance));
        switch (r) {
            case 0:
                room.addPart(Direction.NORTH_WEST);
                room.addPart(Direction.NORTH);
                room.addPart(Direction.NORTH_EAST);
                break;
            case 1:
                room.addPart(Direction.NORTH_EAST);
                room.addPart(Direction.EAST);
                room.addPart(Direction.SOUTH_EAST);
                break;
            case 2:
                room.addPart(Direction.SOUTH_EAST);
                room.addPart(Direction.SOUTH);
                room.addPart(Direction.SOUTH_WEST);
                break;
            case 3:
                room.addPart(Direction.SOUTH_WEST);
                room.addPart(Direction.WEST);
                room.addPart(Direction.NORTH_WEST);
                break;
            case 4:
                room.addPart(Direction.NORTH_WEST);
                room.addPart(Direction.NORTH);
                room.addPart(Direction.NORTH_EAST);
                room.addPart(Direction.EAST);
                room.addPart(Direction.SOUTH_EAST);
                room.addPart(Direction.SOUTH);
                room.addPart(Direction.SOUTH_WEST);
                room.addPart(Direction.WEST);
                break;
        }
    }

    private static class Room {
        private int x;
        private int y;

        private Set<Direction> parts;

        public Room(int x, int y, Set<Direction> parts) {
            this.x = x;
            this.y = y;
            this.parts = parts;
        }

        public void addPart(Direction part) {
            parts.add(part);
        }

        public void addToWorld(World dungeon) {
            try {
                addCenter(dungeon);
            } catch (Exception e) {

            }
            for (Direction part : parts) {
                try {
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
                } catch (IllegalArgumentException e) {
                    System.out.println("Error adding part " + part + " to world in room at " + x + ", " + y);
                }
            }
        }

        private void addCenter(World dungeon) {
            for (int i = 0; i < ROOM_SIZE; i++) {
                for (int j = 0; j < ROOM_SIZE; j++) {
                    if (x*ROOM_SIZE + i < dungeon.getWorldWidth() && y*ROOM_SIZE + j < dungeon.getWorldHeight()) {
                        if (i >= ROOM_SIZE / 2 - HALLWAY_SIZE / 2 && i < ROOM_SIZE / 2 + HALLWAY_SIZE / 2 && j >= ROOM_SIZE / 2 - HALLWAY_SIZE / 2 && j < ROOM_SIZE / 2 + HALLWAY_SIZE / 2) {
                            dungeon.add(new Tile("stone", new Position(x*ROOM_SIZE + i, y*ROOM_SIZE + j, dungeon.getId())));
                        }
                    }
                }
            }
        }

        private void addNorth(World dungeon) {
            for (int i = 0; i < ROOM_SIZE; i++) {
                for (int j = 0; j < ROOM_SIZE; j++) {
                    if (x*ROOM_SIZE + i < dungeon.getWorldWidth() && y*ROOM_SIZE + j < dungeon.getWorldHeight()) {
                        if (i >= ROOM_SIZE / 2 - HALLWAY_SIZE / 2 && i < ROOM_SIZE / 2 + HALLWAY_SIZE / 2 && j < ROOM_SIZE / 2 - HALLWAY_SIZE / 2) {
                            dungeon.add(new Tile("stone", new Position(x*ROOM_SIZE + i, y*ROOM_SIZE + j, dungeon.getId())));
                        }
                    }
                }
            }
        }

        private void addSouth(World dungeon) {
            for (int i = 0; i < ROOM_SIZE; i++) {
                for (int j = 0; j < ROOM_SIZE; j++) {
                    if (x*ROOM_SIZE + i < dungeon.getWorldWidth() && y*ROOM_SIZE + j < dungeon.getWorldHeight()) {
                        if (i >= ROOM_SIZE / 2 - HALLWAY_SIZE / 2 && i < ROOM_SIZE / 2 + HALLWAY_SIZE / 2 && j >= ROOM_SIZE / 2 + HALLWAY_SIZE / 2) {
                            dungeon.add(new Tile("stone", new Position(x*ROOM_SIZE + i, y*ROOM_SIZE + j, dungeon.getId())));
                        }
                    }
                }
            }
        }

        private void addEast(World dungeon) {
            for (int i = 0; i < ROOM_SIZE; i++) {
                for (int j = 0; j < ROOM_SIZE; j++) {
                    if (x*ROOM_SIZE + i < dungeon.getWorldWidth() && y*ROOM_SIZE + j < dungeon.getWorldHeight()) {
                        if (j >= ROOM_SIZE / 2 - HALLWAY_SIZE / 2 && j < ROOM_SIZE / 2 + HALLWAY_SIZE / 2 && i >= ROOM_SIZE / 2 + HALLWAY_SIZE / 2) {
                            dungeon.add(new Tile("stone", new Position(x*ROOM_SIZE + i, y*ROOM_SIZE + j, dungeon.getId())));
                        }
                    }
                }
            }
        }

        private void addWest(World dungeon) {
            for (int i = 0; i < ROOM_SIZE; i++) {
                for (int j = 0; j < ROOM_SIZE; j++) {
                    if (x*ROOM_SIZE + i < dungeon.getWorldWidth() && y*ROOM_SIZE + j < dungeon.getWorldHeight()) {
                        if (j >= ROOM_SIZE / 2 - HALLWAY_SIZE / 2 && j < ROOM_SIZE / 2 + HALLWAY_SIZE / 2 && i < ROOM_SIZE / 2 - HALLWAY_SIZE / 2) {
                            dungeon.add(new Tile("stone", new Position(x*ROOM_SIZE + i, y*ROOM_SIZE + j, dungeon.getId())));
                        }
                    }
                }
            }
        }

        private void addNorthEast(World dungeon) {
            for (int i = 0; i < ROOM_SIZE; i++) {
                for (int j = 0; j < ROOM_SIZE; j++) {
                    if (x*ROOM_SIZE + i < dungeon.getWorldWidth() && y*ROOM_SIZE + j < dungeon.getWorldHeight()) {
                        if (((i >= ROOM_SIZE / 2 + HALLWAY_SIZE / 2) && (j < ROOM_SIZE / 2 - HALLWAY_SIZE / 2))) {
                            dungeon.add(new Tile("stone", new Position(x*ROOM_SIZE + i, y*ROOM_SIZE + j, dungeon.getId())));
                        }
                    }
                }
            }
        }

        private void addSouthEast(World dungeon) {
            for (int i = 0; i < ROOM_SIZE; i++) {
                for (int j = 0; j < ROOM_SIZE; j++) {
                    if (x*ROOM_SIZE + i < dungeon.getWorldWidth() && y*ROOM_SIZE + j < dungeon.getWorldHeight()) {
                        if (((i >= ROOM_SIZE / 2 + HALLWAY_SIZE / 2) && (j >= ROOM_SIZE / 2 + HALLWAY_SIZE / 2))) {
                            dungeon.add(new Tile("stone", new Position(x*ROOM_SIZE + i, y*ROOM_SIZE + j, dungeon.getId())));
                        }
                    }
                }
            }
        }

        private void addSouthWest(World dungeon) {
            for (int i = 0; i < ROOM_SIZE; i++) {
                for (int j = 0; j < ROOM_SIZE; j++) {
                    if (x*ROOM_SIZE + i < dungeon.getWorldWidth() && y*ROOM_SIZE + j < dungeon.getWorldHeight()) {
                        if (((i < ROOM_SIZE / 2 - HALLWAY_SIZE / 2) && (j >= ROOM_SIZE / 2 + HALLWAY_SIZE / 2))) {
                            dungeon.add(new Tile("stone", new Position(x*ROOM_SIZE + i, y*ROOM_SIZE + j, dungeon.getId())));
                        }
                    }
                }
            }
        }

        private void addNorthWest(World dungeon) {
            for (int i = 0; i < ROOM_SIZE; i++) {
                for (int j = 0; j < ROOM_SIZE; j++) { 
                    if (x*ROOM_SIZE + i < dungeon.getWorldWidth() && y*ROOM_SIZE + j < dungeon.getWorldHeight()) {
                        if (((i < ROOM_SIZE / 2 - HALLWAY_SIZE / 2) && (j < ROOM_SIZE / 2 - HALLWAY_SIZE / 2))) {
                            dungeon.add(new Tile("stone", new Position(x*ROOM_SIZE + i, y*ROOM_SIZE + j, dungeon.getId())));
                        }
                    }
                }
            }
        }
    }
}