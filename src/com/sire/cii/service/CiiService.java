/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template excelFilePath, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sire.cii.service;

import com.sire.cii.dto.ExportDatas;
import com.sire.cii.dto.ImportDatas;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

/**
 *
 * @author sire
 */
public final class CiiService {

    private List<ExportDatas> items;

    public CiiService() {
    }

    /**
     * Import Canon invoices from excel file.
     *
     * @param excelFilePath
     */
    public List<ImportDatas> excelToList(String excelFilePath) {
        List<ImportDatas> machines = new ArrayList<>();
        double invoiceNumber = 0;
        double netto = 0;
        String machineID = null;
        String partnerName = null;
        try {
            FileInputStream inputStream = new FileInputStream(excelFilePath);
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
                                case 17:
                                    machineID = nextCell.getStringCellValue();
                                    break;
                                case 14:
                                    partnerName = nextCell.getStringCellValue();
                                    break;
                            }
                        }
                        if (invoiceNumber != 0 && netto != 0) {
                            ImportDatas element = new ImportDatas(invoiceNumber, netto, machineID, partnerName);
                            machines.add(element);
                            invoiceNumber = 0;
                            netto = 0;
                        }
                    } catch (Exception e) {
                        invoiceNumber = 0;
                    }
                }
            }
            workbook.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Hiányzik a fájl");
        } catch (IOException ex) {
            System.out.println("Hiba2");
        }
        return machines;
    }

    /**
     * Add brutto in invoice items.
     *
     * @param excelFilePath
     */
    public List<ExportDatas> bruttoToImportList(List<ExportDatas> items) {

        Map<Double, Double> invoiceNumbers = new HashMap<>();

        for (ExportDatas item : items) {
            invoiceNumbers.put(item.getInvoiceNumber(), invoiceNumbers.get(item.getInvoiceNumber() + item.getNetto()));
            System.out.println(invoiceNumbers.toString());
        }

        return items;
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

    public List<ExportDatas> createItemList(ExportDatas item, List<ImportDatas> machines) {
        items = new ArrayList<>();
        ExportDatas element;
        String text = item.getNotes();
        for (ImportDatas machine : machines) {
            element = new ExportDatas(item.getInvoiceDate(), item.getSettlingDate(), item.getVATDate(), item.getDueDate(), item.getBookingDate(), item.getNotes());
            element.setInvoiceNumber(machine.getInvoiceNumber());
            element.setNetto(machine.getNetto());
            element.setMachineID(machine.getMachineID());
            element.setNotes(text + " " + machine.getPartnerName());
            items.add(element);
        }
        return items;
    }
}
