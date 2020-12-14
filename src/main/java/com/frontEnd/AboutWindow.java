package com.frontEnd;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;


public class AboutWindow {

    private BorderPane mainBox;

    public AboutWindow(){
        this.mainBox = new BorderPane();
        var style = "-fx-font-size: 20; -fx-font-family: 'Courier New'; -fx-font-width: bold;";
        var str = "System was developed by student\nShestakov Andrey Alekseevich\nfrom IVT/b-19-2-o group\nSevSU - 2020";

        Image image = new Image("/com/images/LogoAbout.png", 256, 187, true, true);
        ImageView imageView = new ImageView(image);

        Label label = new Label(str);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setStyle(style);
        BorderPane.setAlignment(label, Pos.CENTER);


        mainBox.setPadding(new Insets(10));
        mainBox.setCenter(imageView);
        mainBox.setBottom(label);
    }

    public void show(){
        ModalWindow modalWindow = new ModalWindow("About");
        modalWindow.setMainWorkSpace(mainBox);
        modalWindow.show(true);
    }
}
