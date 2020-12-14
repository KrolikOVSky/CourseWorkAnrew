package com.frontEnd;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;

public class HelpWindow {

    private BorderPane mainBox;

    public HelpWindow(){
        this.mainBox = new BorderPane();

        var str = "System of storage and\nmanaging flights information";

        Image image = new Image("/com/images/LogoInfo.png", 256, 187, true, true);
        ImageView imageView = new ImageView(image);

        Label label = new Label(str);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setStyle("-fx-font-size: 20; -fx-font-family: 'Courier New'; -fx-font-width: bold;");
        BorderPane.setAlignment(label, Pos.CENTER);


        mainBox.setPadding(new Insets(10));
        mainBox.setCenter(imageView);
        mainBox.setBottom(label);


    }

    public void show(){
        ModalWindow modalWindow = new ModalWindow("Help");
        modalWindow.setMainWorkSpace(mainBox);
        modalWindow.show();
    }

}
