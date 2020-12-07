/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template excelFilePath, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sire.cii.service;

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

//<editor-fold defaultstate="collapsed" desc="Class variables">
    String excelFilePath = "iranyitoszam.xlsx";
    List<ImportDatas> machines;
//</editor-fold>

    public CiiService() {
        excelToList(excelFilePath);
    }

    /**
     * Import Canon invoices from excel file.
     *
     * @param excelFilePath
     */
    private void excelToList(String excelFilePath) {
        machines = new ArrayList<>();
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

        } catch (FileNotFoundException ex) {
            System.out.println("Hiba1");
        } catch (IOException ex) {
            System.out.println("Hiba2");
        }
    }
}
