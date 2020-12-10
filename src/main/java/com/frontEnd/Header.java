package com.frontEnd;

import com.Global;
import javafx.event.EventType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.WindowEvent;

public class Header {

    private final MenuBar mainMenuBar;
    private final Menu fileMenu = new Menu("File");
    private final Menu editMenu = new Menu("Edit");
    private final Menu helpMenu = new Menu("Help");
    private final MenuItem newItem = new MenuItem("New");
    private final MenuItem openItem = new MenuItem("Open");
    private final MenuItem saveAsItem = new MenuItem("Save as...");
    private final MenuItem saveItem = new MenuItem("Save");
    private final MenuItem closeItem = new MenuItem("Close");
    private final MenuItem addTableItem = new MenuItem("Add table");
    private final MenuItem delTableItem = new MenuItem("Delete table");
    private final MenuItem addRecordItem = new MenuItem("Add new record to table");
    private final MenuItem delRecordItem = new MenuItem("Delete record from table");
    private final MenuItem helpItem = new MenuItem("Help");
    private final MenuItem aboutItem = new MenuItem("About...");

    public Header() {
        // Menu
        mainMenuBar = new MenuBar();
        {
//          File
            {
//              New
                {
                    newItem.setOnAction(event -> {
                        if (Global.changed) {
                            if (Global.onCloseRequest.show()) Global.newSource();
                            ;
                        } else {
                            Global.newSource();
                        }
                    });
                    newItem.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
                }

//              Open
                {
                    openItem.setOnAction(event -> {
                        if (Global.changed) {
                            if (Global.onCloseRequest.show()) Global.openAction();
                            ;
                        } else {
                            Global.openAction();
                        }
                    });
                    openItem.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
                }

//              Save As...
                {
                    saveAsItem.setOnAction(event -> Global.saveAction());
                    saveAsItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN));
                }

//              Save
                {
                    saveItem.setOnAction(event -> {
                        if (!Global.path.equals("")) {
                            Global.fromListToFile();
                        } else {
                            Global.saveAction();
                        }
                    });
                    saveItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
                }

//              Close
                {
                    var eventType = new EventType<>();
                    closeItem.setOnAction(event -> Global.primaryStage.getOnCloseRequest().handle(new WindowEvent(Global.primaryStage.getScene().getWindow(), eventType)));
                }

                fileMenu.getItems().add(newItem);
                fileMenu.getItems().add(openItem);
                fileMenu.getItems().add(new SeparatorMenuItem());
                fileMenu.getItems().add(saveItem);
                fileMenu.getItems().add(saveAsItem);
                fileMenu.getItems().add(new SeparatorMenuItem());
                fileMenu.getItems().add(closeItem);
            }

//          Edit
            {
//              Add table
                {
                    addTableItem.setOnAction(event -> {

                    });
                }

//              Delete table
                {
                    delTableItem.setOnAction(event -> {

                    });
                }

//              Add new record to table
                {
                    addRecordItem.setOnAction(event -> {

                    });
                }

//              Delete record from table
                {
                    delRecordItem.setOnAction(event -> {

                    });
                }

                editMenu.getItems().add(addTableItem);
                editMenu.getItems().add(delTableItem);
                editMenu.getItems().add(new SeparatorMenuItem());
                editMenu.getItems().add(addRecordItem);
                editMenu.getItems().add(delRecordItem);
            }

//          Help
            {
//              Help
                {
                    helpItem.setOnAction(event -> {

                    });
                    helpItem.setAccelerator(new KeyCodeCombination(KeyCode.F1));
                }

//              About
                {
                    aboutItem.setOnAction(event -> {
                    });
                }

                helpMenu.getItems().addAll(helpItem, new SeparatorMenuItem(), aboutItem);
            }

            mainMenuBar.getMenus().add(fileMenu);
            mainMenuBar.getMenus().add(editMenu);
            mainMenuBar.getMenus().add(helpMenu);
        }
    }

    public MenuBar getMainMenuBar() {
        return mainMenuBar;
    }
}
