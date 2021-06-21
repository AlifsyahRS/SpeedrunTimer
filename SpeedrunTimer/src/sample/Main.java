package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Main extends Application { // Contains the code for the splits window

    TableView<Split> splits = new TableView<>(); // Table to store all the segments of the speedrun
    Stopwatch timer = new Stopwatch(); // Creates the timer window
    final ObservableList<Split> allSplits = FXCollections.observableArrayList(); // ObservableList used to store the data for the TableView
    int numSplits = 0; // Variable to track the number of segments added (Work in Progress)

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(new Group());
        primaryStage.setTitle("Timer's splits");
        primaryStage.setWidth(300);
        primaryStage.setHeight(600);
        final Label label = new Label("Segments");
        label.setFont(new Font("Arial", 20));
        TableColumn firstColumn = new TableColumn("Segment"); // Creating table columns for splits table
        firstColumn.setCellValueFactory(new PropertyValueFactory<Split,String>("splitTitle")); // Bonding the cells of the table to the custom class
        TableColumn secondColumn = new TableColumn("Time Completed");
        secondColumn.setCellValueFactory(new PropertyValueFactory<Split,String>("timeCompleted"));
        splits.setItems(allSplits);

        final TextField addSplit = new TextField(); // Text field to add splits to the table
        addSplit.setPromptText("Split Title");
        addSplit.setMaxWidth(firstColumn.getPrefWidth());

        final Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent e){
                allSplits.add(new Split(addSplit.getText())); // When the Add button is pressed, a new split is added to the table
                addSplit.clear(); // Clears the text field after add button is pressed
            }

        });




        splits.getColumns().addAll(firstColumn,secondColumn);

        final VBox vbox = new VBox(); // Used to align all previously made widgets in a vertical manner
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, splits,addSplit,addButton);


        ((Group) scene.getRoot()).getChildren().addAll(vbox); // Packing all the widgets

        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
