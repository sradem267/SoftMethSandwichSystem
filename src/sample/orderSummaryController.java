package sample;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class orderSummaryController {

    @FXML
    private ListView<Object> listView;

    @FXML
    private Button duplicateOrderBttn, removeOrderBttn, clearOrderBttn, backBttnc, saveOrderBttn;

    @FXML
    private TextArea orderTotalPrice;

    private Order order = new Order(); // instantiate new Order object

    @FXML
    public void displayPrice() {
        double price = 0.0;
        String orderTotal = "";
        for (Object ojb : order.getorderlines()){ //get objects from arraylist
            OrderLine orderLine = (OrderLine) ojb; //cast them as an orderline object
            price += orderLine.getPrice();
        }

        orderTotal += String.format("%.2f", price);

        orderTotalPrice.appendText(orderTotal);
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) { //initialize this when the show order button is clicked
        listView.getItems().addAll(order.getorderlines());
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    public void duplicateOrder(MouseEvent event) {
        //are you allowed to select more than one item order to duplicate at once??

        ObservableList<Object> orderItem = listView.getSelectionModel().getSelectedItems();

        OrderLine selectedOrder = (OrderLine) orderItem;

        OrderLine duplicateOrderSandwich = new OrderLine(selectedOrder.getSandwich());
        order.add(duplicateOrderSandwich);
        listView.getItems().addAll(order.getorderlines()); //print updated list to listview
    }

    @FXML
    public void removeOrder(MouseEvent event) {
        ObservableList<Object> orderItem = listView.getSelectionModel().getSelectedItems();

        OrderLine selectedOrder = (OrderLine) orderItem;
        order.remove(selectedOrder);

        for (int i = 0; i < order.getorderlines().size(); i ++){
            OrderLine orderLine = (OrderLine) order.getorderlines().get(i); //cast obj from arrayList as orderline obj
            OrderLine.setLineNumber(orderLine, i);
        }

        //listView.getSelectionModel().clearSelection(); //not sure if this will work
        listView.getItems().addAll(order.getorderlines()); //print updated list to listview
    }

    @FXML
    public void clearOrder(MouseEvent event) {
        listView.getItems().clear();
        order.getorderlines().clear();

    }

    @FXML
    public void goBack(MouseEvent event) {

    }

    @FXML
    public void saveOrder(MouseEvent event) {

    }

}
