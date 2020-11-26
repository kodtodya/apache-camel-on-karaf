package com.kodtodya.training.fuse.pacs008.beans;


import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;
import org.apache.camel.dataformat.bindy.annotation.Section;
import org.apache.camel.spi.annotations.Component;

@Component("pacs008Trailer")
@Section(number = 3)
@CsvRecord(separator = "\\|", generateHeaderColumns = true, quoting = true)
public class Pacs008Trailer {

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

}
