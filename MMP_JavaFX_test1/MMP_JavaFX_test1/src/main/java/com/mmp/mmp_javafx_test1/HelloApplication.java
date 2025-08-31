package com.mmp.mmp_javafx_test1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.util.List;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Pane root = fxmlLoader.load(); //Load the root (vbox in this case)

        HelloController controller =
                fxmlLoader.getController(); //get the controller instance

        Scene scene = new Scene(root, 800, 600); //create scene with loaded root


        stage.setTitle("Maze Generator");


        // Generate maze
        MazeGenerator generator = new MazeGenerator(20, 15);
        Graph mazeGraph = generator.generate();

        // Create maze grid
        MazeGrid mazeGrid = new MazeGrid(mazeGraph, 20, 15, 30);

        // Set start and end nodes
        Graph.Node start = new Graph.Node(0, 0);
        Graph.Node end = new Graph.Node(19, 14);
        mazeGrid.setStartNode(start);
        mazeGrid.setEndNode(end);

        // Solve maze
        AStarSolver solver = new AStarSolver(mazeGraph, start, end);
        List<Graph.Node> path = solver.findPath();
        mazeGrid.highlightPath(path);

       //use the controller to set the maze in the designated container
        controller.setMazeGrid(mazeGrid);
        controller.setApplication(this);//optinonal: if controller needs a reference back

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
