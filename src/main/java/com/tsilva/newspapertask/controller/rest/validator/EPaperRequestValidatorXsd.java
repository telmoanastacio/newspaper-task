package com.tsilva.newspapertask.controller.rest.validator;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by Telmo Silva on 05.10.2020.
 */

public class EPaperRequestValidatorXsd implements XsdValidator
{
    @Override
    public boolean validate(String xmlStr)
    {
        File schemaFile = new File("src/main/resources/templates/epaper-scheme.xsd");
        Source xmlFileSrc = null;
        try
        {
            xmlFileSrc = new StreamSource(new ByteArrayInputStream(xmlStr.getBytes()));
            SchemaFactory schemaFactory = SchemaFactory
                    .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            Schema schema = schemaFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(xmlFileSrc);
            return true;
        }
        catch (IOException | SAXException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
