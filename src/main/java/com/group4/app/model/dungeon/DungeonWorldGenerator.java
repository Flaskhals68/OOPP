package com.group4.app.model.dungeon;

import com.group4.app.model.Position;
import com.group4.app.model.dungeon.DungeonGraphGenerator.Corridor;
import com.group4.app.model.dungeon.DungeonGraphGenerator.DungeonGraph;
import com.group4.app.model.dungeon.DungeonGraphGenerator.Room;

public class DungeonWorldGenerator {

    private static final int ROOM_SIZE = 6;
    private static final int CORRIDOR_SIZE = 2;

    public static World generate(int size, IWorldContainer container) {
        World world = new World(size*ROOM_SIZE);
        container.add(world);

        DungeonGraph graph = DungeonGraphGenerator.generate(size);

        for (Room room : graph.getRooms()) {
            int x = room.getX() * ROOM_SIZE;
            int y = room.getY() * ROOM_SIZE;

            addRoom(room, world);
        }

        for (Corridor corridor : graph.getCorridors()) {
            addCorridors(corridor.getRoom1(), world);
        }

        return world;
    }

    private static void addRoom(Room room, World world) {
        addCenter(room, world);
    }

    private static void addCenter(Room room, World world) {
        int x = room.getX() * ROOM_SIZE;
        int y = room.getY() * ROOM_SIZE;
        double rand = Math.random();
        if (rand < 1) {
            addBox(x + ROOM_SIZE/2 - CORRIDOR_SIZE/2, y + ROOM_SIZE/2 - CORRIDOR_SIZE/2, CORRIDOR_SIZE, CORRIDOR_SIZE, world);
        }
    }

    private static void addCorridors(Room room, World world) {
        for (Corridor corridor : room.getCorridors()) {
            if (corridor.getRoom1() == room) {
                addCorridor(room, corridor, world);
            }
        }
    }

    private static void addCorridor(Room room, Corridor corridor, World world) {
        int x = room.getX() * ROOM_SIZE;
        int y = room.getY() * ROOM_SIZE;

        try {
            double rand = Math.random();
            if (rand < 0.5) {
                if (corridor.getDirection() == DungeonGraphGenerator.Direction.NORTH) {
                    addBox(x + ROOM_SIZE/2 - CORRIDOR_SIZE/2, y - ROOM_SIZE/2 + CORRIDOR_SIZE/2, CORRIDOR_SIZE, ROOM_SIZE - CORRIDOR_SIZE, world);
                } else if (corridor.getDirection() == DungeonGraphGenerator.Direction.SOUTH) {
                    addBox(x + ROOM_SIZE/2 - CORRIDOR_SIZE/2, y + ROOM_SIZE/2 + CORRIDOR_SIZE/2, CORRIDOR_SIZE, ROOM_SIZE - CORRIDOR_SIZE, world);
                } else if (corridor.getDirection() == DungeonGraphGenerator.Direction.EAST) {
                    addBox(x + ROOM_SIZE/2 + CORRIDOR_SIZE/2, y + ROOM_SIZE/2 - CORRIDOR_SIZE/2, ROOM_SIZE - CORRIDOR_SIZE, CORRIDOR_SIZE, world);
                } else if (corridor.getDirection() == DungeonGraphGenerator.Direction.WEST) {
                    addBox(x - ROOM_SIZE/2 + CORRIDOR_SIZE/2, y + ROOM_SIZE/2 - CORRIDOR_SIZE/2, ROOM_SIZE - CORRIDOR_SIZE, CORRIDOR_SIZE, world);
                }
            } else if (rand < 0.75) {
                if (corridor.getDirection() == DungeonGraphGenerator.Direction.NORTH) {
                    addBox(x + 1, y - ROOM_SIZE/2 + CORRIDOR_SIZE/2, ROOM_SIZE - 1, ROOM_SIZE - CORRIDOR_SIZE, world);
                } else if (corridor.getDirection() == DungeonGraphGenerator.Direction.SOUTH) {
                    addBox(x + 1, y + ROOM_SIZE/2 + CORRIDOR_SIZE/2, ROOM_SIZE - 1, ROOM_SIZE - CORRIDOR_SIZE, world);
                } else if (corridor.getDirection() == DungeonGraphGenerator.Direction.EAST) {
                    addBox(x + ROOM_SIZE/2 + CORRIDOR_SIZE/2, y + 1, ROOM_SIZE - CORRIDOR_SIZE, ROOM_SIZE - 1, world);
                } else if (corridor.getDirection() == DungeonGraphGenerator.Direction.WEST) {
                    addBox(x - ROOM_SIZE/2 + CORRIDOR_SIZE/2, y + 1, ROOM_SIZE - CORRIDOR_SIZE, ROOM_SIZE - 1, world);
                }
            } else {
                if (corridor.getDirection() == DungeonGraphGenerator.Direction.NORTH) {
                    addBox(x, y - ROOM_SIZE/2 + CORRIDOR_SIZE/2, ROOM_SIZE, ROOM_SIZE - CORRIDOR_SIZE, world);
                } else if (corridor.getDirection() == DungeonGraphGenerator.Direction.SOUTH) {
                    addBox(x, y + ROOM_SIZE/2 + CORRIDOR_SIZE/2, ROOM_SIZE, ROOM_SIZE - CORRIDOR_SIZE, world);
                } else if (corridor.getDirection() == DungeonGraphGenerator.Direction.EAST) {
                    addBox(x + ROOM_SIZE/2 + CORRIDOR_SIZE/2, y, ROOM_SIZE - CORRIDOR_SIZE, ROOM_SIZE, world);
                } else if (corridor.getDirection() == DungeonGraphGenerator.Direction.WEST) {
                    addBox(x - ROOM_SIZE/2 + CORRIDOR_SIZE/2, y, ROOM_SIZE - CORRIDOR_SIZE, ROOM_SIZE, world);
                }
            }
        } catch (Exception e) {
            System.out.println("Error adding corridor");
        }
    }

    private static void addBox(int x, int y, int width, int height, World world) {
        for (int i = x; i < x + width; i++) {
            for (int j = y; j < y + height ; j++) {
                Position pos = new Position(i, j, world.getId());
                try {
                    world.add(new Tile("stone", pos));
                } catch (IllegalArgumentException e) {
                    System.out.println("Error adding corridor");
                }
            }
        }
    }
}