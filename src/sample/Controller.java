package sample;

import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class Controller implements Initializable{
    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private ListView<String> listView;

    @FXML
    private ListView<String> lvIngredientSelections;

    @FXML
    private ListView<String> lvExtraIngredients;

    @FXML
    private TextArea displayArea;

    @FXML
    private Button bttnAdd, bttnRemove, bttnClear;

    private Chicken chicken_sandwich;
    private Beef beef_sandwich;
    private Fish fish_sandwich;
    ObservableList<String> list = FXCollections.observableArrayList("Beef", "Fish", "Chicken");
    ObservableList<String> chickenData = FXCollections.observableArrayList("Fried Chicken", "Spicy Sauce", "Pickles");
    ObservableList<String> beefData = FXCollections.observableArrayList("Roast Beef", "Provolone", "Mustard");
    ObservableList<String> fishData = FXCollections.observableArrayList("Grilled Snapper", "Cilantro", "Lime");
    ObservableList<String> extraIngredients =
            FXCollections.observableArrayList("Lettuce", "Onions", "Olives", "Cheddar", "Tomatoes",
                    "Swiss", "Mushrooms", "Jalapenos", "Mayo", "Spinach");


    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        comboBox.setItems(list);
        listView.setItems(chickenData);
        listView.setDisable(true);
        lvIngredientSelections.setItems(extraIngredients);
        lvIngredientSelections.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lvExtraIngredients.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    public void comboChanged(ActionEvent event) {
        if (comboBox.getSelectionModel().getSelectedItem().equals("Chicken")) {
            listView.setItems(chickenData);
            chicken_sandwich = new Chicken();
        }
        else if (comboBox.getSelectionModel().getSelectedItem().equals("Beef")) {
            listView.setItems(beefData);
            beef_sandwich = new Beef();
        }
        else if (comboBox.getSelectionModel().getSelectedItem().equals("Fish")) {
            listView.setItems(fishData);
            fish_sandwich = new Fish();
        }
        else{
            displayArea.appendText("Congrats you broke the combo box!\n");
        }
    }

    @FXML
    public void addClicked(MouseEvent event) {
        ObservableList<String> extras;
        extras = lvIngredientSelections.getSelectionModel().getSelectedItems();
        //boolean success = false;
        /*
        for(String extra: extras){
            if(chicken_sandwich.add(extra))
                success = true;
            else{
                success = false;
                displayArea.appendText("Item not found!\n");
                continue;
            }
        }
        if(success)
            lvExtraIngredients.setItems(extras);
        */
        lvExtraIngredients.setItems(extras);
    }

    @FXML
    public void removeClicked(MouseEvent event) {
        ObservableList<String> extrasRemoved;
        extrasRemoved = lvExtraIngredients.getSelectionModel().getSelectedItems();
        //boolean success = true;
        /*
        for(String extraRm: extrasRemoved){
            if(chicken_sandwich.remove(extraRm))
                continue;
            else{
                success = false;
                displayArea.appendText("Item not found!\n");
            }
        }
        if(success)
            lvExtraIngredients.setItems(extras);
        */
        lvExtraIngredients.getSelectionModel().clearSelection();

    }

    /**
     * Clears the Extra Ingredients ListView.
     * @param event
     */
    @FXML
    void clear(MouseEvent event) {
        lvExtraIngredients.getItems().clear();
    }
}
