package com.kodtodya.training.fuse.pacs008.beans;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;
import org.apache.camel.dataformat.bindy.annotation.Section;
import org.apache.camel.spi.annotations.Component;

@Component("pacs008Header")
@Section(number = 1)
@CsvRecord(separator = "\\|", generateHeaderColumns = true, quoting = true)
public class Pacs008Header {

    @DataField(pos = 1, defaultValue = "", columnName = "\"recordType\"")
    private String recordType;

    @DataField(pos = 2, defaultValue = "", columnName = "\"msgId\"")
    private String msgId;

    @DataField(pos = 3, defaultValue = "", columnName = "\"creDtTm\"")
    private String creDtTm;

    @DataField(pos = 4, defaultValue = "", columnName = "\"batchBooking\"")
    private String batchBooking;

    @DataField(pos = 5, defaultValue = "", columnName = "\"noOfTx\"")
    private String noOfTx;

    @DataField(pos = 6, defaultValue = "", columnName = "\"ctrlSum\"")
    private String ctrlSum;

    @DataField(pos = 7, defaultValue = "", columnName = "\"totalInterbnkSttlAmt\"")
    private String totalInterbnkSttlAmt;

    @DataField(pos = 8, defaultValue = "", columnName = "\"ccy\"")
    private String ccy;

    @DataField(pos = 9, defaultValue = "", columnName = "\"interbankSettlDt\"")
    private String interbankSettlDt;

    @DataField(pos = 10, defaultValue = "", columnName = "\"recordType\"")
    private String settlInfo;

    @DataField(pos = 11, defaultValue = "", columnName = "\"settlInfo\"")
    private String settlMethod;

    @DataField(pos = 12, defaultValue = "", columnName = "\"settlAcc\"")
    private String settlAcc;

    @DataField(pos = 13, defaultValue = "", columnName = "\"id\"")
    private String id;

    @DataField(pos = 14, defaultValue = "", columnName = "\"type\"")
    private String type;

    @DataField(pos = 15, defaultValue = "", columnName = "\"name\"")
    private String name;

    @DataField(pos = 16, defaultValue = "", columnName = "\"clrSys\"")
    private String clrSys;

    @DataField(pos = 17, defaultValue = "", columnName = "\"proprietary\"")
    private String proprietary;

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getCreDtTm() {
        return creDtTm;
    }

    public void setCreDtTm(String creDtTm) {
        this.creDtTm = creDtTm;
    }

    public String getBatchBooking() {
        return batchBooking;
    }

    public void setBatchBooking(String batchBooking) {
        this.batchBooking = batchBooking;
    }

    public String getNoOfTx() {
        return noOfTx;
    }

    public void setNoOfTx(String noOfTx) {
        this.noOfTx = noOfTx;
    }

    public String getCtrlSum() {
        return ctrlSum;
    }

    public void setCtrlSum(String ctrlSum) {
        this.ctrlSum = ctrlSum;
    }

    public String getTotalInterbnkSttlAmt() {
        return totalInterbnkSttlAmt;
    }

    public void setTotalInterbnkSttlAmt(String totalInterbnkSttlAmt) {
        this.totalInterbnkSttlAmt = totalInterbnkSttlAmt;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getInterbankSettlDt() {
        return interbankSettlDt;
    }

    public void setInterbankSettlDt(String interbankSettlDt) {
        this.interbankSettlDt = interbankSettlDt;
    }

    public String getSettlInfo() {
        return settlInfo;
    }

    public void setSettlInfo(String settlInfo) {
        this.settlInfo = settlInfo;
    }

    public String getSettlMethod() {
        return settlMethod;
    }

    public void setSettlMethod(String settlMethod) {
        this.settlMethod = settlMethod;
    }

    public String getSettlAcc() {
        return settlAcc;
    }

    public void setSettlAcc(String settlAcc) {
        this.settlAcc = settlAcc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClrSys() {
        return clrSys;
    }

    public void setClrSys(String clrSys) {
        this.clrSys = clrSys;
    }

    public String getProprietary() {
        return proprietary;
    }

    public void setProprietary(String proprietary) {
        this.proprietary = proprietary;
    }
}
