package org.mics.icedjava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Graph {
    int[] vertices;

    /**
     * This is a two dimensional array or matrix. It literature
     * it is also called an adjacency matrix i.e. which vertices
     * are adjacent (neighbors) to each other.
     * 
     * [0][0] would contain the distance from vertex 0 => 0. In this case it will be
     * of course 0.
     * [0][1] would contain the distance from vertex 0 => 1. This would be 4 based
     * on docs/graph.png
     * [3][4] would contain the distance from vertex 3 => 4. This would be 9 based
     * on docs/graph.png
     * etc.
     */
    int[][] edges;

    /**
     * See README for details on what a Queue is.
     */
    ArrayList<Integer> queue;

    /**
     * Initialze our graph
     * 
     * @param v How many vertices are in this graph
     */
    Graph(int v) {
        this.edges = new int[v][v];
        this.vertices = new int[v];

        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                if (i == j) {
                    /**
                     * The distance from any vertex to itself will always be 0
                     */
                    edges[i][j] = 0;
                } else {
                    /**
                     * We are initializing to a really large number so we can tell edges
                     * that aren't connected.
                     */
                    edges[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        queue = new ArrayList<Integer>();
    }

    /**
     * Add an edge to the graph. This method indicates
     * that this is an undirected graph because it is setting
     * the path between two vertices twice. Once for src =>
     * dst and once for dst => src.
     * 
     * @param src
     * @param dst
     * @param edgeWeight
     */
    public void addEdge(int src, int dst, int edgeWeight) {
        edges[src][dst] = edgeWeight;
        edges[dst][src] = edgeWeight;
        vertices[src] = src;
        vertices[dst] = dst;
    }

    /**
     * An implementation of Dijkstra's Algorithm which
     * will find the shortest path within a graph. It has
     * been modified to support finding the shortest path
     * between a source and a destination or target.
     * 
     * @param src
     * @param dst
     * @return An in order list of verticies with the shortest path between src and
     *         dst
     */
    public List<Integer> findShortestPath(int src, int dst) {
        /**
         * This is an array of integers that will store
         * the distance between src and every other
         * vertex within the graph.
         */
        int[] dist = new int[vertices.length];

        /**
         * This stores the the previous vertex
         * along the shortest path. You can think of it
         * as the path, but its going from end to start.
         */
        int[] previous = new int[vertices.length];

        for (int i = 0; i < vertices.length; i++) {
            dist[i] = Integer.MAX_VALUE;
            previous[i] = -1;
            queue.add(vertices[i]);
        }

        dist[src] = 0;

        /**
         * We need to visit every vertex in the graph
         * to build out our distance array and shortest
         * path in the previous array
         * 
         * We can loop until the queue is empty because when
         * we visit the next vertex in the graph we remove it
         * from the queue. So once we have visited all vertices
         * the queue will be empty.
         */
        while (!queue.isEmpty()) {
            /**
             * First we need to find the next vertex
             * in our queue that has the next smallest
             * distance
             */
            int minDistanceIndex = -1;
            int actualIndex = -1;
            for (int k = 0; k < queue.size(); k++) {
                int indx = queue.get(k);
                if (k == 0) {
                    minDistanceIndex = indx;
                    actualIndex = k;
                } else if (dist[indx] < dist[minDistanceIndex]) {
                    minDistanceIndex = indx;
                    actualIndex = k;
                }
            }

            /**
             * We have selected vertex actualIndex as the next
             * vertex in our shortest path
             */
            int u = queue.remove(actualIndex);

            /**
             * Visit all of u's neighbors and update our
             * distance array and previous array with the
             * next smallest distance.
             */
            for (int v = 0; v < edges[u].length; v++) {
                if (edges[u][v] != Integer.MAX_VALUE && queue.contains(v)) {
                    int alt = dist[u] + edges[u][v];
                    if (alt < dist[v]) {
                        dist[v] = alt;
                        previous[v] = u;
                    }
                }
            }
        }

        /**
         * At this point the previous array contains
         * the shortest path, but as we said above its in
         * reverse order - from end to start. This code
         * follows the vertices from end to start and
         * then returns the reverse so our test can
         * just have from start to end.
         */
        ArrayList<Integer> path = new ArrayList<Integer>();
        if (previous[dst] != -1 || dst == src) {
            int u = dst;
            while (u != -1) {
                path.add(u);
                u = previous[u];
            }

            Collections.reverse(path);
            return path;
        }

        return null;
    }
}
