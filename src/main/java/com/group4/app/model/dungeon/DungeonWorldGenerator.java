package com.group4.app.model.dungeon;

import com.group4.app.model.dungeon.DungeonGraphGenerator.Corridor;
import com.group4.app.model.dungeon.DungeonGraphGenerator.DungeonGraph;
import com.group4.app.model.dungeon.DungeonGraphGenerator.Room;

public class DungeonWorldGenerator {

    private static final int ROOM_SIZE = 6;
    private static final int CORRIDOR_SIZE = 2;


    /**
     * Generates a dungeon world of a given size and adds it to the specified container.
     *
     * @param size      the size of the dungeon world
     * @param container the container to add the dungeon world to
     * @return the generated dungeon world
     */
    public static World generate(int size, IWorldContainer container) {
        World world = new World(size*ROOM_SIZE);
        container.add(world);

        DungeonGraph graph = DungeonGraphGenerator.generate(size);

        for (Room room : graph.getRooms()) {
            int x = room.getX() * ROOM_SIZE;
            int y = room.getY() * ROOM_SIZE;

            addRoom(room, world, container);
        }

        for (Corridor corridor : graph.getCorridors()) {
            addCorridors(corridor.getRoom1(), world, container);
        }

        return world;
    }

    private static void addRoom(Room room, World world, ITileContainer tContainer) {
        addCenter(room, world, tContainer);
    }

    private static void addCenter(Room room, World world, ITileContainer tContainer) {
        int x = room.getX() * ROOM_SIZE;
        int y = room.getY() * ROOM_SIZE;
        double rand = Math.random();
        if (rand < 1) {
            addBox(x + ROOM_SIZE/2 - CORRIDOR_SIZE/2, y + ROOM_SIZE/2 - CORRIDOR_SIZE/2, CORRIDOR_SIZE, CORRIDOR_SIZE, world, tContainer);
        }
    }

    private static void addCorridors(Room room, World world, ITileContainer tContainer) {
        for (Corridor corridor : room.getCorridors()) {
            if (corridor.getRoom1() == room) {
                addCorridor(room, corridor, world, tContainer);
            }
        }
    }

    /**
     * Adds a corridor to the specified room in the world.
     * The corridor is added based on the direction of the corridor and the position of the room.
     * The size of the corridor is determined randomly.
     * 
     * @param room the room to add the corridor from
     * @param corridor the corridor to add
     * @param world the world to add the corridor to
     */
    private static void addCorridor(Room room, Corridor corridor, World world, ITileContainer tContainer) {
        int x = room.getX() * ROOM_SIZE;
        int y = room.getY() * ROOM_SIZE;

        try {
            double rand = Math.random();
            if (rand < 0.5) {
                if (corridor.getDirection() == DungeonGraphGenerator.Direction.NORTH) {
                    addBox(x + ROOM_SIZE/2 - CORRIDOR_SIZE/2, y - ROOM_SIZE/2 + CORRIDOR_SIZE/2, CORRIDOR_SIZE, ROOM_SIZE - CORRIDOR_SIZE, world, tContainer);
                } else if (corridor.getDirection() == DungeonGraphGenerator.Direction.SOUTH) {
                    addBox(x + ROOM_SIZE/2 - CORRIDOR_SIZE/2, y + ROOM_SIZE/2 + CORRIDOR_SIZE/2, CORRIDOR_SIZE, ROOM_SIZE - CORRIDOR_SIZE, world, tContainer);
                } else if (corridor.getDirection() == DungeonGraphGenerator.Direction.EAST) {
                    addBox(x + ROOM_SIZE/2 + CORRIDOR_SIZE/2, y + ROOM_SIZE/2 - CORRIDOR_SIZE/2, ROOM_SIZE - CORRIDOR_SIZE, CORRIDOR_SIZE, world, tContainer);
                } else if (corridor.getDirection() == DungeonGraphGenerator.Direction.WEST) {
                    addBox(x - ROOM_SIZE/2 + CORRIDOR_SIZE/2, y + ROOM_SIZE/2 - CORRIDOR_SIZE/2, ROOM_SIZE - CORRIDOR_SIZE, CORRIDOR_SIZE, world, tContainer);
                }
            } else if (rand < 0.75) {
                if (corridor.getDirection() == DungeonGraphGenerator.Direction.NORTH) {
                    addBox(x + 1, y - ROOM_SIZE/2 + CORRIDOR_SIZE/2, ROOM_SIZE - 1, ROOM_SIZE - CORRIDOR_SIZE, world, tContainer);
                } else if (corridor.getDirection() == DungeonGraphGenerator.Direction.SOUTH) {
                    addBox(x + 1, y + ROOM_SIZE/2 + CORRIDOR_SIZE/2, ROOM_SIZE - 1, ROOM_SIZE - CORRIDOR_SIZE, world, tContainer);
                } else if (corridor.getDirection() == DungeonGraphGenerator.Direction.EAST) {
                    addBox(x + ROOM_SIZE/2 + CORRIDOR_SIZE/2, y + 1, ROOM_SIZE - CORRIDOR_SIZE, ROOM_SIZE - 1, world, tContainer);
                } else if (corridor.getDirection() == DungeonGraphGenerator.Direction.WEST) {
                    addBox(x - ROOM_SIZE/2 + CORRIDOR_SIZE/2, y + 1, ROOM_SIZE - CORRIDOR_SIZE, ROOM_SIZE - 1, world, tContainer);
                }
            } else {
                if (corridor.getDirection() == DungeonGraphGenerator.Direction.NORTH) {
                    addBox(x, y - ROOM_SIZE/2 + CORRIDOR_SIZE/2, ROOM_SIZE, ROOM_SIZE - CORRIDOR_SIZE, world, tContainer);
                } else if (corridor.getDirection() == DungeonGraphGenerator.Direction.SOUTH) {
                    addBox(x, y + ROOM_SIZE/2 + CORRIDOR_SIZE/2, ROOM_SIZE, ROOM_SIZE - CORRIDOR_SIZE, world, tContainer);
                } else if (corridor.getDirection() == DungeonGraphGenerator.Direction.EAST) {
                    addBox(x + ROOM_SIZE/2 + CORRIDOR_SIZE/2, y, ROOM_SIZE - CORRIDOR_SIZE, ROOM_SIZE, world, tContainer);
                } else if (corridor.getDirection() == DungeonGraphGenerator.Direction.WEST) {
                    addBox(x - ROOM_SIZE/2 + CORRIDOR_SIZE/2, y, ROOM_SIZE - CORRIDOR_SIZE, ROOM_SIZE, world, tContainer);
                }
            }
        } catch (Exception e) {
            // System.out.println("Error adding corridor");
        }
    }

    /**
     * Adds a box of tiles to the world starting from the specified position (x, y) with the given width and height.
     * Each tile is added to the world using the provided World object.
     *
     * @param x      the starting x-coordinate of the box
     * @param y      the starting y-coordinate of the box
     * @param width  the width of the box
     * @param height the height of the box
     * @param world  the World object to add the tiles to
     */
    private static void addBox(int x, int y, int width, int height, World world, ITileContainer tContainer) {
        for (int i = x; i < x + width; i++) {
            for (int j = y; j < y + height ; j++) {
                Position pos = new Position(i, j, world.getId());
                try {
                    world.add(new Tile("stone", pos, tContainer));
                } catch (IllegalArgumentException e) {
                }
            }
        }
    }
}
