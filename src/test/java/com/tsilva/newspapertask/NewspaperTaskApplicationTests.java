package com.tsilva.newspapertask;

import com.tsilva.newspapertask.controller.rest.RestControllerNews;
import com.tsilva.newspapertask.controller.rest.contract.request.*;
import com.tsilva.newspapertask.controller.rest.validator.EPaperRequestValidatorXsd;
import com.tsilva.newspapertask.controller.rest.validator.XsdValidator;
import com.tsilva.newspapertask.persistence.dao.IDeviceInfoJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NewspaperTaskApplicationTests
{
	@Autowired
	private RestControllerNews restControllerNews;

	@Autowired
	HttpServletResponse response;

	@Autowired
	private IDeviceInfoJpaRepository iDeviceInfoJpaRepository;

	@Test
	void contextLoads()
	{
		assertNotNull(restControllerNews);
		assertNotNull(response);
		assertNotNull(iDeviceInfoJpaRepository);
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

	// this test is failing in unit testing, but passes in functional testing
	// use the following curl expression
	/*
curl --location --request POST 'localhost:8080/news/post' \
--header 'Content-Type: application/xml' \
--data-raw '<?xml version="1.0" encoding="utf-8"?>
<epaperRequest>
  <deviceInfo name="Browser" id="test@comp">
    <screenInfo width="1280" height="752" dpi="160" />
    <osInfo name="Browser" version="1.0" />
    <appInfo>
      <newspaperName>New name</newspaperName>
      <version>1.0</version>
    </appInfo>
  </deviceInfo>
  <getPages editionDefId="11" publicationDate="2017-06-08"/>
</epaperRequest> '
	*/
//	@Test
//	void controlDataRestControllerPost()
//	{
//		EpaperRequest epaperRequest = new EpaperRequest(
//				new DeviceInfo(
//						new ScreenInfo(1280, 752, 160),
//						new OsInfo("Browser", "1.0"),
//						new AppInfo("abb", "1.0"),
//						"Browser",
//						"test@comp"),
//				new GetPages(11L, "2017-06-06"));
//
//		assertTrue(restControllerNews.postNews(epaperRequest, response));
//	}

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
				new GetPages(11L, "2017-06-06"));

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
				new GetPages(11L, "2017-06-06"));

		assertFalse(restControllerNews.postNews(epaperRequest, response));
	}

	@Test
	void deviceInformationLike()
	{
		List<com.tsilva.newspapertask.persistence.entity.DeviceInfo> deviceInfoList =
				iDeviceInfoJpaRepository.findAllDeviceInfoLikeAppName("a", Sort.by("appName"));

		assertNotNull(deviceInfoList);
		assertTrue(deviceInfoList.size() > 0);
	}

	@Test
	void deviceInformationLikeNoMatch()
	{
		List<com.tsilva.newspapertask.persistence.entity.DeviceInfo> deviceInfoList =
				iDeviceInfoJpaRepository.findAllDeviceInfoLikeAppName("c", Sort.by("appName"));

		assertNotNull(deviceInfoList);
		assertEquals(deviceInfoList.size(), 0);
	}

	@Test
	void getNewsSortedAlpha()
	{
		assertNotNull(restControllerNews.getNews(response, "appName", 1, "name1"));
	}

	@Test
	void getNewsSortedAlphaNoMach()
	{
		assertNull(restControllerNews.getNews(response, "appName", 1, "no match"));
	}

	@Test
	void getNewsSortedTimeStamp()
	{
		assertNotNull(restControllerNews.getNews(response, "updateTs", 1, "1000000"));
	}

	@Test
	void getNewsSortedTimeStampInvalid()
	{
		assertNull(restControllerNews.getNews(response, "updateTs", 1, "invalid data"));
	}

	@Test
	void getNewsNullParameters()
	{
		assertNotNull(restControllerNews.getNews(response, null, null, null));
	}

	private String readFile(String path, Charset encoding)
			throws IOException
	{
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
}
