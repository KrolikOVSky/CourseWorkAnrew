package com;

import com.backEnd.Flight;
import com.backEnd.Flights;
import com.frontEnd.Header;
import com.frontEnd.OnCloseRequest;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.ResourceBundle;

public class Global {
    public static final String extension = ".db";
    public static String path = "";
    public static Flights flights = new Flights();
    public static Stage primaryStage = new Stage();
    public static TableView<Flight> mainTable = new TableView<>();
    public static boolean changed = false;
    public static OnCloseRequest onCloseRequest = new OnCloseRequest();

    public static void initialize(Node node) {
        flights.getFlights().addListener((ListChangeListener<Flight>) c -> {
            changed = true;
        });

        primaryStage.setScene(new Scene(new BorderPane(node,new Header().getMainMenuBar(),null,null,null)));
    }

    public static void fromListToFile() {
        StringBuilder content = new StringBuilder();
        for (var el : flights.getFlights()) {
            content .append(String.format("id=%d;\n", el.getId()))
                    .append(String.format("flightCode=%s;\n", el.getFlightCode()))
                    .append(String.format("planeType=%s;\n", el.getPlaneType()))
                    .append(String.format("destination=%s;\n", el.getDestination()))
                    .append(String.format("departTime=%s;\n", el.getDepartTime()))
                    .append(String.format("totalTime=%d;\n\n", el.getTotalTime()));
        }
        try (FileWriter writer = new FileWriter(path)) {
            writer.append(content);
            writer.flush();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
        changed = false;
    }

    public static void fromFileToList() {
        String[] content = new String[0];
        try {
            content = Files
                    .readString(Paths.get(path))
                    .replaceAll("\n", "")
                    .replaceAll("\r", "")
                    .split(";");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        flights.clear();
        var j = 0;
        for (var i = 0; i < content.length / 6; i++) {
            Flight flight = new Flight();
            flight.setId(Long.parseLong(content[j].substring((content[j].indexOf("=")) + 1)));
            j++;
            flight.setFlightCode(content[j].substring((content[j].indexOf("=") + 1)));
            j++;
            flight.setPlaneType(content[j].substring((content[j].indexOf("=") + 1)));
            j++;
            flight.setDestination(content[j].substring((content[j].indexOf("=") + 1)));
            j++;
            flight.setDepartTime(content[j].substring((content[j].indexOf("=") + 1)));
            j++;
            flight.setTotalTime(Integer.parseInt(content[j].substring((content[j].indexOf("=")) + 1)));
            j++;
            flights.add(flight);
        }
    }

    public static void newSource() {
        flights.clear();
        path = "";
        changed = false;
    }

    public static void saveAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save database file");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("My Database Files (*" + extension + ")", "*" + extension);
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialFileName("Database");
        File file = fileChooser.showSaveDialog(primaryStage);
        if (file != null) {
            path = file.getPath();
            try {
                fromListToFile();
                changed = false;
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public static void openAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open database file");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("My Database Files (*" + extension + ")", "*" + extension);
        fileChooser.getExtensionFilters().addAll(extFilter);
        File file = fileChooser.showOpenDialog(primaryStage);
//        File file = new File("C:\\Users\\Alex\\Desktop\\test_Andrew.db");
        if (file != null) {
            path = file.getPath();
            fromFileToList();
            changed = false;
        }

    }

}
