package com.mmp.mmp_javafx_test1;

import javafx.scene.layout.GridPane;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MazeGrid extends GridPane {
    private final Map<Graph.Node, MazeCell> cellMap = new HashMap<>();
    private final int cellSize;
    private Graph mazeGraph;
    // In MazeGrid constructor


    public MazeGrid(Graph graph, int width, int height, int cellSize) {

        this.cellSize = cellSize;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Graph.Node node = new Graph.Node(x, y);
                MazeCell cell = new MazeCell(node, cellSize);
                cellMap.put(node, cell);
                add(cell, x, y);
            }
        }

        // Connect cells based on graph edges

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Graph.Node current = new Graph.Node(x, y);
                MazeCell currentCell = cellMap.get(current);

                // Check right neighbor
                if (x < width - 1) {
                    Graph.Node rightNode = new Graph.Node(x + 1, y);
                    if (graph.getNeighbors(current).contains(rightNode)) {
                        currentCell.setRightBorderVisible(false);
                        MazeCell rightCell = cellMap.get(rightNode);
                        rightCell.setLeftBorderVisible(false);
                    }
                }

                // Check bottom neighbor
                if (y < height - 1) {
                    Graph.Node bottomNode = new Graph.Node(x, y + 1);
                    if (graph.getNeighbors(current).contains(bottomNode)) {
                        currentCell.setBottomBorderVisible(false);
                        MazeCell bottomCell = cellMap.get(bottomNode);
                        bottomCell.setTopBorderVisible(false);
                    }
                }
            }
        }


    }


    //this needs to be much more complex, it can traverse and highlight the list with some case statements.
    //need to implement tests
    public void highlightPath(List<Graph.Node> path) {
        path.forEach(node -> {
            if (cellMap.containsKey(node)) {
                cellMap.get(node).markAsPath();
            }
        });
    }

    public void setStartNode(Graph.Node start) {
        if (cellMap.containsKey(start)) {
            cellMap.get(start).markAsStart();
        }
    }

    public void setEndNode(Graph.Node end) {
        if (cellMap.containsKey(end)) {
            cellMap.get(end).markAsEnd();
        }
    }
}
