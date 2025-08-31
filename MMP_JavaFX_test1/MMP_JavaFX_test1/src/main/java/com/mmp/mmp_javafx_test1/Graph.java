package com.mmp.mmp_javafx_test1;

import java.util.*;

public class Graph {

    //Need to be able to explain
    final Map<Node, List<Node>> adjacencyList = new HashMap<>();

    // Adds a bidirectional edge between two nodes (maze paths are undirected)



    //The use of Lambda functions in my code was inspired by
    public void addEdge(Node node1, Node node2) {
        adjacencyList.computeIfAbsent(node1, k -> new ArrayList<>()).add(node2);
        adjacencyList.computeIfAbsent(node2, k -> new ArrayList<>()).add(node1);
        // Add both ways
    }

    // Returns all neighbors of a node (connected maze paths)
    public List<Node> getNeighbors(Node node) {
        return adjacencyList.getOrDefault(node, new ArrayList<>()); // Return empty list if no neighbors
    }

    // Represents a maze cell/node with coordinates (x,y)
    public static class Node {
        final int x;
        final int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // Equality based on coordinates for proper hashing in adjacencyList
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        // HashCode uses coordinates to ensure consistent hashing
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
