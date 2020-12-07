/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sire.cii.dto;

/**
 *
 * @author balza
 */
public class ImportDatas {
//<editor-fold defaultstate="collapsed" desc="Class variables">
    private double invoiceNumber;
    private String machineID;
    private String partnerName;
//</editor-fold>

    public ImportDatas() {
    }

    public ImportDatas(double invoiceNumber, String machineID, String partnerName) {
        this.invoiceNumber = invoiceNumber;
        this.machineID = machineID;
        this.partnerName = partnerName;
    }

    public double getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(double invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getMachineID() {
        return machineID;
    }

    public void setMachineID(String machineID) {
        this.machineID = machineID;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    @Override
    public String toString() {
        return "ImportDatas{" + "invoiceNumber=" + invoiceNumber + ", machineID=" + machineID + ", partnerName=" + partnerName + '}';
    }

            



  
    
}
