/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sire.cii.controller;

import com.sire.cii.service.CiiService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author balza
 */
public class ciiFXMLDocumentController implements Initializable {

    @FXML
    private Button exportbutton;
    @FXML
    private DatePicker inpurInvoiceDate;
    @FXML
    private DatePicker inputsettlingDate;
    @FXML
    private DatePicker inputVATDate;
    @FXML
    private DatePicker inputDueDate;
    @FXML
    private TextField inputNotes;

    @FXML
    private void handleButtonAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CiiService service = new CiiService();
    }

    @FXML
    private void fillDates(ActionEvent event) {
    }

}
