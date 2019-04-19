/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csr.models;

/**
 *
 * @author R
 */
public class RlistTable {
    String productcode,customerid,buyername,sellingprice,profit,loss,sellingdate;

    public RlistTable(String productcode, String customerid, String buyername, String sellingprice, String profit, String loss, String sellingdate) {
        this.productcode = productcode;
        this.customerid = customerid;
        this.buyername = buyername;
        this.sellingprice = sellingprice;
        this.profit = profit;
        this.loss = loss;
        this.sellingdate = sellingdate;
    }

    public String getProductcode() {
        return productcode;
    }

    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getBuyername() {
        return buyername;
    }

    public void setBuyername(String buyername) {
        this.buyername = buyername;
    }

    public String getSellingprice() {
        return sellingprice;
    }

    public void setSellingprice(String sellingprice) {
        this.sellingprice = sellingprice;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public String getLoss() {
        return loss;
    }

    public void setLoss(String loss) {
        this.loss = loss;
    }

    public String getSellingdate() {
        return sellingdate;
    }

    public void setSellingdate(String sellingdate) {
        this.sellingdate = sellingdate;
    }
    
}
