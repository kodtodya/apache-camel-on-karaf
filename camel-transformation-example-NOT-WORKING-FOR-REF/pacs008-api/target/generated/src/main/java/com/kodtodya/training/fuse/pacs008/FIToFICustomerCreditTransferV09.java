//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.11.21 at 09:58:33 PM IST 
//


package com.kodtodya.training.fuse.pacs008;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FIToFICustomerCreditTransferV09 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FIToFICustomerCreditTransferV09"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="GrpHdr" type="{urn:iso:std:iso:20022:tech:xsd:pacs.008.001.09}GroupHeader93"/&gt;
 *         &lt;element name="CdtTrfTxInf" type="{urn:iso:std:iso:20022:tech:xsd:pacs.008.001.09}CreditTransferTransaction43" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FIToFICustomerCreditTransferV09", propOrder = {
    "grpHdr",
    "cdtTrfTxInf"
})
public class FIToFICustomerCreditTransferV09 {

    @XmlElement(name = "GrpHdr", required = true)
    protected GroupHeader93 grpHdr;
    @XmlElement(name = "CdtTrfTxInf", required = true)
    protected List<CreditTransferTransaction43> cdtTrfTxInf;

    /**
     * Gets the value of the grpHdr property.
     * 
     * @return
     *     possible object is
     *     {@link GroupHeader93 }
     *     
     */
    public GroupHeader93 getGrpHdr() {
        return grpHdr;
    }

    /**
     * Sets the value of the grpHdr property.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupHeader93 }
     *     
     */
    public void setGrpHdr(GroupHeader93 value) {
        this.grpHdr = value;
    }

    /**
     * Gets the value of the cdtTrfTxInf property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cdtTrfTxInf property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCdtTrfTxInf().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CreditTransferTransaction43 }
     * 
     * 
     */
    public List<CreditTransferTransaction43> getCdtTrfTxInf() {
        if (cdtTrfTxInf == null) {
            cdtTrfTxInf = new ArrayList<CreditTransferTransaction43>();
        }
        return this.cdtTrfTxInf;
    }

}
