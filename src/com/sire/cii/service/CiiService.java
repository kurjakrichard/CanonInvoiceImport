/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template excelFilePath, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sire.cii.service;

import com.sire.cii.dto.ExportDatas;
import com.sire.cii.dto.ImportDatas;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javafx.scene.control.Alert;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

/**
 *
 * @author sire
 */
public final class CiiService {

    private List<ExportDatas> items;
    private final String[] columnNames = {"SzamlaSzam", "Kelte", "Teljesites", "AFADatum", "Hatarido", "Konyveles", "Netto", "BrutoVegosszeg", "Ktgh", "Rendeles", "Dolgozó", "Gep", "Projekt", "Megjegyzes"};
    private final String serviceText = "Szervíz példányszám ";

    public CiiService() {
    }

    /**
     * Import Canon invoices from excel file.
     *
     * @param importFilePath
     * @return
     */
    public List<ImportDatas> excelToList(String importFilePath) {
        List<ImportDatas> machines = new ArrayList<>();
        double invoiceNumber = 0;
        double netto = 0;
        double brutto = 0;
        String machineID = null;
        String partnerName = null;
        try {
            FileInputStream inputStream = new FileInputStream(importFilePath);
            Workbook workbook = new XSSFWorkbook(inputStream);
            Iterator<Sheet> sheetIterator = workbook.iterator();
            while (sheetIterator.hasNext()) {
                Sheet sheet = sheetIterator.next();
                Iterator<Row> rowIterator = sheet.iterator();
                while (rowIterator.hasNext()) {
                    Row nextRow = rowIterator.next();
                    Iterator<Cell> cellIterator = nextRow.cellIterator();

                    //Végig megy az cellákon egy soron belül
                    try {
                        while (cellIterator.hasNext()) {
                            Cell nextCell = cellIterator.next();
                            int columnIndex = nextCell.getColumnIndex();
                            switch (columnIndex) {
                                case 0:
                                    invoiceNumber = nextCell.getNumericCellValue();
                                    break;
                                case 11:
                                    netto = nextCell.getNumericCellValue();
                                    break;
                                case 13:
                                    brutto = nextCell.getNumericCellValue();
                                    break;
                                case 14:
                                    partnerName = nextCell.getStringCellValue();
                                    break;
                                case 17:
                                    try {
                                    machineID = String.valueOf((int) nextCell.getNumericCellValue());
                                } catch (Exception e) {
                                    machineID = nextCell.getStringCellValue();
                                }
                                break;
                            }
                        }
                        if (invoiceNumber != 0 && netto != 0 && machineID != null) {
                            ImportDatas element = new ImportDatas(invoiceNumber, netto, brutto, machineID, partnerName);
                            machines.add(element);
                        }
                        invoiceNumber = 0;
                        netto = 0;
                    } catch (Exception e) {
                    }
                }
            }
            workbook.close();
        } catch (FileNotFoundException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Hiányzik a fájl!");
            alert.showAndWait();
        } catch (IOException ex) {
            System.out.println("Hiba2");
        }
        return machines;
    }

    /**
     * Add brutto in invoice items.
     *
     * @param machines
     * @return 
     */
    public List<ImportDatas> bruttoToImportList(List<ImportDatas> machines) {

        Map<Double, Double> invoiceNumbers = new HashMap<>();

        for (ImportDatas machine : machines) {
            double invoiceNumber = machine.getInvoiceNumber();
            double brutto = machine.getBrutto();
            invoiceNumbers.putIfAbsent(invoiceNumber, 0.0);
            double original = invoiceNumbers.get(machine.getInvoiceNumber());
            invoiceNumbers.put(invoiceNumber, original + brutto);
        }
        for (ImportDatas machine : machines) {
            machine.setBrutto(invoiceNumbers.get(machine.getInvoiceNumber()));
        }
        return machines;
    }

    public void printImportList(List<ImportDatas> machines) {
        for (ImportDatas machine : machines) {
            System.out.println(machine.toString());
        }
    }

    public void printExportList(List<ExportDatas> items) {
        for (ExportDatas item : items) {
            System.out.println(item.toString());
        }
    }

    /**
     * Crreate 
     * @param item
     * @param machines
     * @return 
     */
    public List<ExportDatas> createItemList(ExportDatas item, List<ImportDatas> machines) {
        items = new ArrayList<>();
        ExportDatas element;
        String text = item.getNotes();
        for (ImportDatas machine : machines) {
            element = new ExportDatas(item.getInvoiceDate(), item.getSettlingDate(), item.getVATDate(), item.getDueDate(), item.getBookingDate(), item.getNotes());
            element.setInvoiceNumber(machine.getInvoiceNumber());
            element.setNetto(machine.getNetto());
            element.setBruttoSumInvoice(machine.getBrutto());
            element.setMachineID(machine.getMachineID());
            element.setNotes(serviceText + text + " " + machine.getPartnerName());
            items.add(element);
        }
        return items;
    }

    /**
     * Export Canon invoices from excel file.
     *
     * @param exportFilePath
     * @param items
     * @throws java.io.FileNotFoundException
     *
     */
    public void listToExcel(String exportFilePath, List<ExportDatas> items) throws FileNotFoundException, IOException {
        FileOutputStream out = new FileOutputStream(new File(exportFilePath));
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        workbook.setSheetName(0, "Munka1");
        Row row = sheet.createRow(0);
        for (int i = 0; i < columnNames.length; i++) {
            row.createCell(i).setCellValue(columnNames[i]);
        }
        int rowNum = 1;
        for (ExportDatas item : items) {
            row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(item.getInvoiceNumber());
            row.createCell(1).setCellValue(item.getInvoiceDate());
            row.createCell(2).setCellValue(item.getSettlingDate());
            row.createCell(3).setCellValue(item.getVATDate());
            row.createCell(4).setCellValue(item.getDueDate());
            row.createCell(5).setCellValue(item.getBookingDate());
            row.createCell(6).setCellValue(item.getNetto());
            row.createCell(7).setCellValue(item.getBruttoSumInvoice());
            row.createCell(8).setCellValue(item.getCostCenter());
            row.createCell(9).setCellValue(item.getOrderNumber());
            row.createCell(10).setCellValue(item.getEmployee());
            row.createCell(11).setCellValue(item.getMachineID());
            row.createCell(12).setCellValue(item.getProjekt());
            row.createCell(13).setCellValue(item.getNotes());
        }
        workbook.write(out);
        out.close();
        workbook.close();
    }
}
