package com.tsilva.newspapertask.controller.rest.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

/**
 * Created by Telmo Silva on 05.10.2020.
 */

public class XmlUtil
{
    private static Logger LOG = LoggerFactory.getLogger(XmlUtil.class);

    public static String toXML(Object data)
    {
        String xml = "";
        try
        {
            LOG.info("Generating xml for: " + data.getClass());
            JAXBContext jaxbContext = JAXBContext.newInstance(data.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //jaxbMarshaller.marshal(data, System.out);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(data, sw);
            xml = sw.toString();
        }
        catch (JAXBException e)
        {
            LOG.debug("Conversion failure!", e);
        }

        return xml;
    }
}
