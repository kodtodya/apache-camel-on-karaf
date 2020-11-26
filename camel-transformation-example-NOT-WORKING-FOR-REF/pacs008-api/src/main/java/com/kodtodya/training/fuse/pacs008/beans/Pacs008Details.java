package com.kodtodya.training.fuse.pacs008.beans;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;
import org.apache.camel.dataformat.bindy.annotation.Section;
import org.apache.camel.spi.annotations.Component;

@Component("pacs008Details")
@Section(number = 2)
@CsvRecord(separator = "\\|", generateHeaderColumns = true, quoting = true)
public class Pacs008Details {

    @DataField(pos = 1, defaultValue = "", columnName = "\"recordType\"")
    private String recordType;

    @DataField(pos = 2, defaultValue = "", columnName = "\"instructionPriority\"")
    private String instructionPriority;

    @DataField(pos = 3, defaultValue = "", columnName = "\"serviceLvl\"")
    private String serviceLvl;

    @DataField(pos = 4, defaultValue = "", columnName = "\"localInstrument\"")
    private String localInstrument;

    @DataField(pos = 5, defaultValue = "", columnName = "\"BICFI\"")
    private String bicfi;

    @DataField(pos = 6, defaultValue = "", columnName = "\"clrSysMemId\"")
    private String clrSysMemId;

    @DataField(pos = 7, defaultValue = "", columnName = "\"name\"")
    private String name;

    @DataField(pos = 8, defaultValue = "", columnName = "\"postalAddress\"")
    private String postalAddress;

    @DataField(pos = 9, defaultValue = "", columnName = "\"branchId\"")
    private String branchId;

    @DataField(pos = 10, defaultValue = "", columnName = "\"branchAddress\"")
    private String branchAddress;

    @DataField(pos = 11, defaultValue = "", columnName = "\"instructionId\"")
    private String instructionId;

    @DataField(pos = 12, defaultValue = "", columnName = "\"endToEndId\"")
    private String endToEndId;

    @DataField(pos = 13, defaultValue = "", columnName = "\"txId\"")
    private String txId;

    @DataField(pos = 14, defaultValue = "", columnName = "\"clrSysRef\"")
    private String clrSysRef;

    @DataField(pos = 15, defaultValue = "", columnName = "\"settlPriority\"")
    private String settlPriority;

    @DataField(pos = 16, defaultValue = "", columnName = "\"debitDtTm\"")
    private String debitDtTm;

    @DataField(pos = 17, defaultValue = "", columnName = "\"clsTime\"")
    private String clsTime;

    @DataField(pos = 18, defaultValue = "", columnName = "\"acceptanceTm\"")
    private String acceptanceTm;

    @DataField(pos = 19, defaultValue = "", columnName = "\"exchangeRate\"")
    private String exchangeRate;

    @DataField(pos = 20, defaultValue = "", columnName = "\"chargeBearer\"")
    private String chargeBearer;

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getInstructionPriority() {
        return instructionPriority;
    }

    public void setInstructionPriority(String instructionPriority) {
        this.instructionPriority = instructionPriority;
    }

    public String getServiceLvl() {
        return serviceLvl;
    }

    public void setServiceLvl(String serviceLvl) {
        this.serviceLvl = serviceLvl;
    }

    public String getLocalInstrument() {
        return localInstrument;
    }

    public void setLocalInstrument(String localInstrument) {
        this.localInstrument = localInstrument;
    }

    public String getBicfi() {
        return bicfi;
    }

    public void setBicfi(String bicfi) {
        this.bicfi = bicfi;
    }

    public String getClrSysMemId() {
        return clrSysMemId;
    }

    public void setClrSysMemId(String clrSysMemId) {
        this.clrSysMemId = clrSysMemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public String getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(String instructionId) {
        this.instructionId = instructionId;
    }

    public String getEndToEndId() {
        return endToEndId;
    }

    public void setEndToEndId(String endToEndId) {
        this.endToEndId = endToEndId;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public String getClrSysRef() {
        return clrSysRef;
    }

    public void setClrSysRef(String clrSysRef) {
        this.clrSysRef = clrSysRef;
    }

    public String getSettlPriority() {
        return settlPriority;
    }

    public void setSettlPriority(String settlPriority) {
        this.settlPriority = settlPriority;
    }

    public String getDebitDtTm() {
        return debitDtTm;
    }

    public void setDebitDtTm(String debitDtTm) {
        this.debitDtTm = debitDtTm;
    }

    public String getClsTime() {
        return clsTime;
    }

    public void setClsTime(String clsTime) {
        this.clsTime = clsTime;
    }

    public String getAcceptanceTm() {
        return acceptanceTm;
    }

    public void setAcceptanceTm(String acceptanceTm) {
        this.acceptanceTm = acceptanceTm;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getChargeBearer() {
        return chargeBearer;
    }

    public void setChargeBearer(String chargeBearer) {
        this.chargeBearer = chargeBearer;
    }
}
