package com.sire.cii.controller;

import com.sire.cii.domain.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLController implements Initializable{

    @FXML
    private AnchorPane mainPane;
    @FXML
    TableView<Product> table;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableData();
    }

    @FXML
    private void setTableData() {
        TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Product, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(100);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        table.getColumns().addAll(nameColumn, priceColumn,quantityColumn);
        table.setItems(getProduct());
    }

    private ObservableList<Product> getProduct(){
        ObservableList<Product> products = FXCollections.observableArrayList();
        products.add(new Product("Laptop", 55.2,5 ));
        products.add(new Product("MP3 player", 5.2,50 ));
        products.add(new Product("Mouse", 1.2,22 ));
        products.add(new Product("Keyboard", 2.2,15 ));
        products.add(new Product("Monitor", 15.2,16 ));
        return products;
    }
}
