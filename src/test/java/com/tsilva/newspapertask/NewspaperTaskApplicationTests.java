package com.tsilva.newspapertask;

import com.tsilva.newspapertask.controller.rest.RestControllerNews;
import com.tsilva.newspapertask.controller.rest.contract.request.*;
import com.tsilva.newspapertask.controller.rest.validator.EPaperRequestValidatorXsd;
import com.tsilva.newspapertask.controller.rest.validator.XsdValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NewspaperTaskApplicationTests
{
	@Autowired
	private RestControllerNews restControllerNews;

	@Autowired
	HttpServletResponse response;

	@Test
	void contextLoads()
	{
		assertNotNull(restControllerNews);
	}

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

	@Test
	void controlDataRestControllerPost()
	{
		EpaperRequest epaperRequest = new EpaperRequest(
				new DeviceInfo(
						new ScreenInfo(1280, 752, 160),
						new OsInfo("Browser", "1.0"),
						new AppInfo("abb", "1.0"),
						"Browser",
						"test@comp"),
				new GetPages(11, "2017-06-06"));

		assertTrue(restControllerNews.postNews(epaperRequest, response));
	}

	@Test
	void missingTagsDataRestControllerPost()
	{
		EpaperRequest epaperRequest = new EpaperRequest(
				new DeviceInfo(
						null,
						new OsInfo("Browser", "1.0"),
						new AppInfo("abb", "1.0"),
						"Browser",
						"test@comp"),
				new GetPages(11, "2017-06-06"));

		assertFalse(restControllerNews.postNews(epaperRequest, response));
	}

	@Test
	void noAttributeDataRestControllerPost()
	{
		EpaperRequest epaperRequest = new EpaperRequest(
				new DeviceInfo(
						new ScreenInfo(1280, 752, 160),
						new OsInfo(null, null),
						new AppInfo("abb", "1.0"),
						null,
						null),
				new GetPages(11, "2017-06-06"));

		assertFalse(restControllerNews.postNews(epaperRequest, response));
	}

	private String readFile(String path, Charset encoding)
			throws IOException
	{
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
}
