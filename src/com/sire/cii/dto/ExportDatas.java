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
public class ExportDatas {

//<editor-fold defaultstate="collapsed" desc="Class variables">
    private double invoiceNumber;
    private String invoiceDate;
    private String settlingDate;
    private String VATDate;
    private String dueDate;
    private String bookingDate;
    private double netto;
    private double bruttoSumInvoice;
    private String costCenter;
    private String orderNumber;
    private String employee;
    private String machineID;
    private String projekt;
    private String notes;
//</editor-fold>

    public ExportDatas() {
    }

    public ExportDatas(String invoiceDate, String settlingDate, String VATDate, String dueDate, String bookingDate, String notes) {
        this.invoiceNumber = 0;
        this.invoiceDate = invoiceDate;
        this.settlingDate = settlingDate;
        this.VATDate = VATDate;
        this.dueDate = dueDate;
        this.bookingDate = bookingDate;
        this.netto = 0;
        this.bruttoSumInvoice = 0;
        this.costCenter = "";
        this.orderNumber = "/Nincs rendeléshez sorolva";
        this.employee = "/nincs dolgozóhoz sorolva";
        this.machineID = "";
        this.projekt = "";
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public double getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(double invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public String getSettlingDate() {
        return settlingDate;
    }

    public String getVATDate() {
        return VATDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public double getNetto() {
        return netto;
    }

    public void setNetto(double netto) {
        this.netto = netto;
    }

    public double getBruttoSumInvoice() {
        return bruttoSumInvoice;
    }

    public void setBruttoSumInvoice(double bruttoSumInvoice) {
        this.bruttoSumInvoice = bruttoSumInvoice;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getEmployee() {
        return employee;
    }

    public String getMachineID() {
        return machineID;
    }

    public void setMachineID(String machineID) {
        this.machineID = machineID;
    }

    public String getProjekt() {
        return projekt;
    }

    public void setProjekt(String projekt) {
        this.projekt = projekt;
    }

    @Override
    public String toString() {
        return "ExportDatas{" + "invoiceNumber=" + invoiceNumber + ", invoiceDate=" + invoiceDate + ", settlingDate=" + settlingDate + ", VATDate=" + VATDate + ", dueDate=" + dueDate + ", bookingDate=" + bookingDate + ", netto=" + netto + ", bruttoSumInvoice=" + bruttoSumInvoice + ", machineID=" + machineID + ", notes=" + notes + '}';
    }
}
