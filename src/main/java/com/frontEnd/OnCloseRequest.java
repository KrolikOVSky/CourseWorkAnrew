package com.frontEnd;

import com.Global;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Optional;

public class OnCloseRequest {
    private final Alert alert = new Alert(Alert.AlertType.WARNING);

    public OnCloseRequest() {
        ButtonType yesBtn = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noBtn = new ButtonType("No", ButtonBar.ButtonData.NO);
        ButtonType cancelBtn = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/com/images/icon.png"));
        alert.setTitle(Global.primaryStage.getTitle());
        alert.setHeaderText("Do you want to save changes in file?");
        alert.getButtonTypes().setAll(yesBtn, noBtn, cancelBtn);
    }

    public boolean show() {
        Optional<ButtonType> result = alert.showAndWait();
        switch (result.get().getButtonData().getTypeCode()) {
            case "Y": {
                if (!Global.path.equals("")) {
                    Global.fromListToFile();
                } else {
                    Global.saveAction();
                }
                return true;
            }
            case "N": {
                return true;
            }
            default: {
                return false;
            }
        }
    }
}
