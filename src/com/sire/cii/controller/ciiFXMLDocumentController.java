/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sire.cii.controller;

import com.sire.cii.dto.ExportDatas;
import com.sire.cii.dto.ImportDatas;
import com.sire.cii.service.CiiService;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 *
 * @author balza
 */
public class ciiFXMLDocumentController implements Initializable {

//<editor-fold defaultstate="collapsed" desc="FXML variables">
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
    private DatePicker inputBookingDate;
    @FXML
    private TextField inputNotes;
    @FXML
    private Button importButtun;
//</editor-fold>

    private CiiService ciiService;
    private final String EXCELFILEPATH = "import.xlsx";
    private List<ImportDatas> machines;
    private ExportDatas item;
    private List<ExportDatas> items;
    String invoiceDate;
    String settlingDate;
    String VATDate;
    String dueDate;
    String bookingDate;
    String notes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ciiService = new CiiService();
    }

    @FXML
    private void fillDates(ActionEvent event) {

    }

    @FXML
    private void handleImportButtonAction(ActionEvent event) {
        try {
            invoiceDate = inpurInvoiceDate.getValue().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
            settlingDate = inputsettlingDate.getValue().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
            VATDate = inputVATDate.getValue().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
            dueDate = inputDueDate.getValue().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
            bookingDate = inputBookingDate.getValue().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
            notes = inputNotes.getText();
            machines = ciiService.excelToList(EXCELFILEPATH);
        } catch (Exception e) {
            alert(Alert.AlertType.ERROR, "Kérlek adj meg minden adatot!");
        }
    }

    @FXML
    private void handleExportButtonAction(ActionEvent event) {
        item = new ExportDatas(invoiceDate, settlingDate, VATDate, dueDate, bookingDate, notes);
        items = ciiService.createItemList(item, machines);
    }

    private void alert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
