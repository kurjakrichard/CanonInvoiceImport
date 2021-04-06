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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
    @FXML
    private TableView<ImportDatas> invoiceList;
    @FXML
    private TableColumn<ImportDatas, Double> invoiceNumberColumn;
    @FXML
    private TableColumn<ImportDatas, String> partnerNameColumn;
    @FXML
    private TableColumn<ImportDatas, Double> nettoColumn;
    @FXML
    private TableColumn<ImportDatas, String> machineidColumn;
//</editor-fold>

    private CiiService ciiService;
    private final String USERHOME = System.getProperty("user.home");
    //private final String IMPORTFILEPATH = USERHOME + "\\OneDrive\\Asztali gép\\import.xlsx";
    //private final String EXPORTFILEPATH = USERHOME + "\\OneDrive\\Asztali gép\\export.xlsx";
    private final String IMPORTFILEPATH = USERHOME + "\\Desktop\\import.xlsx";
    private final String EXPORTFILEPATH = USERHOME + "\\Desktop\\export.xlsx";
    private List<ImportDatas> machines;
    private final List<String> companies;
    private ExportDatas item;
    private List<ExportDatas> items;
    String invoiceDate;
    String settlingDate;
    String VATDate;
    String dueDate;
    String bookingDate;
    double netto;
    String notes;
    ObservableList<ImportDatas> importDatasList;

    public ciiFXMLDocumentController() {
        this.companies = new ArrayList<>(Arrays.asList("DMRV Zrt."));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ciiService = new CiiService();
    }

    private void initCol() {
        invoiceNumberColumn.setCellValueFactory(new PropertyValueFactory<>("invoiceNumber"));
        partnerNameColumn.setCellValueFactory(new PropertyValueFactory<>("partnerName"));
        nettoColumn.setCellValueFactory(new PropertyValueFactory<>("netto"));
        machineidColumn.setCellValueFactory(new PropertyValueFactory<>("machineID"));
    }

    private void loadData() {
        importDatasList = FXCollections.observableArrayList(machines);
        invoiceList.getItems().setAll(importDatasList);
    }

    @FXML
    private void fillDates(ActionEvent event) {
        LocalDate date = inputsettlingDate.getValue();
        inputVATDate.setValue(date);
        inputDueDate.setValue(date);
    }

    @FXML
    private void handleImportButtonAction(ActionEvent event) {
        machines = ciiService.excelToList(IMPORTFILEPATH);
        machines = ciiService.sumCompanies(companies, machines);
        machines = ciiService.sumMachines(machines);
        ciiService.bruttoToImportList(machines);
        initCol();
        loadData();
    }

    @FXML
    private void handleExportButtonAction(ActionEvent event) {
        try {
            try {
                invoiceDate = inpurInvoiceDate.getValue().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                settlingDate = inputsettlingDate.getValue().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                VATDate = inputVATDate.getValue().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                dueDate = inputDueDate.getValue().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                bookingDate = inputBookingDate.getValue().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                notes = inputNotes.getText();
            } catch (Exception e) {
                alert(Alert.AlertType.ERROR, "Kérlek, adj meg minden adatot!");
                return;
            }
            item = new ExportDatas(invoiceDate, settlingDate, VATDate, dueDate, bookingDate, notes);
            items = ciiService.createItemList(item, machines);
            ciiService.listToExcel(EXPORTFILEPATH, items);
        } catch (Exception e) {
            alert(Alert.AlertType.ERROR, "Kérlek, előbb az importot futtasd le!");
        }
    }

    private void alert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
