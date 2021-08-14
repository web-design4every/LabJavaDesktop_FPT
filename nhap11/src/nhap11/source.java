/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhap11;

import java.util.Date;

/**
 *
 * @author longd
 */
public class source {
    private int stockID;
    private String stockName;
    private String address;
    private String datee;
    private String note;

    public int getStockID() {
        return stockID;
    }

    public void setStockID(int stockID) {
        this.stockID = stockID;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDatee() {
        return datee;
    }

    public void setDatee(String datee) {
        this.datee = datee;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public source(int stockID, String stockName, String address, String datee, String note) {
        this.stockID = stockID;
        this.stockName = stockName;
        this.address = address;
        this.datee = datee;
        this.note = note;
    }

    
    public source() {
    }

    
    
    
    
    
}
