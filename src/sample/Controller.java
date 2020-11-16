package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

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
    private Button bttnAdd, bttnRemove, bttnClear, bttnAddOrder, bttnShowOrder;

    @FXML
    private TextField price;

    @FXML
    private ImageView imageView;

    private OrderController orderController;
    private Sandwich sandwich = new Chicken();
    private ObservableList<String> list = FXCollections.observableArrayList("Beef", "Fish", "Chicken");
    private ObservableList<String> chickenData = FXCollections.observableArrayList("Fried Chicken", "Spicy Sauce", "Pickles");
    private ObservableList<String> beefData = FXCollections.observableArrayList("Roast Beef", "Provolone", "Mustard");
    private ObservableList<String> fishData = FXCollections.observableArrayList("Grilled Snapper", "Cilantro", "Lime");
    private ObservableList<Extra> extraIngredients =
            FXCollections.observableArrayList(Extra.LETTUCE, Extra.OLIVES , Extra.CHEDDAR, Extra.TOMATOES,
                    Extra.SWISS, Extra.MUSHROOMS, Extra.JALAPENOS, Extra.MAYO, Extra.SPINACH, Extra.ONIONS);
    private static final int MAX_EXTRAS = 6;


    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        orderController = null;
        Image image = new Image(getClass().getResourceAsStream("/images/ChickenSandwich.jpeg"));
        imageView.setImage(image);
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
            sandwich = new Chicken();
            listView.setItems(chickenData);
            Image image = new Image(getClass().getResourceAsStream("/images/ChickenSandwich.jpeg"));
            imageView.setImage(image);
        }
        else if (comboBox.getSelectionModel().getSelectedItem().equals("Beef")) {
            sandwich = new Beef();
            listView.setItems(beefData);
            Image image = new Image(getClass().getResourceAsStream("/images/RoastBeef.jpeg"));
            imageView.setImage(image);
        }
        else if (comboBox.getSelectionModel().getSelectedItem().equals("Fish")) {
            sandwich = new Fish();
            listView.setItems(fishData);
            Image image = new Image(getClass().getResourceAsStream("/images/FishSandwich.jpeg"));
            imageView.setImage(image);
        }
        else{
            displayArea.appendText("Congrats you broke the combo box!\n");
        }
    }

    @FXML
    public void addClicked(MouseEvent event) {
        ObservableList<Extra> extras = lvIngredientSelections.getSelectionModel().getSelectedItems();
        for(Extra extra: extras){
            if(sandwich.add(extra)) {
                lvExtraIngredients.getItems().add(extra);
                price.setText(String.format("%.2f", sandwich.price()));
            }
            else{
                if(extras.size() > MAX_EXTRAS){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setHeaderText("CANNOT add more than 6 items!");
                    alert.setContentText("Number of extra ingredients is limited to 6.");
                    alert.showAndWait();
                }
                else {
                    displayArea.appendText("Item already exists!\n");
                    continue;
                }

            }
        }
    }

    @FXML
    public void removeClicked(MouseEvent event) {
        ObservableList<Extra> observableExtrasRemoved = lvExtraIngredients.getSelectionModel().getSelectedItems();
        ArrayList<Extra> extrasRemoved = new ArrayList<>(observableExtrasRemoved);
        for(int i = 0; i < extrasRemoved.size(); i++){
            if(sandwich.remove(extrasRemoved.get(i))) {
                lvExtraIngredients.getItems().remove(extrasRemoved.get(i));
                price.setText(String.format("%.2f", sandwich.price()));
            }
            else{
                displayArea.appendText("Item not found!\n");
                continue;
            }
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

    public void clickedAddtoOrder(MouseEvent event) throws IOException
    {
        Order order = new Order();
        OrderLine newOrder = new OrderLine(sandwich);
        order.add(newOrder);
        displayArea.appendText("Sandwich added to order.\n");
    }

    public void clickedShowOrder(MouseEvent event) throws IOException
    {
        if ( orderController == null ) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("orderSummary.fxml"));
                Scene scene = new Scene(loader.load(), 600, 418);
                orderController = loader.getController();
                orderController.setMainController(this);
                Stage stage = new Stage();
                stage.setTitle("My Sandwich Store");
                stage.setScene(scene);
                //stage.setOnCloseRequest(e -> orderController = null);
                stage.show();
            } catch ( IOException e ) {
                orderController = null;
                displayArea.appendText("Error: Failed to open order window\n");
            }
        } else {
            // Order window is already open
            displayArea.appendText("Order window already open\n");
        }
    }

    /**
     * Gets the ArrayList of pizzas and returns it.
     *
     * @return pizzaOrder The ArrayList of pizzas being ordered
     */
    protected ArrayList<OrderLine> get_orderlines () {
        Order order = new Order();
        return order.getorderlines();
    }
}
