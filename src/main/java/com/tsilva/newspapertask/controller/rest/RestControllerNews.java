package com.tsilva.newspapertask.controller.rest;

import com.tsilva.newspapertask.controller.rest.contract.request.EpaperRequest;
import com.tsilva.newspapertask.controller.rest.util.XmlUtil;
import com.tsilva.newspapertask.controller.rest.validator.EPaperRequestValidatorXsd;
import com.tsilva.newspapertask.controller.rest.validator.XsdValidator;
import com.tsilva.newspapertask.persistence.entity.DeviceInfo;
import com.tsilva.newspapertask.persistence.entity.GetPages;
import com.tsilva.newspapertask.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Telmo Silva on 05.10.2020.
 */

@RestController
@RequestMapping(value = "/news", method = RequestMethod.POST)
public class RestControllerNews
{
    @Autowired
    private INewsService iNewsService;

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    /**
     * @return true if xml is valid against xsd
     */
    public boolean postNews(@RequestBody EpaperRequest epaperRequest, HttpServletResponse response)
    {
        XsdValidator xsdValidator = new EPaperRequestValidatorXsd();
        boolean isValid = xsdValidator.validate(XmlUtil.toXML(epaperRequest));

        if(isValid)
        {
            DeviceInfo deviceInfo = new DeviceInfo(epaperRequest.getDeviceInfo().getScreenInfo().getWidth(),
                    epaperRequest.getDeviceInfo().getScreenInfo().getHeight(),
                    epaperRequest.getDeviceInfo().getScreenInfo().getDpi(),
                    epaperRequest.getDeviceInfo().getOsInfo().getName(),
                    epaperRequest.getDeviceInfo().getOsInfo().getVersion(),
                    epaperRequest.getDeviceInfo().getAppInfo().getNewspaperName(),
                    epaperRequest.getDeviceInfo().getAppInfo().getVersion(),
                    epaperRequest.getDeviceInfo().getName(),
                    epaperRequest.getDeviceInfo().getId());

            GetPages getPages = new GetPages(epaperRequest.getGetPages().getEditionDefId(),
                    epaperRequest.getGetPages().getPublicationDate(),
                    deviceInfo);

            iNewsService.saveNews(deviceInfo, getPages);
            response.setStatus(HttpServletResponse.SC_OK);
        }
        else
        {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        return isValid;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<EpaperRequest> getNews(HttpServletResponse response,
                                       @Nullable @RequestParam(name = "sort", required = false) String sort,
                                       @Nullable @RequestParam(name = "page", required = false) Integer page,
                                       @Nullable @RequestParam(name = "filter", required = false) String filter)
    {
        List<EpaperRequest> epaperRequestList = iNewsService.getNews(sort, page, filter);

        if(epaperRequestList == null)
        {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        else
        {
            response.setStatus(HttpServletResponse.SC_OK);
        }

        return epaperRequestList;
    }
}
