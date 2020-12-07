/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template excelFilePath, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sire.cii.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

/**
 *
 * @author sire
 */
public final class CiiService {

//<editor-fold defaultstate="collapsed" desc="Class variables">
    private final String FILENAME = "cii.db";
    String excelFilePath = "iranyitoszam.xlsx";
//</editor-fold>

    public CiiService() {
        excelToDatabase(excelFilePath);
    }

    /**
     * Import Canon invoices from excel file.
     *
     * @param excelFilePath
     */
    private void excelToDatabase(String excelFilePath) {

        try {
            FileInputStream inputStream = new FileInputStream(excelFilePath);
            Workbook workbook = new XSSFWorkbook(inputStream);
            //Csak az első munkalapot olvassa be, ez hiba
            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = firstSheet.iterator();

            while (rowIterator.hasNext()) {
                Row nextRow = rowIterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();
                double invoiceNumber = 0;
                int count = 0;
                //Végig megy az cellákon egy soron belül

                while (cellIterator.hasNext()) {
                    Cell nextCell = cellIterator.next();
                    int columnIndex = nextCell.getColumnIndex();

                    switch (columnIndex) {
                        case 0:
                        try {
                            invoiceNumber = nextCell.getNumericCellValue();
                            System.out.println("A számlaszám: " + invoiceNumber);
                        } catch (Exception e) {
                            System.out.println("Nem számlaszámot adtál meg!");
                        }
                        break;
                        case 17:
                            String machineid = nextCell.getStringCellValue();
                            System.out.println(machineid);
                            break;
                        case 14:
                            String partnername = nextCell.getStringCellValue();
                            System.out.println(partnername);
                            break;
                    }
                }

                count++;
            }
            workbook.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CiiService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CiiService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
