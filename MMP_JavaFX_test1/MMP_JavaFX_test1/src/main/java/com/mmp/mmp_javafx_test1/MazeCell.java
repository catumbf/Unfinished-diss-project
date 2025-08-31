
package com.mmp.mmp_javafx_test1;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



// MazeCell.java
    //can just feed the graph through and then see what are connected
    public class MazeCell extends StackPane {
        private final int size;
        private final Graph.Node node;
        private BorderStroke topBorder;
        private BorderStroke bottomBorder;
        private BorderStroke leftBorder;
        private BorderStroke rightBorder;

        public MazeCell(Graph.Node node, int size) {
            this.node = node;
            this.size = size;
            setStyle("-fx-background-color: white;");
            setPrefSize(size, size);

            // Initialize all borders
            topBorder = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT);
            rightBorder = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT);
            bottomBorder = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT);
            leftBorder = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT);

            setBorder(new Border(topBorder, rightBorder, bottomBorder, leftBorder));
        }

        //new methods to control borders

        public void setRightBorderVisible(boolean visible) {
            rightBorder = visible ?
                    new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT) :
                    new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT);
            updateBorders();
        }

        public void setLeftBorderVisible(boolean visible) {
            leftBorder = visible ?
                    new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT) :
                    new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT);
            updateBorders();
        }

        public void setBottomBorderVisible(boolean visible) {
            bottomBorder = visible ?
                    new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT) :
                    new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT);
            updateBorders();
        }

        public void setTopBorderVisible(boolean visible) {
            topBorder = visible ?
                    new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT) :
                    new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT);
            updateBorders();
        }

        private void updateBorders() {
            setBorder(new Border(topBorder, rightBorder, bottomBorder, leftBorder));
        }


    public void markAsPath() {
        setStyle("-fx-background-color: #00ff00; -fx-border-color: #00cc00; -fx-border-width: 1px;");
    }

    public void markAsStart() {
        setStyle("-fx-background-color: #005ce6; -fx-border-color: white; -fx-border-width: 2px;");
    }

    public void markAsEnd() {
        setStyle("-fx-background-color: #ff4444; -fx-border-color: white; -fx-border-width: 2px;");
    }


    }


// MazeGrid.java

