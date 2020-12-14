package com;

import com.frontEnd.MainWindow;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Run extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        var mainWindow = new MainWindow();
        Global.initialize(mainWindow.getMainBoxOfElements());
        Global.primaryStage.setResizable(true);
        Global.primaryStage.setTitle("Program to work with database of flights");
        Global.primaryStage.getIcons().add(new Image("/com/images/icon.png"));
        Global.primaryStage.setOnCloseRequest(event -> {
            Platform.setImplicitExit(false);
            if (Global.changed) {
                if (Global.onCloseRequest.show()) Platform.setImplicitExit(true);
                else event.consume();
            } else Platform.setImplicitExit(true);
        });
        Global.primaryStage.show();
    }
}
