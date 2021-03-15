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
    private double netto;
    private double brutto;
    private String machineID;
    private String partnerName;
//</editor-fold>

    public ImportDatas() {
    }

    public ImportDatas(double invoiceNumber, double netto, double brutto, String machineID, String partnerName) {
        this.invoiceNumber = invoiceNumber;
        this.netto = netto;
        this.brutto = brutto;
        this.machineID = machineID;
        this.partnerName = partnerName;
    }

    public double getInvoiceNumber() {
        return invoiceNumber;
    }

    public double getNetto() {
        return netto;
    }

    public void setNetto(double netto) {
        this.netto = netto;
    }

    public double getBrutto() {
        return brutto;
    }

    public void setBrutto(double brutto) {
        this.brutto = brutto;
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

    @Override
    public String toString() {
        return "ImportDatas{" + "invoiceNumber=" + invoiceNumber + ", netto=" + netto + ", brutto=" + brutto + ", machineID=" + machineID + ", partnerName=" + partnerName + '}';
    }
}
