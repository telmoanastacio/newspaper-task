package com.tsilva.newspapertask;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NewspaperTaskApplicationTests
{
	@Test
	void contextLoads() {}

	@Test
	void validateControlXml()
	{
		boolean isValid = false;
		File xmlFile = new File("src/main/resources/templates/news-metadata.xml");
		File schemaFile = new File("src/main/resources/templates/news-metadata-scheme.xsd");
		Source xmlFileSrc = null;
		try
		{
			xmlFileSrc = new StreamSource(new FileInputStream(xmlFile));
			SchemaFactory schemaFactory = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

			Schema schema = schemaFactory.newSchema(schemaFile);
			Validator validator = schema.newValidator();
			validator.validate(xmlFileSrc);
			isValid = true;
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (SAXException e)
		{
			e.printStackTrace();
		}
		finally
		{
			assertTrue(isValid);
		}
	}
}
