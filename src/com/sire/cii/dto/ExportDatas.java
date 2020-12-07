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
    private int id;
    private String invoiceNumber;
    private String invoiceDate;
    private String settlingDate;
    private String VATDate;
    private String dueDate;
    private String dookDate;
    private double netto;
    private double bruttoSumInvoice;
    private String costCenter;
    private String orderNumber;
    private String employee;
    private String machineNumber;
    private String projekt;
    private String notes;
//</editor-fold>

    public ExportDatas() {
    }

    public ExportDatas(String invoiceNumber, String invoiceDate, String settlingDate, String VATDate, String dueDate, String dookDate, double netto, double bruttoSumInvoice, String costCenter, String orderNumber, String employee, String machineNumber, String projekt, String notes) {
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.settlingDate = settlingDate;
        this.VATDate = VATDate;
        this.dueDate = dueDate;
        this.dookDate = dookDate;
        this.netto = netto;
        this.bruttoSumInvoice = bruttoSumInvoice;
        this.costCenter = costCenter;
        this.orderNumber = orderNumber;
        this.employee = employee;
        this.machineNumber = machineNumber;
        this.projekt = projekt;
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getSettlingDate() {
        return settlingDate;
    }

    public void setSettlingDate(String settlingDate) {
        this.settlingDate = settlingDate;
    }

    public String getVATDate() {
        return VATDate;
    }

    public void setVATDate(String VATDate) {
        this.VATDate = VATDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDookDate() {
        return dookDate;
    }

    public void setDookDate(String dookDate) {
        this.dookDate = dookDate;
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

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getMachineNumber() {
        return machineNumber;
    }

    public void setMachineNumber(String machineNumber) {
        this.machineNumber = machineNumber;
    }

    public String getProjekt() {
        return projekt;
    }

    public void setProjekt(String projekt) {
        this.projekt = projekt;
    }
    
    
    
}
