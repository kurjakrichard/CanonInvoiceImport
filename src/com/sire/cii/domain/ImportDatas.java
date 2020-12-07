/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sire.cii.domain;

/**
 *
 * @author balza
 */
public class ImportDatas {
//<editor-fold defaultstate="collapsed" desc="Class variables">
    private int id;
    private String invoiceNumber;
    private String machineID;
    private String partnerName;
//</editor-fold>

    public ImportDatas() {
    }

    public ImportDatas(String invoiceNumber, String machineID, String partnerName) {
        this.invoiceNumber = invoiceNumber;
        this.machineID = machineID;
        this.partnerName = partnerName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
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

            



  
    
}
