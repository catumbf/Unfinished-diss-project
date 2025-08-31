/* Old code, Change this back it it doesnt work
             package com.mmp.mmp_javafx_test1;

             import javafx.fxml.FXML;
             import javafx.scene.control.Label;

             public class HelloController {
                 @FXML
                 private Label welcomeText;

                 @FXML
                 protected void onHelloButtonClick() {
                     welcomeText.setText("Welcome to JavaFX Application!");
                 }
             }
             */
// src/main/java/com/mmp/mmp_javafx_test1/HelloController.java
package com.mmp.mmp_javafx_test1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane; //Imports pane

public class HelloController {
    @FXML
    private Label welcomeText;

    //Inject a pane from FXML
    @FXML
    private Pane mazeContainer;

    private HelloApplication application;

    public void setApplication(HelloApplication application) {
        this.application = application;
    }

    @FXML
    protected void generateNewMaze() {
        //TODO: Implement logic to clear old maze (if any)
        //Also Trigger regeneration in HelloApplication or a dedicated service
        //For now, just update text

        // Implement new maze generation logic
        welcomeText.setText("New maze generated!");
    }

    //Add this method to display the maze
    public void setMazeGrid(MazeGrid mazeGrid){
        mazeContainer.getChildren().setAll(mazeGrid);
        //clear previous children and add new grid
    }
}
