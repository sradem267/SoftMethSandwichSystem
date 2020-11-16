package sample;

import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
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
    private ListView<Extra> lvIngredientSelections;

    @FXML
    private ListView<Extra> lvExtraIngredients;

    @FXML
    private TextArea displayArea;

    @FXML
    private Button bttnAdd, bttnRemove, bttnClear;

    @FXML
    private TextField price;

    private Chicken chicken_sandwich = new Chicken();
    private Beef beef_sandwich = new Beef();
    private Fish fish_sandwich = new Fish();
    private ObservableList<String> list = FXCollections.observableArrayList("Beef", "Fish", "Chicken");
    private ObservableList<String> chickenData = FXCollections.observableArrayList("Fried Chicken", "Spicy Sauce", "Pickles");
    private ObservableList<String> beefData = FXCollections.observableArrayList("Roast Beef", "Provolone", "Mustard");
    private ObservableList<String> fishData = FXCollections.observableArrayList("Grilled Snapper", "Cilantro", "Lime");
    private ObservableList<Extra> extraIngredients =
            FXCollections.observableArrayList(Extra.LETTUCE, Extra.OLIVES , Extra.CHEDDAR, Extra.TOMATOES,
                    Extra.SWISS, Extra.MUSHROOMS, Extra.JALAPENOS, Extra.MAYO, Extra.SPINACH, Extra.ONIONS);



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
        }
        else if (comboBox.getSelectionModel().getSelectedItem().equals("Beef")) {
            listView.setItems(beefData);
        }
        else if (comboBox.getSelectionModel().getSelectedItem().equals("Fish")) {
            listView.setItems(fishData);
        }
        else{
            displayArea.appendText("Congrats you broke the combo box!\n");
        }
    }

    @FXML
    public void addClicked(MouseEvent event) {
        ObservableList<Extra> extras = lvIngredientSelections.getSelectionModel().getSelectedItems();
        if (comboBox.getSelectionModel().getSelectedItem() == null || comboBox.getSelectionModel().getSelectedItem().equals("Chicken")) {
            for(Extra extra: extras){
                if(chicken_sandwich.add(extra)) {
                    lvExtraIngredients.getItems().add(extra);
                    price.setText(String.format("%.2f", chicken_sandwich.price()));
                }
                else{
                    displayArea.appendText("Item already exists!\n");
                    continue;
                }
            }
        }
        else if (comboBox.getSelectionModel().getSelectedItem().equals("Beef")) {
            for(Extra extra: extras){
                if(beef_sandwich.add(extra)) {
                    lvExtraIngredients.getItems().add(extra);
                    price.setText(String.format("%.2f", beef_sandwich.price()));
                }
                else{
                    displayArea.appendText("Item already exists!\n");
                    continue;
                }
            }
        }
        else if (comboBox.getSelectionModel().getSelectedItem().equals("Fish")) {
            for(Extra extra: extras){
                if(fish_sandwich.add(extra)) {
                    lvExtraIngredients.getItems().add(extra);
                    price.setText(String.format("%.2f", fish_sandwich.price()));
                }
                else{
                    displayArea.appendText("Item already exists!\n");
                    continue;
                }
            }
        }
        else{
            displayArea.appendText("Congrats you broke the listView box!\n");
        }
    }

    @FXML
    public void removeClicked(MouseEvent event) {
        ObservableList<Extra> observalbeextrasRemoved = lvExtraIngredients.getSelectionModel().getSelectedItems();
        ArrayList<Extra> extrasRemoved = new ArrayList<>(observalbeextrasRemoved);
        if (comboBox.getSelectionModel().getSelectedItem() == null || comboBox.getSelectionModel().getSelectedItem().equals("Chicken")) {
            for(int i = 0; i < extrasRemoved.size(); i++){
                if(chicken_sandwich.remove(extrasRemoved.get(i))) {
                    lvExtraIngredients.getItems().remove(extrasRemoved.get(i));
                    //lvExtraIngredients.getSelectionModel().clearSelection();
                    price.setText(String.format("%.2f", chicken_sandwich.price()));
                }
                else{
                    displayArea.appendText("Item not found!\n");
                    continue;
                }
            }
        }
        else if (comboBox.getSelectionModel().getSelectedItem().equals("Beef")) {
            for(Extra extraRm: extrasRemoved){
                if(beef_sandwich.remove(extraRm)) {
                    lvExtraIngredients.getSelectionModel().clearSelection();
                    price.setText(String.format("%.2f", beef_sandwich.price()));
                }
                else{
                    displayArea.appendText("Item not found!\n");
                    continue;
                }
            }
        }
        else if (comboBox.getSelectionModel().getSelectedItem().equals("Fish")) {
            for (Extra extraRm : extrasRemoved) {
                if (fish_sandwich.remove(extraRm)) {
                    lvExtraIngredients.getSelectionModel().clearSelection();
                    price.setText(String.format("%.2f", fish_sandwich.price()));
                } else {
                    displayArea.appendText("Item not found!\n");
                    continue;
                }
            }
        }
        else{
            displayArea.appendText("Congrats you broke the listView box!\n");
        }
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
