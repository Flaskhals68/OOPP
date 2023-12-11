package com.group4.app.testmodel;

import org.junit.jupiter.api.Test;

import com.group4.app.model.dungeon.DungeonGraphGenerator;
import com.group4.app.model.dungeon.DungeonGraphGenerator.Corridor;
import com.group4.app.model.dungeon.DungeonGraphGenerator.DungeonGraph;
import com.group4.app.model.dungeon.DungeonGraphGenerator.Room;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDungeonGraphGenerator {

    @Test
    public void testGenerate() {
        DungeonGraph graph = DungeonGraphGenerator.generate(10);
    }

    @Test
    public void testAddCorridor() {
        DungeonGraph graph = new DungeonGraph(10);
        Room room = new Room(0, 0);
        Room room2 = new Room(0, 1);
        graph.addRoom(room);
        graph.addRoom(room2);
        Corridor corridor = new Corridor(room, room2);
        graph.addCorridor(corridor);
        corridor = new Corridor(room2, room);
        graph.addCorridor(corridor);
        
        assertEquals(1, room.getCorridors().size());
        assertEquals(1, room2.getCorridors().size());
        assertEquals(1, graph.getCorridors().size());
    }
}
