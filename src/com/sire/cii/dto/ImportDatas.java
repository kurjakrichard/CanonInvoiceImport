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
    private String machineID;
    private String partnerName;
    private double bruttoSumInvoice;
//</editor-fold>

    public ImportDatas() {
    }

    public ImportDatas(double invoiceNumber, double netto, String machineID, String partnerName) {
        this.invoiceNumber = invoiceNumber;
        this.netto = netto;
        this.machineID = machineID;
        this.partnerName = partnerName;
    }


    public double getInvoiceNumber() {
        return invoiceNumber;
    }

    public double getNetto() {
        return netto;
    }
    
    public String getMachineID() {
        return machineID;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public double getBruttoSumInvoice() {
        return bruttoSumInvoice;
    }

    public void setBruttoSumInvoice(double bruttoSumInvoice) {
        this.bruttoSumInvoice = bruttoSumInvoice;
    }

    @Override
    public String toString() {
        return "ImportDatas{" + "invoiceNumber=" + invoiceNumber + ", netto=" + netto + ", machineID=" + machineID + ", partnerName=" + partnerName + ", bruttoSumInvoice=" + bruttoSumInvoice + '}';
    }

  

}
