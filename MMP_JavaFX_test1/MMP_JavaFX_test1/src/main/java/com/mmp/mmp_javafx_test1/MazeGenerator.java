package com.mmp.mmp_javafx_test1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class MazeGenerator {
    private final int width, height;
    private final boolean[][] visited; // Tracks visited cells during generation
    private final Graph graph = new Graph(); // Stores maze structure

    public MazeGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        this.visited = new boolean[height][width]; // Initialize visitation grid
    }

    // Generates a maze using recursive backtracking algorithm
    public Graph generate() {
        Random rand = new Random();
        Stack<Graph.Node> stack = new Stack<>();
        Graph.Node start = new Graph.Node(0, 0);
        visited[0][0] = true;
        stack.push(start);

        while (!stack.isEmpty()) {
            Graph.Node current = stack.pop(); // Backtracking step
            List<Graph.Node> neighbors = getUnvisitedNeighbors(current);

            if (!neighbors.isEmpty()) {
                stack.push(current); // Push current back to backtrack later
                Graph.Node next = neighbors.get(rand.nextInt(neighbors.size())); // Random neighbor
                graph.addEdge(current, next); // Create path between nodes
                visited[next.y][next.x] = true; // Mark as visited
                stack.push(next); // Continue exploring from new node
            }
        }
        return graph;
    }

    // Finds unvisited neighbors in all 4 directions (up, down, left, right)
    private List<Graph.Node> getUnvisitedNeighbors(Graph.Node node) {
        List<Graph.Node> neighbors = new ArrayList<>();
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // Right, Down, Left, Up

        for (int[] dir : directions) {
            int nx = node.x + dir[0];
            int ny = node.y + dir[1];

            if (nx >= 0 && ny >= 0 && nx < width && ny < height && !visited[ny][nx]) {
                neighbors.add(new Graph.Node(nx, ny));
            }
        }
        return neighbors;
    }
}
