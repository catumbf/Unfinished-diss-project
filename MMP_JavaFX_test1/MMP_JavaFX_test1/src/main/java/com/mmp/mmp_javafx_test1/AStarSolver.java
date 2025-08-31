package com.mmp.mmp_javafx_test1;

import java.util.*;

public class AStarSolver {
    private final Graph graph;
    private final Graph.Node start, end;

    public AStarSolver(Graph graph, Graph.Node start, Graph.Node end) {
        this.graph = graph;
        this.start = start;
        this.end = end;
    }

    // Add this method to calculate heuristic (Manhattan distance)
    private int heuristic(Graph.Node a, Graph.Node b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    //uses lambda to priotitise by g, this is a placeholder for when i upgrade from djkstra to A*
    public List<Graph.Node> findPath() {
        PriorityQueue<NodeWrapper> openSet = new PriorityQueue<>(Comparator.comparingInt(n -> n.f));
        Map<Graph.Node, NodeWrapper> allNodes = new HashMap<>();

        NodeWrapper startWrapper = new NodeWrapper(start, 0, end);
        openSet.add(startWrapper);
        allNodes.put(start, startWrapper);

        while (!openSet.isEmpty()) {
            NodeWrapper current = openSet.poll();

            //this prevents the algorithm from showing all visited nodes
            if (current.node.equals(end)) {
                return reconstructPath(current);
            }

            for (Graph.Node neighbor : graph.getNeighbors(current.node)) {
                int tentativeG = current.g + 1;
                //need to start commenting. Checks to see
                //research to see if the update step for dijksra's is needed or if its an oversimplification.
                NodeWrapper neighborWrapper = allNodes.computeIfAbsent(neighbor,
                        n -> new NodeWrapper(n, Integer.MAX_VALUE, end));

                if (tentativeG < neighborWrapper.g) {
                    neighborWrapper.g = tentativeG;
                    neighborWrapper.f = tentativeG + heuristic(neighbor, end);
                    neighborWrapper.parent = current;

                    if (!openSet.contains(neighborWrapper)) {
                        openSet.add(neighborWrapper);
                    }
                }
            }
        }
        return Collections.emptyList();
    }

    private List<Graph.Node> reconstructPath(NodeWrapper node) {
        List<Graph.Node> path = new ArrayList<>();
        while (node != null) {
            path.add(node.node);
            node = node.parent;
        }
        Collections.reverse(path);
        return path;
    }

    //static to keep seperate from graph class
    //nodes can exist seperately from graph
    //doesn't need an instace of graph
    //adjaceny lists are handeled seperate from the node class
    private class NodeWrapper {
        Graph.Node node;
        int g; // Cost from start
        int f; // Total cost (g + h)
        NodeWrapper parent;

        NodeWrapper(Graph.Node node, int g, Graph.Node end) {
            this.node = node;
            this.g = g;
            this.f = g + heuristic(node, end);
        }
    }
}