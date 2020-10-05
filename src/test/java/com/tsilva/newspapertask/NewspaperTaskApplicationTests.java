package com.tsilva.newspapertask;

import com.tsilva.newspapertask.controller.rest.validator.EPaperRequestValidatorXsd;
import com.tsilva.newspapertask.controller.rest.validator.XsdValidator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

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
		XsdValidator xsdValidator = new EPaperRequestValidatorXsd();
		try
		{
			isValid = xsdValidator.validate(readFile(
					"src/test/resources/templates/news-metadata-control.xml",
					Charset.defaultCharset()));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			assertTrue(isValid);
		}
	}

	@Test
	void validateNoAttributeXml()
	{
		boolean isValid = false;
		XsdValidator xsdValidator = new EPaperRequestValidatorXsd();
		try
		{
			isValid = xsdValidator.validate(readFile(
					"src/test/resources/templates/news-metadata-control-no-attribute.xml",
					Charset.defaultCharset()));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			// this validation is supposed to return false, xml is not valid against xsd
			assertFalse(isValid);
		}
	}

	@Test
	void validateMissingTagsXml()
	{
		boolean isValid = false;
		XsdValidator xsdValidator = new EPaperRequestValidatorXsd();
		try
		{
			isValid = xsdValidator.validate(readFile(
					"src/test/resources/templates/news-metadata-control-missing-tags.xml",
					Charset.defaultCharset()));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			// this validation is supposed to return false, xml is not valid against xsd
			assertFalse(isValid);
		}
	}

	private String readFile(String path, Charset encoding)
			throws IOException
	{
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
}
