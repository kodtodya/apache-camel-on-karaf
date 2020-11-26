package com.kodtodya.training.fuse.helper;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransformationHelper {

    int counter = 0;

    // Generates XMLGregorianCalendar based on current date in String format
    public XMLGregorianCalendar getXMLGregorianCalendar(String targetFormat)
            throws DatatypeConfigurationException, ParseException {
        return this.resolveDate(new Date(), targetFormat);
    }

    // Generates XMLGregorianCalendar based on current date in String format
    public XMLGregorianCalendar getXMLGregorianCalendar(String inputDate, String inputFormat, String targetFormat)
            throws DatatypeConfigurationException, ParseException {
        return this.resolveDate(new SimpleDateFormat(inputFormat).parse(inputDate), targetFormat);
    }

    private XMLGregorianCalendar resolveDate(Date date, String targetFormat) throws DatatypeConfigurationException {
        DateFormat toFormat = new SimpleDateFormat(targetFormat);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(toFormat.format(date));
    }

    public String checkAndGetFixedSizeString(String inputString, int expectedLength) {
        if (inputString.length() > expectedLength) {
            return inputString.substring(0, expectedLength - 1);
        }
        return inputString;
    }

    public boolean isNotEmptyOrNull(String input) {
        if (input.trim().equals(null) || input.trim().isEmpty()) {
            return false;
        }
        return true;
    }

    public String getUniqueId() {
        if ( counter == 999 ) {
            counter = 0;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return formatter.format(new Date()) + String.format("%03d", ++ counter);
    }
}