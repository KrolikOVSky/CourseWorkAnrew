package com.frontEnd;

import com.backEnd.Flight;
import com.backEnd.Flights;
import com.backEnd.ResultOne;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import static com.Global.*;
import static java.lang.Double.MAX_VALUE;

public class MainWindow {

    private final String style = "-fx-border-radius: 5; -fx-border-width: 0.5; -fx-border-color: black;";

    private final BorderPane mainBoxOfElements = new BorderPane();
    private final VBox buttonBox = new VBox(10);

    private final TableColumn<Flight, String> flightCodeColumn = new TableColumn<>("Flight code");
    private final TableColumn<Flight, String> planeTypeColumn = new TableColumn<>("Plane type");
    private final TableColumn<Flight, String> destinationColumn = new TableColumn<>("Destination");
    private final TableColumn<Flight, String> departTimeColumn = new TableColumn<>("Depart Time");
    private final TableColumn<Flight, Long> totalTimeColumn = new TableColumn<>("Total time in flight");

    private final Button showAllBtn = new Button("Show all");
    private final Button addBtn = new Button("Add element");
    private final Button sortBtn = new Button("Sort");
    private final Button remByCondBtn = new Button("Remove by condition");
    private final Button resultOneBtn = new Button("Result 1");
    private final Button resultTwoBtn = new Button("Result 2");
    private final Button filterBtn = new Button("Filter");

    private final TextField remByCondInput = new TextField();
    private final TextField filterInput = new TextField();

    private final VBox remByCondBox = new VBox();
    private final VBox filterBox = new VBox();

    public MainWindow() {
        // Table Initialize
        {
            int width = 150;
            flightCodeColumn.setCellValueFactory(new PropertyValueFactory<>("flightCode"));
            flightCodeColumn.setPrefWidth(width);

            planeTypeColumn.setCellValueFactory(new PropertyValueFactory<>("planeType"));
            planeTypeColumn.setPrefWidth(width);

            destinationColumn.setCellValueFactory(new PropertyValueFactory<>("destination"));
            destinationColumn.setPrefWidth(width);

            departTimeColumn.setCellValueFactory(new PropertyValueFactory<>("departTime"));
            departTimeColumn.setPrefWidth(width);

            totalTimeColumn.setCellValueFactory(new PropertyValueFactory<>("totalTime"));
            totalTimeColumn.setPrefWidth(width);

            mainTable.getColumns().add(flightCodeColumn);
            mainTable.getColumns().add(planeTypeColumn);
            mainTable.getColumns().add(destinationColumn);
            mainTable.getColumns().add(departTimeColumn);
            mainTable.getColumns().add(totalTimeColumn);
            mainTable.setItems(flights.getFlights());
            mainTable.setOnMouseClicked(event -> edit(event));
        }
        //Box with buttons
        {

            // Show all
            {
                showAllBtn.setOnAction(event -> {
                    if (flights != null) {
                        addToTable(flights);
                    }
                });
                showAllBtn.setMaxSize(MAX_VALUE, MAX_VALUE);
            }

            // Add
            {
                addBtn.setOnAction(event -> {
                    if (flights != null) {
                        ModalWindow modalWindow = new ModalWindow("Add element");
                        VBox addBox = new VBox(10);
                        TextField flightCodeInput = new TextField();
                        TextField planeTypeInput = new TextField();
                        TextField destinationInput = new TextField();
                        VBox departTime = new VBox();
                        TextField totalTimeInput = new TextField();
                        Button commit = new Button("Add element");
                        // Add box
                        {
                            Spinner<Integer> chooseHours = new Spinner<>(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23));
                            Spinner<Integer> chooseMinutes = new Spinner<>(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
                            var width = 300;
                            // Flight code input
                            {
                                flightCodeInput.setPromptText("Enter Flight code");
                                flightCodeInput.setPrefWidth(width);
                            }

                            // Plane type input
                            {
                                planeTypeInput.setPromptText("Enter plane type");
                                planeTypeInput.setPrefWidth(width);
                            }

                            // Destination input
                            {
                                destinationInput.setPromptText("Enter Destination");
                                destinationInput.setPrefWidth(width);
                            }

                            // Depart time input
                            {
                                chooseHours.setEditable(true);
                                chooseMinutes.setEditable(true);

                                Label hoursLbl = new Label("Hours");
                                Label minutesLbl = new Label("Minutes");
                                Label caption = new Label("Depart time");

                                HBox hoursBox = new HBox(5);
                                HBox minutesBox = new HBox(5);

                                hoursBox.getChildren().addAll(chooseHours, hoursLbl);
                                minutesBox.getChildren().addAll(chooseMinutes, minutesLbl);

                                departTime.getChildren().addAll(caption, hoursBox, minutesBox);
                                departTime.setSpacing(10);
                                departTime.setPadding(new Insets(5));
                                departTime.setStyle("-fx-border-color: black; -fx-border-width: 0.5; -fx-border-radius: 5");
                            }

                            // Total time
                            {
                                totalTimeInput.setPromptText("Enter total time in flight");
                                totalTimeInput.setPrefWidth(width);
                                totalTimeInput.textProperty().addListener((observable, oldValue, newValue) -> {
                                    if (!newValue.matches("\\d*")) {
                                        totalTimeInput.setText(newValue.replaceAll("[^\\d]", ""));
                                    }
                                });
                            }

                            // Commit button
                            {
                                commit.setOnAction(e -> {
                                    if (!flightCodeInput.getText().isEmpty() &&
                                            !planeTypeInput.getText().isEmpty() &&
                                            !destinationInput.getText().isEmpty() &&
                                            !totalTimeInput.getText().isEmpty()
                                    ) {
                                        var h = chooseHours.getValue();
                                        var m = chooseMinutes.getValue();
                                        var departTimeFormat = String.format("%s:%s", ((h < 10) ? "0" + h : h), ((m < 10) ? "0" + m : m));

                                        flights.add(new Flight(
                                                flightCodeInput.getText(),
                                                planeTypeInput.getText(),
                                                destinationInput.getText(),
                                                departTimeFormat,
                                                Integer.parseInt(totalTimeInput.getText())));
                                        modalWindow.close();
                                    }
                                });
                            }

                            addBox.setAlignment(Pos.CENTER);
                            addBox.setPadding(new Insets(0, 0, 10, 0));
                            addBox.getChildren().add(flightCodeInput);
                            addBox.getChildren().add(planeTypeInput);
                            addBox.getChildren().add(destinationInput);
                            addBox.getChildren().add(departTime);
                            addBox.getChildren().add(totalTimeInput);
                            addBox.getChildren().add(commit);
                        }
                        modalWindow.setMainWorkSpace(addBox);
                        modalWindow.show();
                    }
                });
                addBtn.setMaxSize(MAX_VALUE, MAX_VALUE);

            }

            // Sort
            {
                sortBtn.setOnAction(event -> {
                    if (flights != null && !flights.getFlights().isEmpty()) {
                        flights.sort();
                    }
                });
                sortBtn.setMaxSize(MAX_VALUE, MAX_VALUE);

            }

            // Remove by condition
            {
                TextField inputRemoveCondition = new TextField();
                Button btnRemove = new Button();

                //Button Remove ++
                {
                    remByCondBtn.setMaxSize(MAX_VALUE, MAX_VALUE);
                    remByCondBtn.setOnAction(event -> {
                        if (!remByCondInput.getText().equals("") && flights != null && !flights.getFlights().isEmpty()) {
                            flights.removeByCondition(remByCondInput.getText());
                            remByCondInput.clear();
                        }
                    });
                }

                //Remove Condition ++
                {
                    remByCondInput.setPromptText("Enter remove condition");
                    remByCondInput.setMaxSize(MAX_VALUE, MAX_VALUE);
                    remByCondInput.setOnKeyPressed(event -> {
                        if (event.getCode() == KeyCode.ENTER) remByCondBtn.getOnAction().handle(new ActionEvent());
                    });
                }

                remByCondBox.getChildren().addAll(remByCondInput, remByCondBtn);
                remByCondBox.setSpacing(10);
                remByCondBox.setPadding(new Insets(5));
                remByCondBox.setStyle(style);
            }

            // Result 1
            {
                resultOneBtn.setOnAction(event -> {
                    if (flights != null && !flights.getFlights().isEmpty()) {
//                            ScrollPane scrollBox = new ScrollPane();
//                            HBox resultTable = new HBox();
//                            VBox posNameBox = new VBox();
                        BorderPane mainBox = new BorderPane();
                        TableView<ResultOne> resultTable = new TableView<>();
                        TableColumn<ResultOne, String> plainTypeCol = new TableColumn<>("Plain type");
                        TableColumn<ResultOne, Integer> flightsNumCol = new TableColumn<>("Flights quantity");
                        TableColumn<ResultOne, Integer> minDepTimeCol = new TableColumn<>("Min depart time");

                        plainTypeCol.setCellValueFactory(new PropertyValueFactory<>("planeType"));
                        flightsNumCol.setCellValueFactory(new PropertyValueFactory<>("flightsNum"));
                        minDepTimeCol.setCellValueFactory(new PropertyValueFactory<>("minDepTime"));

                        resultTable.getColumns().add(plainTypeCol);
                        resultTable.getColumns().add(flightsNumCol);
                        resultTable.getColumns().add(minDepTimeCol);
                        resultTable.setItems(flights.result1());

                        mainBox.setCenter(resultTable);
                        mainBox.setPadding(new Insets(0, 0, 10, 0));

                        ModalWindow window = new ModalWindow("Result 1");
                        window.setMainWorkSpace(mainBox);
                        window.show();
                    }
                });

                resultOneBtn.setMaxSize(MAX_VALUE, MAX_VALUE);

            }

            // Result 2
            {
                resultTwoBtn.setOnAction(event -> {
                    if (flights != null && !flights.getFlights().isEmpty()) {
                        VBox content = new VBox();
                        content.setAlignment(Pos.CENTER_LEFT);
                        content.setPadding(new Insets(0, 0, 10, 0));
                        content.setSpacing(10);
                        content.getChildren().add(new Label(String.format("Total number of destinations are %d", flights.result2())));
                        ModalWindow modalWindow = new ModalWindow("Result 2");
                        modalWindow.setMainWorkSpace(content);
                        modalWindow.show();
                    }
                });

                resultTwoBtn.setMaxSize(MAX_VALUE, MAX_VALUE);
            }

            // Filter
            {
                // Input
                {
                    filterInput.setPromptText("Enter filter");
                    filterInput.setOnAction(event -> filterBtn.getOnAction().handle(new ActionEvent()));
                }

                // Confirm btn
                {
                    filterBtn.setOnAction(event -> {
                        if (flights != null && !flights.getFlights().isEmpty() && !filterInput.getText().equals("")) {
                            addToTable(flights.filter(filterInput.getText()));
                        }
                    });
//                    filterBtn.setPrefWidth(150);
                    filterBtn.setMaxSize(MAX_VALUE, MAX_VALUE);
                }

                filterBox.getChildren().addAll(filterInput, filterBtn);
                filterBox.setSpacing(10);
                filterBox.setPadding(new Insets(5));
                filterBox.setStyle(style);
            }

            buttonBox.getChildren().add(showAllBtn);
            buttonBox.getChildren().add(addBtn);
            buttonBox.getChildren().add(sortBtn);
            buttonBox.getChildren().add(remByCondBox);
            buttonBox.getChildren().add(filterBox);
            buttonBox.getChildren().add(resultOneBtn);
            buttonBox.getChildren().add(resultTwoBtn);
            buttonBox.setPadding(new Insets(10));
        }
    }

    private void addToTable(Flights flights) {
        mainTable.setItems(flights.getFlights());
    }

    public BorderPane getMainBoxOfElements() {
        mainBoxOfElements.setLeft(buttonBox);
        mainBoxOfElements.setCenter(mainTable);
        return mainBoxOfElements;
    }

    public void edit(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseButton.SECONDARY) {
            Flight currentFlight = mainTable.getSelectionModel().getSelectedItem();
            if (currentFlight != null) {
                ContextMenu contextMenu = new ContextMenu();
                MenuItem removeItem = new MenuItem(String.format("Remove %s", currentFlight.getFlightCode()));
                MenuItem editItem = new MenuItem(String.format("Edit %s", currentFlight.getFlightCode()));

                removeItem.setOnAction(event -> {
                    flights.remove(currentFlight.getId());
                });

                editItem.setOnAction(event -> {
                    if(flights != null){
                        ModalWindow modalWindow = new ModalWindow("Update element");
                        VBox addBox = new VBox(10);
                        TextField flightCodeInput = new TextField();
                        TextField planeTypeInput = new TextField();
                        TextField destinationInput = new TextField();
                        VBox departTime = new VBox();
                        TextField totalTimeInput = new TextField();
                        Button commit = new Button("Update element");
                        // Update box
                        {
                            Spinner<Integer> chooseHours = new Spinner<>(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23));
                            Spinner<Integer> chooseMinutes = new Spinner<>(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
                            var width = 300;
                            // Flight code input
                            {
                                flightCodeInput.setPromptText("Enter Flight code");
                                flightCodeInput.setText(currentFlight.getFlightCode());
                                flightCodeInput.setPrefWidth(width);
                            }

                            // Plane type input
                            {
                                planeTypeInput.setPromptText("Enter plane type");
                                planeTypeInput.setText(currentFlight.getPlaneType());
                                planeTypeInput.setPrefWidth(width);
                            }

                            // Destination input
                            {
                                destinationInput.setPromptText("Enter Destination");
                                destinationInput.setText(currentFlight.getDestination());
                                destinationInput.setPrefWidth(width);
                            }

                            // Depart time input
                            {
                                var h = currentFlight.getDepartTime().split(":")[0];
                                var m = currentFlight.getDepartTime().split(":")[1];

                                chooseHours.setEditable(true);
                                chooseMinutes.setEditable(true);

                                chooseHours.getValueFactory().setValue(Integer.valueOf(h));
                                chooseMinutes.getValueFactory().setValue(Integer.valueOf(m));

                                Label hoursLbl = new Label("Hours");
                                Label minutesLbl = new Label("Minutes");
                                Label caption = new Label("Depart time");

                                HBox hoursBox = new HBox(5);
                                HBox minutesBox = new HBox(5);

                                hoursBox.getChildren().addAll(chooseHours, hoursLbl);
                                minutesBox.getChildren().addAll(chooseMinutes, minutesLbl);

                                departTime.getChildren().addAll(caption, hoursBox, minutesBox);
                                departTime.setSpacing(10);
                                departTime.setPadding(new Insets(5));
                                departTime.setStyle("-fx-border-color: black; -fx-border-width: 0.5; -fx-border-radius: 5");
                            }

                            // Total time
                            {
                                totalTimeInput.setPromptText("Enter total time in flight");
                                totalTimeInput.setText(String.valueOf(currentFlight.getTotalTime()));
                                totalTimeInput.setPrefWidth(width);
                                totalTimeInput.textProperty().addListener((observable, oldValue, newValue) -> {
                                    if (!newValue.matches("\\d*")) {
                                        totalTimeInput.setText(newValue.replaceAll("[^\\d]", ""));
                                    }
                                });
                            }

                            // Commit button
                            {
                                commit.setOnAction(e -> {
                                    if (!flightCodeInput.getText().isEmpty() &&
                                            !planeTypeInput.getText().isEmpty() &&
                                            !destinationInput.getText().isEmpty() &&
                                            !totalTimeInput.getText().isEmpty()
                                    ) {
                                        var h = chooseHours.getValue();
                                        var m = chooseMinutes.getValue();
                                        var departTimeFormat = String.format("%s:%s", ((h < 10) ? "0" + h : h), ((m < 10) ? "0" + m : m));

                                        flights.getById(currentFlight.getId()).setFlightCode(flightCodeInput.getText());
                                        flights.getById(currentFlight.getId()).setPlaneType(planeTypeInput.getText());
                                        flights.getById(currentFlight.getId()).setDestination(destinationInput.getText());
                                        flights.getById(currentFlight.getId()).setDepartTime(departTimeFormat);
                                        flights.getById(currentFlight.getId()).setTotalTime(Integer.parseInt(totalTimeInput.getText()));

                                        changed = true;

                                        modalWindow.close();
                                    }
                                });
                            }

                            addBox.setAlignment(Pos.CENTER);
                            addBox.setPadding(new Insets(0, 0, 10, 0));
                            addBox.getChildren().add(flightCodeInput);
                            addBox.getChildren().add(planeTypeInput);
                            addBox.getChildren().add(destinationInput);
                            addBox.getChildren().add(departTime);
                            addBox.getChildren().add(totalTimeInput);
                            addBox.getChildren().add(commit);
                        }
                        modalWindow.setMainWorkSpace(addBox);
                        modalWindow.show();
                    }
                });

                contextMenu.getItems().add(removeItem);
                contextMenu.getItems().add(editItem);
                mainTable.setContextMenu(contextMenu);
            }
        }
    }

}
