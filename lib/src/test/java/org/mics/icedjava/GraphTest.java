package org.mics.icedjava;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class GraphTest {
    @Test
    void returnShortestPathIfSourceAndTargetAreDifferent() {
        /**
         * See README for a visualization of this sample graph
         */
        int numOfVertices = 9;
        Graph graph = new Graph(numOfVertices);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 7, 8);
        graph.addEdge(1, 7, 11);
        graph.addEdge(1, 2, 8);
        graph.addEdge(2, 8, 2);
        graph.addEdge(2, 3, 7);
        graph.addEdge(2, 5, 4);
        graph.addEdge(3, 5, 14);
        graph.addEdge(3, 4, 9);
        graph.addEdge(7, 6, 1);
        graph.addEdge(7, 8, 7);
        graph.addEdge(8, 6, 6);
        graph.addEdge(6, 5, 2);
        graph.addEdge(5, 4, 10);

        List<Integer> visitedNodes = graph.findShortestPath(0, 4);
        String path = visitedNodes.toString();

        /**
         * Confirm the path order is indeed the shortest path from
         * vertex 0 to vertex 4. The shortest path is a distance of
         * 21 as follows
         * 
         * 0 => 7 distance: 8
         * 7 => 6 distance: 1
         * 6 => 5 distance: 2
         * 4 => 4 distance: 10
         * 
         * total distance: 21
         */
        assertEquals("[0, 7, 6, 5, 4]", path, "findShortestPath should return '0,7,6,5,4'");
    }
}
