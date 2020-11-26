package com.kodtodya.training.fuse.mapper;

import com.kodtodya.training.fuse.pacs008.*;
import com.kodtodya.training.fuse.helper.TransformationHelper;
import com.sun.xml.bind.marshaller.NamespacePrefixMapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import org.apache.camel.Exchange;

//import javax.xml.bind.JAXBContext;
import com.kodtodya.training.fuse.pacs008.beans.*;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.spi.DataFormat;
import org.apache.camel.spi.annotations.Component;
import org.apache.commons.io.IOUtils;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import java.io.InputStream;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

@Component(value = "pacs008Mapper")
public class Pacs008Mapper {

    private DataFormat headerFormat = new BindyCsvDataFormat(Pacs008Header.class);
    private DataFormat detailsFormat = new BindyCsvDataFormat(Pacs008Details.class);
    private DataFormat trailerFormat = new BindyCsvDataFormat(Pacs008Trailer.class);

    public static final String DATETIME_FMTSTR_TIMESTAMPXML = "yyyy-MM-dd'T'HH:mm:ss";

    private TransformationHelper helper = new TransformationHelper();

    public void mapCsvToPojos(Exchange exchange) throws Exception {
        Pacs008 pacs008 = new Pacs008();
        String data = exchange.getIn().getBody(String.class);
        List<Pacs008Details> detailRecords = new ArrayList<>();

        for (String record : data.split(System.lineSeparator())) {
            InputStream recordStream = IOUtils.toInputStream(record, "UTF-8");
            if (record.startsWith("HEADER")) {
                Pacs008Header pacs008Header = (Pacs008Header) headerFormat.unmarshal(exchange, recordStream);
                pacs008.setHeader(pacs008Header);
            } else if (record.startsWith("DETAIL")) {
                Pacs008Details detail = (Pacs008Details) detailsFormat.unmarshal(exchange, recordStream);
                detailRecords.add(detail);
            } else if (record.startsWith("TRAILER")) {
                Pacs008Trailer trailer = (Pacs008Trailer) trailerFormat.unmarshal(exchange, recordStream);
                pacs008.setTrailer(trailer);
            }
        }
        pacs008.setEntries(detailRecords);
        exchange.getIn().setBody(pacs008);
    }

    public void mapCsvPojosToXml(Exchange exchange) throws JAXBException, JAXBException, DatatypeConfigurationException, ParseException, jakarta.xml.bind.JAXBException {
        Marshaller jaxbMarshaller = JAXBContext.newInstance(Document.class).createMarshaller();

        // To format XML
        ((Marshaller) jaxbMarshaller).setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        ((Marshaller) jaxbMarshaller).setProperty("com.sun.xml.bind.namespacePrefixMapper", new NamespacePrefixMapper() {

            private Map<String, String> namespaceMap = new HashMap<>();

            @Override
            public String[] getPreDeclaredNamespaceUris() {
                return new String[] { XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI };
            }

            @Override
            public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
                namespaceMap.put(XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI, "xsi");
                return namespaceMap.getOrDefault(namespaceUri, suggestion);
            }
        });

        Pacs008 pacs008 = exchange.getIn().getBody(Pacs008.class);
        Document document = this.mapPacs008Document(pacs008);
        //If we DO NOT have JAXB annotated class
        JAXBElement<Document> jaxbElement = new ObjectFactory().createDocument(document);
        StringWriter writer = new StringWriter();
        jaxbMarshaller.marshal(jaxbElement, writer);

        // output file name
        String ouputFileName = "TEST_BANK_PACS008_" + new Random().nextLong() + ".xml";

        exchange.getIn().setHeader("output-filename", ouputFileName);
        //set body
        exchange.getIn().setBody(writer.toString());

    }

    private Document mapPacs008Document(Pacs008 pacs008) throws DatatypeConfigurationException, ParseException {
        Document document = new Document();
        document.setFIToFICstmrCdtTrf(this.mapFitToFiCustomerCdtTrf(pacs008));
        return document;
    }

    private FIToFICustomerCreditTransferV09 mapFitToFiCustomerCdtTrf(Pacs008 pacs008) throws DatatypeConfigurationException, ParseException {
        FIToFICustomerCreditTransferV09 fiToFICstmrCrXfer = new FIToFICustomerCreditTransferV09();
        fiToFICstmrCrXfer.setGrpHdr(this.mapGroupHeaders(pacs008));
        return fiToFICstmrCrXfer;
    }

    private GroupHeader93 mapGroupHeaders(Pacs008 pacs008) throws DatatypeConfigurationException, ParseException {
        GroupHeader93 groupHdr = new GroupHeader93();
        Pacs008Header header = pacs008.getHeader();
        groupHdr.setMsgId(header.getMsgId());
        groupHdr.setCreDtTm(helper.getXMLGregorianCalendar(DATETIME_FMTSTR_TIMESTAMPXML));
        groupHdr.setNbOfTxs(header.getNoOfTx());
        BigDecimal ctrlSum = new BigDecimal(header.getCtrlSum());
        groupHdr.setCtrlSum(ctrlSum);
        String orgId = header.getId();
        groupHdr.setPmtTpInf(this.mapPaymentTypeInfo(pacs008));
        return groupHdr;
    }

    private PaymentTypeInformation28 mapPaymentTypeInfo(Pacs008 pacs008) {
        PaymentTypeInformation28 paymentTypeInfo = new PaymentTypeInformation28();
        Pacs008Header header = pacs008.getHeader();
        paymentTypeInfo.setClrChanl(this.mapClrChnl(header));
        return paymentTypeInfo;
    }

    private ClearingChannel2Code mapClrChnl(Pacs008Header header) {
        if (header.getCcy().equals("CAD")) {
            return ClearingChannel2Code.RTGS;
        } else {
            return ClearingChannel2Code.MPNS;
        }
    }
}
