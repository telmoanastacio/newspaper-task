package com.tsilva.newspapertask.controller.rest.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Telmo Silva on 05.10.2020.
 */

public class EPaperRequestValidatorXsd implements XsdValidator
{
    private final Logger LOG = LoggerFactory.getLogger(EPaperRequestValidatorXsd.class);

    @Override
    public boolean validate(String xmlStr)
    {
        try
        {
            Source xmlFileSrc = new StreamSource(new ByteArrayInputStream(xmlStr.getBytes()));
            SchemaFactory schemaFactory = SchemaFactory
                    .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            File schemaFile = null;
            try
            {
                schemaFile = ResourceUtils.getFile("classpath:templates/epaper-scheme.xsd");
            }
            catch (FileNotFoundException e)
            {
                LOG.debug("SchemaFile not found.", e);
            }

            if(schemaFile == null)
            {
                schemaFile = ResourceUtils.getFile(new URI("file:///target/classes/templates/epaper-scheme.xsd"));
                LOG.info("SchemaFile found.");
            }
            LOG.info("SchemaFile path: " + schemaFile.getAbsolutePath());
            Schema schema = schemaFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(xmlFileSrc);
            return true;
        }
        catch (IOException | SAXException | URISyntaxException e)
        {
            LOG.debug("Cant validate data", e);
            return false;
        }
    }
}
