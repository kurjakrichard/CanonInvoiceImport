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
import java.util.Iterator;
import java.util.List;
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
                                case 17:
                                    machineID = nextCell.getStringCellValue();
                                    break;
                                case 14:
                                    partnerName = nextCell.getStringCellValue();
                                    break;
                            }
                        }
                        if (invoiceNumber != 0) {
                            ImportDatas element = new ImportDatas(invoiceNumber, machineID, partnerName);
                            machines.add(element);
                        }

                    } catch (Exception e) {
                        invoiceNumber = 0;
                    }
                }
            }

            workbook.close();
            System.out.println(machines.size());

        } catch (FileNotFoundException ex) {
            System.out.println("Hiányzik a fájl");
        } catch (IOException ex) {
            System.out.println("Hiba2");
        }
        return machines;
    }

    public void printList(List<ImportDatas> machines) {
        for (ImportDatas machine : machines) {
            System.out.println(machine.toString());
        }
    }

    public List<ExportDatas> createItemList(ExportDatas item, List<ImportDatas> machines) {
        items = new ArrayList<>();
        String text = item.getNotes();
        for (ImportDatas machine : machines) {
            item.setInvoiceNumber(machine.getInvoiceNumber());
            item.setMachineID(machine.getMachineID());
            item.setNotes(text + " " + machine.getPartnerName());
            System.out.println(item.toString());
            items.add(item);
        }

        return items;
    }
}
