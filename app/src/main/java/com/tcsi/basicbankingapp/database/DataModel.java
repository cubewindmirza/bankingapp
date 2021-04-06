package com.tcsi.basicbankingapp.database;

public class DataModel {

    public static String NAME;
    public static String RECEIPTENT;
    public static String TRANSID;
    public static String AMOUNT;
    public static String DATE;

    public DataModel(String NAME, String RECEIPTENT, String TRANSID, String AMOUNT, String DATE) {
        this.NAME = NAME;
        this.RECEIPTENT = RECEIPTENT;
        this.TRANSID = TRANSID;
        this.AMOUNT = AMOUNT;
        this.DATE = DATE;
    }

    public String getNAME() {
        return NAME;
    }

    public String getRECEIPTENT() {
        return RECEIPTENT;
    }

    public String getTRANSID() {
        return TRANSID;
    }

    public String getAMOUNT() {
        return AMOUNT;
    }

    public String getDATE() {
        return DATE;
    }
}
